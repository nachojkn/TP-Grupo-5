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
	boolean enPantalla = false; //? Variable usada para controlar cuándo proyectil se encuentra en pantalla
	
	//- ========= DOUBLES =========	
	double tiempoInicial = System.currentTimeMillis(); //? Tiempo de inicio de juego, referencia
	double velocidadDestructores = 1.0;
	double velocidadAsteroides = 1.5;
	

	//- ========= INTEGERS =========
	int tiempoMinDispEnem = 3000; //? Tiempo mínimo entre disparos enemigos
	int tiempoMaxDispEnem = 7000; //? Tiempo máximo entre disparos enemigos

	int anchoDestructores = 50;
	int altoDestructores = 50;

	int radioAsteroides = 20;
	

	//- ========= ARRAYS =========
	private astro_megaship nave;

	private Proyectil proyectil;
	private Proyectil[] proyectilesDestructores = new Proyectil[4];

	private asteroides[] asteroides = new asteroides[4];
	//? Posición inicial en X de cada asteroide
	double[] posXIniAsteroides = {0,200,800,500};
	//? Posición inicial en Y de cada asteroide
	double[] posYIniAsteroides = {0,250,50,200};

	private destructores_estelares[] destructores = new destructores_estelares[4];
	//? Posición inicial en X de cada Destructor
	double[] posXIniDestructores = {100,300,500,700};
	//? Posición inicial en Y de cada Destructor
	double[] posYIniDestructores = {10,10,10,10};
	


	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian, The Final Frontier - Grupo 5 - v0.1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		//..

		fondo = Herramientas.cargarImagen("fondo.jpg");
		
		//? Creación de objeto nave
		this.nave = new astro_megaship(entorno.ancho()/2, entorno.alto()-100,70,70,4.0);
		
		//? Creación de objeto proyectil
		this.proyectil = new Proyectil(entorno.ancho()/2, entorno.alto()-140,10,5);

		//? Creación de objetos proyectilDestructores		
		for(int i = 0; i<destructores.length; i++)
			this.proyectilesDestructores[i] = new Proyectil(entorno.ancho()/2, entorno.alto()-140,10,5);		

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
		// Procesamiento de un instante de tiempo
		// ...
		entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);
		nave.dibujar(entorno);

//* ========== DIBUJO | DESTRUCTORES ============ */
		for(int i=0; i<destructores.length; i++)
			destructores[i].dibujar(entorno);

//* ========== DIBUJO | ASTEROIDES ============ */
		for(int i=0; i<asteroides.length; i++)
			asteroides[i].dibujar(entorno);

//* ========== MOVIMIENTO | ASTEROIDES ============ */
		asteroides[0].mover_desdeIzquierda();
		asteroides[1].mover_desdeIzquierda(); 
		asteroides[2].mover_desdeDerecha();
		asteroides[3].mover_desdeDerecha();

//* ========== MOVIMIENTO | DESTRUCTORES ============ */
		for(int i=0;i<destructores.length;i++)
			destructores[i].descender();
		
//* ========== MOVIMIENTO IZQ | NAVE ASTRO-MEGASHIP ============ */
		
		if(this.nave.getX() - this.nave.getAncho()/2 > 0 && entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
			nave.moverIzquierda();

//* ========== MOVIMIENTO DER | NAVE ASTRO-MEGASHIP ============ */
		
		if(this.nave.getX() + this.nave.getAncho()/2 < entorno.ancho() && entorno.estaPresionada(entorno.TECLA_DERECHA))
			nave.moverDerecha(); 

//* ========== MOVIMIENTO IZQ | PROYECTIL ============ */
		if(this.proyectil.getX()/2 - this.proyectil.getRadio()/2 > 0 && entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
			proyectil.moverIzquierda();

//* ========== MOVIMIENTO DER | PROYECTIL ============ */
		if(this.proyectil.getX()/2 + this.proyectil.getRadio()/2 < entorno.ancho()/2 && entorno.estaPresionada(entorno.TECLA_DERECHA))
			proyectil.moverDerecha();			
		

//* ========== DISPARO | NAVE ASTRO-MEGASHIP ============ */
		if(entorno.estaPresionada(entorno.TECLA_ESPACIO) || enPantalla){
			enPantalla =true;
			double x = nave.getX();
			double yProy = proyectil.getY();
			if(enPantalla){
				proyectil.dibujar(entorno);
				proyectil.disparoNave(entorno, x);
				yProy = proyectil.getY();
			}
			if(yProy< 0){
				enPantalla = false;
			}
			
			
		}

//* ========== DISPARO | RANDOM DESTRUCTORES ESTELARES ============ */
		// if((System.currentTimeMillis()-tiempoInicial)==(int)(Math.random() * (tiempoMaxDispEnem-tiempoMinDispEnem+1)+tiempoMinDispEnem) || System.currentTimeMillis()+tiempoInicial == tiempoMaxDispEnem){
		// 	proyectil.dibujar(entorno);
		// 	proyectil.disparoDestructor(entorno);
		// }


//* ========== FUNCION DE RESETEO ASTEROIDES ============ */
		for(int i = 0; i<asteroides.length; i++){
			if(asteroides[i].getY() >= 600){
					asteroides[i] = null;
					this.asteroides[i] = new asteroides(posXIniAsteroides[i],posYIniAsteroides[i],radioAsteroides,velocidadAsteroides);					
			}
		}

//* ========== FUNCION DE RESETEO PROYECTIL ============ */
		if(proyectil.getY() <= 0.0){
			proyectil = null;
		}




	}
	
		@SuppressWarnings("unused")
		public static void main(String[] args)
		{
			Juego juego = new Juego();
		}
	
}