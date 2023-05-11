package juego;

import java.awt.Color;

import entorno.Entorno;

public class destructores_estelares {
	
	private double x;
	private double y;
	private int ancho;
	private int alto;
	int v_movimiento;
	
	public destructores_estelares(double x, double y, int antho, int alto, int v_movimiento) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto =alto;
		this.v_movimiento = v_movimiento;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, v_movimiento, Color.orange);
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

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getV_movimiento() {
		return v_movimiento;
	}

	public void setV_movimiento(int v_movimiento) {
		this.v_movimiento = v_movimiento;
	}
	
	
}
