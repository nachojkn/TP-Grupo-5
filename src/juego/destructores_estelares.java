package juego;

import java.awt.Color;

import entorno.Entorno;

public class destructores_estelares {
	
	private double x;
	private double y;
	private int ancho;
	private int alto;
	double v_movimiento;
	
	
	public destructores_estelares(double x, double y, int alto, int ancho , double v_movimiento) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.v_movimiento = v_movimiento;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.orange);
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

	public double getV_movimiento() {
		return v_movimiento;
	}

	public void setV_movimiento(double v_movimiento) {
		this.v_movimiento = v_movimiento;
	}
	
	public void descenderDer(boolean cambioDireccion){
		y += v_movimiento;
		if(!cambioDireccion) {
			x +=v_movimiento;
		}
		else {
			x -= v_movimiento;
		}
	}
	
	public void descenderIzq(boolean cambioDireccion){
		y += v_movimiento;
		if(!cambioDireccion) {
			x -=v_movimiento;
		}
		else {
			x += v_movimiento;
		}
	}
	
	public boolean chocasteCon(Proyectil proyectil) {
		return (this.getX() > proyectil.getX() - proyectil.getRadio()*2) &&
				(this.getX() < proyectil.getX() + proyectil.getRadio()*2) &&
				(this.getY() < proyectil.getY() + proyectil.getRadio()*2) && 
				(this.getY()> proyectil.getY() - proyectil.getRadio()*2); 
		}
}
