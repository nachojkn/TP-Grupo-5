package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Image fondo;
	private astro_megaship nave;
	private Proyectil proyectil;
	private asteroides[] asteroide = new asteroides[4];

	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Attack on Titan, Final Season - Grupo- v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		//..

		fondo = Herramientas.cargarImagen("fondo.jpg");
		
		this.nave = new astro_megaship(entorno.ancho()/2, entorno.alto()-100,70,70,4.0);
		this.proyectil = new Proyectil(entorno.ancho()/2, entorno.alto()-140,10,4.0);
		
		this.asteroide[0] = new asteroides(0,0,20,1.5);
		this.asteroide[1] = new asteroides(200,250,20,1.5);
		this.asteroide[2] = new asteroides(800,0,20,1.5);
		this.asteroide[3] = new asteroides(500,200,20,1.5);
		
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);
		nave.dibujar(entorno);
		proyectil.dibujar(entorno);

		
		for(int i=0; i<asteroide.length; i++)
			asteroide[i].dibujar(entorno);

		asteroide[0].mover_desdeIzquierda();
		asteroide[1].mover_desdeIzquierda(); 
		asteroide[2].mover_desdeDerecha();
		asteroide[3].mover_desdeDerecha();
		
		
		if(this.nave.getX() - this.nave.getAncho()/2 > 0 && entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
			nave.moverIzquierda();
		
		if(this.nave.getX() + this.nave.getAncho()/2 < entorno.ancho() && entorno.estaPresionada(entorno.TECLA_DERECHA))
			nave.moverDerecha();
		
		if(this.proyectil.getX()/2 - this.proyectil.getRadio()/2 > 0 && entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
			proyectil.moverIzquierda();
		
		if(this.proyectil.getX()/2 + this.proyectil.getRadio()/2 < entorno.ancho()/2 && entorno.estaPresionada(entorno.TECLA_DERECHA))
			proyectil.moverDerecha();			
		
			proyectil.mover(entorno);		
	}
						

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
