package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	private double x;
	private double y;
	private Image imgFondo;
	
	
	public Fondo(double x, double y) {
		this.x = x;
		this.y = y;
		this.imgFondo = Herramientas.cargarImagen("fondo.jpg");
	}
	public GameOver(double x, double y) {
		this.x = x;
		this.y = y;
		this.imgFondo = Herramientas.cargarImagen("gameover.jpg");
	}
	
	public void dibujarse(Entorno e) {
		e.dibujarImagen(imgFondo, this.x, this.y, 0); 
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

	public Image getImgFondo() {
		return imgFondo;
	}

	public void setImgFondo(Image imgFondo) {
		this.imgFondo = imgFondo;
	}

	
	
}
