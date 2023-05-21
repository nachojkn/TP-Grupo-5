package juego;



import javax.print.event.PrintJobEvent;

import org.jcp.xml.dsig.internal.dom.DOMXMLSignature.DOMSignatureValue;

import java.lang.Math; //? Para números random
import java.awt.Image; //? Para fondo de pantalla


import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;



public class Juego extends InterfaceJuego{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Image fondo;

	
	// Variables y métodos propios de cada grupo
	//- ========= BOOLEANS =========
	boolean cambioDireccion = false; //? Cambia la dirección de movimiento en x de destructores

	//- ========= DOUBLES =========	
	double tiempoInicial = System.currentTimeMillis(); //? Tiempo de inicio de juego, referencia
	double velocidadDestructores = 1.0;
	double velocidadAsteroides = 1.5;
	double x;

	//- ========= INTEGERS =========
	int tiempoMinDispEnem = 3000; //? Tiempo mínimo entre disparos enemigos
	int tiempoMaxDispEnem = 7000; //? Tiempo máximo entre disparos enemigos

	int anchoDestructores = 50;
	int altoDestructores = 50;

	int radioAsteroides = 20;
	
	int enJuego = 3; //? Variable que determina si el juego sigue en curso

	//- ========= ARRAYS =========
	private astro_megaship nave;

	private Proyectil proyectil;

	private asteroides[] asteroides = new asteroides[4];
	//? Posición inicial en X de cada asteroide
	double[] posXIniAsteroides = {0,800,200,500};
	//? Posición inicial en Y de cada asteroide
	double[] posYIniAsteroides = {0,50,250,200};

	private destructores_estelares[] destructores = new destructores_estelares[4];
	//? Posición inicial en X de cada Destructor
	// double[] posXIniDestructores = {100,300,500,700};
	double[] posXIniDestructores = {(int)(Math.random()*(175-50+1)+50),(int)(Math.random()*(375-250+1)+250),(int)(Math.random()*(575-450+1)+450),(int)(Math.random()*(775-650+1)+650)};
	//? Posición inicial en Y de cada Destructor
	double[] posYIniDestructores = {10,10,10,10};
	


	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian, The Final Frontier - Grupo 5 - v0.1", 800, 600);
		
		// Inicializar lo que haga falta para el juego

		fondo = Herramientas.cargarImagen("fondo.jpg");
		
		//? Creación de objeto nave
		this.nave = new astro_megaship(entorno.ancho()/2, entorno.alto()-100,70,70,4.0);
		
		// //? Creación de objeto proyectil
		// for(int i =0; i<proyectil.length; i++)
		// 	this.proyectil[i] = new Proyectil(entorno.ancho()/2, entorno.alto()-140,10,5);

		//? Creación de objetos proyectilDestructores		
		// for(int i = 0; i<destructores.length; i++)
		// 	this.proyectilesDestructores[i] = new Proyectil(entorno.ancho()/2, entorno.alto()-140,10,5);		

		//? Creación de objetos destructores
		for(int i = 0; i<destructores.length; i++)
			this.destructores[i] = new destructores_estelares(posXIniDestructores[i], posYIniDestructores[i], altoDestructores, anchoDestructores, velocidadDestructores);

