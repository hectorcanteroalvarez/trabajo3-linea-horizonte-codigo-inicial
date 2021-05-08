package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.util.Arrays;

public class LineaFusion {

	private Punto p1;
	private Punto p2;
	private Punto paux;
	private int [] alturas;
	private LineaHorizonte salida;
	private LineaHorizonte s1;
	private LineaHorizonte s2;
	

	public LineaFusion(Punto p1, Punto p2, Punto paux, int[] alturas, LineaHorizonte salida, LineaHorizonte s1,
			LineaHorizonte s2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.paux = paux;
		this.alturas = alturas;
		this.salida = salida;
		this.s1 = s1;
		this.s2 = s2;
	}
	public Punto getP1() {
		return p1;
	}
	public void setP1(Punto p1) {
		this.p1 = p1;
	}
	public Punto getP2() {
		return p2;
	}
	public void setP2(Punto p2) {
		this.p2 = p2;
	}
	public Punto getPaux() {
		return paux;
	}
	public void setPaux(Punto paux) {
		this.paux = paux;
	}
	public int[] getAlturas() {
		return alturas;
	}
	public void setAlturas(int[] alturas) {
		this.alturas = alturas;
	}
	public LineaHorizonte getSalida() {
		return salida;
	}
	public void setSalida(LineaHorizonte salida) {
		this.salida = salida;
	}
	public LineaHorizonte getS1() {
		return s1;
	}
	public void setS1(LineaHorizonte s1) {
		this.s1 = s1;
	}
	public LineaHorizonte getS2() {
		return s2;
	}
	public void setS2(LineaHorizonte s2) {
		this.s2 = s2;
	}
	
	public String toString() {
		return "LineaFusion [p1=" + p1 + ", p2=" + p2 + ", paux=" + paux + ", alturas=" + Arrays.toString(alturas)
				+ ", salida=" + salida + ", s1=" + s1 + ", s2=" + s2 + ", getP1()=" + getP1() + ", getP2()=" + getP2()
				+ ", getPaux()=" + getPaux() + ", getAlturas()=" + Arrays.toString(getAlturas()) + ", getSalida()="
				+ getSalida() + ", getS1()=" + getS1() + ", getS2()=" + getS2() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
