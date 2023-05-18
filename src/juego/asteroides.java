package juego;

import java.awt.Color;

import entorno.Entorno;

public class asteroides {
	private double x;
	private double y;
	private double radio;
	private double angulo;
	private double velocidad;
	
	public asteroides(double x, double y, double radio, double velocidad) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.velocidad = velocidad;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, radio*2, Color.red);
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

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}


	

}
