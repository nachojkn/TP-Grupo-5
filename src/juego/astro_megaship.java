package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class astro_megaship {
	
	private double x;
	private double y;
	private int alto;
	private int ancho;
	private double desplazamiento;
	private Image img_nave;
//	public Image im_Nave = Herramientas.cargarImagen("Image_nave.png");
	
	public astro_megaship(double x, double y, int alto, int ancho , double desplazamiento) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.desplazamiento = desplazamiento;
	}
	
	public void dibujar (Entorno e) {
		e.dibujarRectangulo(x, y, alto, ancho, 0, Color.white); //pantalla 800x600
//		e.dibujarImagen(im_Nave, x, y, alto);
	}
	
	public void moverIzquierda() {
		x -= desplazamiento;
	}
	
	public void moverDerecha() {
		x +=desplazamiento;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	
}
