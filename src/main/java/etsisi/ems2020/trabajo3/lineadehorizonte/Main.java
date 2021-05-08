package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {
		/*
		 Empezamos a ejecutar el c�digo para intentar hacer el ejercicio
		 que nos piden, calcular la l�nea del horizonte de una ciudad.
		 */
        /*Ciudad c = new Ciudad();
        c.cargarEdificios("ciudad1.txt");
        
        // Creamos l�nea del horizonte
        LineaHorizonte linea = new LineaHorizonte();
        linea = c.getLineaHorizonte();
        //Guardamos la linea del horizonte
        
        linea.guardaLineaHorizonte("salida.txt");
        System.out.println("-- Proceso finalizado Correctamente --");
        Punto  p2 = new Punto(5,6);
        System.out.println(p2);*/
		
	
		Ciudad c;
		
		c = new Ciudad();
		Edificio e1 = new Edificio(1,4,3);
		c.addEdificio(e1);
		Edificio e2 = new Edificio(2,7,9);
		c.addEdificio(e2);
		Edificio e3 = new Edificio(4,4,12);
		c.addEdificio(e3);	
		Edificio e4 = new Edificio(6,9,8);
		c.addEdificio(e4);
		Edificio e5 = new Edificio(11,6,13);
		c.addEdificio(e5);
		Edificio e6 = new Edificio(14,2,15);
		c.addEdificio(e6);		

		
		c.getLineaHorizonte();
	}

}
