package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LineaHorizonte {

	private ArrayList<Punto> lineaHorizonte;

	/*
	 * Constructor sin par�metros
	 */
	public LineaHorizonte() {
		lineaHorizonte = new ArrayList<Punto>();
	}

	/*
	 * m�todo que devuelve un objeto de la clase Punto
	 */
	public Punto getPunto(int i) {
		return this.lineaHorizonte.get(i);
	}

	public int getPuntoX(int i) {
		return this.getPunto(i).getX();
	}

	public int getPuntoY(int i) {
		return this.getPunto(i).getY();
	}

	// A�ado un punto a la l�nea del horizonte
	public void addPunto(Punto p) {
		lineaHorizonte.add(p);
	}

	// m�todo que borra un punto de la l�nea del horizonte
	public void borrarPunto(int i) {
		lineaHorizonte.remove(i);
	}

	public int size() {
		return lineaHorizonte.size();
	}

	// m�todo que me dice si la l�nea del horizonte est� o no vac�a
	public boolean isEmpty() {
		return lineaHorizonte.isEmpty();
	}

	/*
	 * M�todo al que le pasamos una serie de par�metros para poder guardar la linea
	 * del horizonte resultante despu�s de haber resuelto el ejercicio mediante la
	 * t�cnica de divide y vencer�s.
	 */
	public void guardaLineaHorizonte(String fichero) {
		try {
			Punto p = new Punto();
			FileWriter fileWriter = new FileWriter(fichero);
			PrintWriter out = new PrintWriter(fileWriter);

			for (int i = 0; i < this.size(); i++) {
				p = getPunto(i);
				mostrar(out, p);
			}
			out.close();
		} catch (Exception e) {
			System.out.println("Error al guardar linea horizonte");
		}
	}

	public void mostrar(PrintWriter muestra, Punto p) {

		muestra.print(p.getX());
		muestra.print(" ");
		muestra.print(p.getY());
		muestra.println();
	}

	public void imprimir() {

		for (int i = 0; i < lineaHorizonte.size(); i++) {
			System.out.println(cadena(i));
		}
	}

	public String cadena(int i) {
		Punto p = lineaHorizonte.get(i);
		return p.toString();
	}
}