		//? Creación de objetos asteroides
		for(int i = 0; i<asteroides.length; i++)
			this.asteroides[i] = new asteroides(posXIniAsteroides[i], posYIniAsteroides[i], radioAsteroides, velocidadAsteroides);
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	public void tick(){
		if(enJuego>0){
			fondo = Herramientas.cargarImagen("fondo.jpg");
		// Procesamiento de un instante de tiempo
		// ...
		entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);
		nave.dibujar(entorno);

//* ========== MOVIMIENTO Y DIBUJO | DESTRUCTORES ============ */
		for(int i=0;i<destructores.length;i++){
		destructores[i].dibujar(entorno);
		destructores[i].descender(cambioDireccion);
		}

		if(destructores[3].getX() >= 750){
			cambioDireccion = true;
		}else if(destructores[0].getX() <= 50){
			cambioDireccion = false;
		}

//* ========== DIBUJO | ASTEROIDES ============ */
		for(int i=0; i<asteroides.length; i++)
			if(asteroides[i] != null)
				asteroides[i].dibujar(entorno);



//* ========== MOVIMIENTO | ASTEROIDES ============ */
		for(int i=0; i<asteroides.length;i++){
			if(i % 2 == 0){
				asteroides[i].mover_desdeIzquierda();
			}else{
				asteroides[i].mover_desdeDerecha();
			}
	
		}

		
//* ========== MOVIMIENTO IZQ | NAVE ASTRO-MEGASHIP ============ */
		
		if(this.nave.getX() - this.nave.getAncho()/2 > 0 && entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
			nave.moverIzquierda();

//* ========== MOVIMIENTO DER | NAVE ASTRO-MEGASHIP ============ */
		
		if(this.nave.getX() + this.nave.getAncho()/2 < entorno.ancho() && entorno.estaPresionada(entorno.TECLA_DERECHA))
			nave.moverDerecha(); 	
		

//* ========== DISPARO | NAVE ASTRO-MEGASHIP ============ */
		if(proyectil == null && entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			this.proyectil = new Proyectil(this.nave.getX(),this.nave.getY()-50,10,7);
		}

		if(proyectil != null){
			this.proyectil.dibujar(entorno);
			this.proyectil.disparoNave(entorno);
		}
//* ========== DISPARO | DESTRUCTORES ESTELARES ============ */
		// if(System.currentTimeMillis()-tiempoInicial % 3000 != 0) {
		// 	for(int i=0;i<proyectilesDestructores.length;i++){
		// 		proyectilesDestructores[i].dibujar(entorno);
		// 		proyectilesDestructores[i].disparoNave(entorno);	
		// 		if(proyectilesDestructores[i].getY() >=entorno.alto()) {
		// 			proyectilesDestructores[i] = null;
		// 		}
		// 	}
		// 	tiempoInicial+=3000;

		// }
		// if((System.currentTimeMillis()-tiempoInicial)==(int)(Math.random() * (tiempoMaxDispEnem-tiempoMinDispEnem+1)+tiempoMinDispEnem) || System.currentTimeMillis()+tiempoInicial == tiempoMaxDispEnem){
		// 	proyectil.dibujar(entorno);
		// 	proyectil.disparoDestructor(entorno);
		// }


//* ========== RESETEO | ASTEROIDES ============ */
		for(int i = 0; i<asteroides.length; i++){
			if(asteroides[i].getY() >= entorno.ancho()){
					asteroides[i] = null;
					this.asteroides[i] = new asteroides(posXIniAsteroides[i],posYIniAsteroides[i],radioAsteroides,velocidadAsteroides);					
			}
		}
		

//* ========== RESETEO | PROYECTIL ============ */
			if(proyectil != null && proyectil.getY() <= 0){
				proyectil = null;
			}


//* ========== RESETEO | DESTRUCTORES ============ */
		for(int i =0; i<destructores.length; i++) {
			if(destructores[i].getY() > entorno.alto()) {
				destructores[i] = null;
				this.destructores[i] = new destructores_estelares(posXIniDestructores[i], posYIniDestructores[i], altoDestructores, anchoDestructores, velocidadDestructores);
			}
		}		

//* ========== COLISION ASTEORIDES CON NAVE ============ */
		for(int i =0; i<asteroides.length; i++)
			if(asteroides[i].chocasteCon(nave) == true) {
				System.out.println("COLISION");
				asteroides[i] = null;
				this.asteroides[i] = new asteroides(posXIniAsteroides[i],posYIniAsteroides[i],radioAsteroides,velocidadAsteroides);		
				enJuego--;
			}
		}

		if(enJuego == 0){
			
			fondo = Herramientas.cargarImagen("gameover.jpg");
			entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);
			if(entorno.estaPresionada(entorno.TECLA_ESPACIO)){
				enJuego = 3;
			}
		}
	}

		@SuppressWarnings("unused")
		public static void main(String[] args)
		{
			Juego juego = new Juego();
		}
	
}