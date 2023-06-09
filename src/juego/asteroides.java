package juego;

import java.awt.Color;

import entorno.Entorno;

public class asteroides {
	private double x;
	private double y;
	private double radio;
	private double velocidad;
	
	public asteroides(double x, double y, double radio, double velocidad) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.velocidad = velocidad;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, radio, Color.red);
	}
	
	public boolean chocasteCon(astro_megaship nave) {
		return (this.getX() > nave.getX() - nave.getAncho() / 2) &&
				(this.getX() < nave.getX() + nave.getAncho() / 2) &&
				(this.getY() < nave.getY() + nave.getAlto()/2) && 
				(this.getY()> nave.getY() - nave.getAlto() /2); 
	}
	
	public boolean chocasteCon(Proyectil proyectil) {
		return (this.getX() > proyectil.getX() - proyectil.getRadio()*2) &&
				(this.getX() < proyectil.getX() + proyectil.getRadio()*2) &&
				(this.getY() < proyectil.getY() + proyectil.getRadio()*2) && 
				(this.getY()> proyectil.getY() - proyectil.getRadio()*2);
	}
	
	public void mover_desdeIzquierda() {
		y += velocidad; //va de 0 a 600 a la velocidad del asteroide
		x += velocidad; // va de 0 a 800 a la velocidad del asteroide
	}
	
	public void mover_desdeDerecha() {
	y += velocidad; //
	x -= velocidad; // 
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

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}


	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}


	

}
