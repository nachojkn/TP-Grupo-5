package juego;

import java.awt.Color;

import entorno.Entorno;

public class Proyectil {
	private double x;
	private double y;
	private int radio;
	private double velocidad;
	
	public Proyectil(double x, double y,int radio, double velocidad) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.velocidad = velocidad;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, radio, Color.magenta);
	}
	
	public void disparoNave(Entorno e){
			y -= velocidad;
	}

	public void disparoDestructor(Entorno e) {
		y +=velocidad;
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

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}


}
