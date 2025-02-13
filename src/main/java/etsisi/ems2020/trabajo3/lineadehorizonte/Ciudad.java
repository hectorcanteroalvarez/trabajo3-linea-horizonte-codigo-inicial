package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/*
 Clase fundamental.
 Sirve para hacer la lectura del fichero de entrada que contiene los datos de como
 están situados los edificios en el fichero de entrada. xi, xd, h, siendo. Siendo
 xi la coordenada en X origen del edificio iésimo, xd la coordenada final en X, y h la altura del edificio.
 */

public class Ciudad {

	private ArrayList<Edificio> conjuntoEdificios;

	private static LineaHorizonte salidaAux; /* variable global para guardar la linea horizonte de salida */

	/*
	 * Generamos una ciudad de manera aleatoria para hacer pruebas.
	 */
	public Ciudad() {

		conjuntoEdificios = new ArrayList<Edificio>();
			int n = 5;
		int xi;
		int y; 
		int xd;

		for (int i = 0; i < n; i++) {
			xi = (int) (Math.random() * 100);
			y = (int) (Math.random() * 100);
			xd = (int) (xi + (Math.random() * 100));
			this.addEdificio(new Edificio(xi, y, xd));
		}

		conjuntoEdificios = new ArrayList<Edificio>();
	}

	public Edificio getEdificio(int i) {
		return (Edificio) this.conjuntoEdificios.get(i);
	}

	public void addEdificio(Edificio e) {
		conjuntoEdificios.add(e);
	}

	public void removeEdificio(int i) {
		conjuntoEdificios.remove(i);
	}

	public int size() {
		return conjuntoEdificios.size();
	}

	public LineaHorizonte getLineaHorizonte() {
		int pi = 0; // pi y pd, representan los edificios de la izquierda y de la derecha.
		int pd = conjuntoEdificios.size() - 1;
		return crearLineaHorizonte(pi, pd);
	}

	public LineaHorizonte crearLineaHorizonte(int pi, int pd) {
		LineaHorizonte linea = new LineaHorizonte(); // LineaHorizonte de salida

		if (pi == pd) // Caso base, la ciudad solo tiene un edificio, el perfil es el de ese edificio.
		{
			linea = soloUnEdificio(pi);
		} else {
			int medio = (pi + pd) / 2; // Edificio mitad
			LineaHorizonte s1 = this.crearLineaHorizonte(pi, medio);
			LineaHorizonte s2 = this.crearLineaHorizonte(medio + 1, pd);
			linea = lineaHorizonteFussion(s1, s2);
		}
		return linea;
	}

	public LineaHorizonte soloUnEdificio(int pi) {
		Edificio edificio = new Edificio();
		LineaHorizonte linea = new LineaHorizonte(); // LineaHorizonte de salida

		edificio = this.getEdificio(pi); // Obtenemos el único edificio y lo guardo en b
		Punto p1 = new Punto(); // punto donde se guardara en su X la Xi del efificio y en su Y la altura del
		// edificio
		Punto p2 = new Punto(); // punto donde se guardara en su X la Xd del efificio y en su Y le pondremos el
		// valor 0

		p1.setX(edificio.getXi()); // En cada punto guardamos la coordenada X y la altura.
		p1.setY(edificio.getY()); // guardo la altura
		p2.setX(edificio.getXd());
		p2.setY(0); // como el edificio se compone de 3 variables, en la Y de p2 le añadiremos un 0

		linea.addPunto(p1); // Añado los puntos a la línea del horizonte
		linea.addPunto(p2);

		return linea;
	}

	/*
	 * Función encargada de fusionar los dos LineaHorizonte obtenidos por la técnica
	 * divide y vencerás. Es una función muy compleja ya que es la encargada de
	 * decidir si un edificio solapa a otro, si hay edificios contiguos, etc. y
	 * solucionar dichos problemas para que el LineaHorizonte calculado sea el
	 * correcto.
	 */
	public LineaHorizonte lineaHorizonteFussion(LineaHorizonte s1, LineaHorizonte s2) {
		// en esta variable guardaremos las alturas de los puntos anteriores, en
		// alturas[0] la del s1, en alturas[1] la del s2
		// y en alturas[2] guardaremos la previa del segmento anterior introducido
		int[] alturas = new int[3];
		alturas[0] = -1;
		alturas[1] = -1;
		alturas[2] = -1;

		LineaHorizonte[] fusion = new LineaHorizonte[2];
		fusion[0] = s1;
		fusion[1] = s2;

		// variable global LineaHorizonte de salida
		salidaAux = new LineaHorizonte();

		mostrarLineas(s1, s2);

		elementosRestantesAmbos(alturas, fusion);

		elementosRestantes1(fusion, alturas);

		elementosRestantes2(fusion, alturas);

		return salidaAux;
	}

	private void xMenor(int[] alturas, LineaHorizonte s1) {
		// Inicializamos la variable paux
		Punto paux = new Punto();
		int max;

		guardarPuntoX(paux, s1); // guardamos en paux esa X

		max = Math.max(obtenerPuntoY(s1), alturas[1]); // y hacemos que el maximo entre la Y del s1 y la altura
		fijaMax(paux, max); // alturas[2] del s2 sea la altura Y de paux

		if (obtenerY(paux) != alturas[2]) // si este maximo no es igual al del segmento anterior
		{
			addPuntoLinea(paux); // añadimos el punto al LineaHorizonte de salida
			alturas[2] = obtenerY(paux); // actualizamos alturas[2]
		}
		alturas[0] = obtenerPuntoY(s1); // actualizamos la altura alturas[0]
		actualizarLinea(s1); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es
								// valido)
	}

	private void xMayor(int[] alturas, LineaHorizonte s2) {
		// Inicializamos la variable paux
		Punto paux = new Punto();
		int max;

		guardarPuntoX(paux, s2); // guardamos en paux esa X
		max = Math.max(obtenerPuntoY(s2), alturas[0]); // y hacemos que el maximo entre la Y del s2 y la altura
		fijaMax(paux, max); // alturas[2] del s1 sea la altura Y de paux

		if (obtenerY(paux) != alturas[2]) // si este maximo no es igual al del segmento anterior
		{
			addPuntoLinea(paux); // añadimos el punto al LineaHorizonte de salida
			alturas[2] = obtenerY(paux); // actualizamos alturas[2]
		}
		alturas[1] = obtenerPuntoY(s2); // actualizamos la altura alturas[1]
		actualizarLinea(s2); // en cualquier caso eliminamos el punto de s2 (tanto si se añade como si no es
		// valido)
	}

	private void xIgual(int[] alturas, LineaHorizonte[] fusion) {
		if ((obtenerPuntoYLinea(fusion[0]) > obtenerPuntoYLinea(fusion[1])) 
				&& (obtenerPuntoYLinea(fusion[0]) != alturas[2])) // guardaremos aquel punto que tenga la altura mas alta
		{
			addPuntoLinea(obtenerPunto(fusion[0]));
			alturas[2] = obtenerPuntoYLinea(fusion[0]);
		}
		if ((obtenerPuntoYLinea(fusion[0]) <= obtenerPuntoYLinea(fusion[1]))
				&& (obtenerPuntoYLinea(fusion[1]) != alturas[2])) {
			addPuntoLinea(obtenerPunto(fusion[1]));
			alturas[2] = obtenerPuntoYLinea(fusion[1]);
		}
		alturas[0] = obtenerPuntoYLinea(fusion[0]); // actualizamos la alturas[0] e alturas[1]
		alturas[1] = obtenerPuntoYLinea(fusion[1]);
		actualizarLinea(fusion[0]); // eliminamos el punto del s1 y del s2
		actualizarLinea(fusion[1]);
	}

	private void elementosRestantes1(LineaHorizonte[] fusion, int[] alturas) {

		while (!comprobarVacio(fusion[0])) // si aun nos quedan elementos en el s1
		{
			Punto paux = obtenerPunto(fusion[0]); // guardamos en paux el primer punto

			if (obtenerY(paux) != alturas[2]) // si paux no tiene la misma altura del segmento alturas[2]io
			{
				addPuntoLinea(paux); // lo añadimos al LineaHorizonte de salida
				alturas[2] = obtenerY(paux); // y actualizamos alturas[2]
			}
			actualizarLinea(fusion[0]); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es
			// valido)
		}
	}

	private void elementosRestantes2(LineaHorizonte[] fusion, int[] alturas) {
		while (!comprobarVacio(fusion[1])) // si aun nos quedan elementos en el s2
		{
			Punto paux = obtenerPunto(fusion[1]); // guardamos en paux el primer punto

			if (obtenerY(paux) != alturas[2]) // si paux no tiene la misma altura del segmento alturas[2]io
			{
				addPuntoLinea(paux); // lo añadimos al LineaHorizonte de salida
				alturas[2] = obtenerY(paux); // y actualizamos alturas[2]
			}
			actualizarLinea(fusion[1]); // en cualquier caso eliminamos el punto de s2 (tanto si se añade como si no es
			// valido)
		}
	}

	private void elementosRestantesAmbos(int[] alturas, LineaHorizonte[] fusion) {
		// Mientras tengamos elementos en s1 y en s2
		while ((!comprobarVacio(fusion[0])) && (!comprobarVacio(fusion[1]))) {
			// si X del s1 es menor que la X del s2
			if (obtenerPuntoX(fusion[0]) < obtenerPuntoX(fusion[1])) {
				xMenor(alturas, fusion[0]);
			}
			// si X del s1 es mayor que la X del s2
			else if (obtenerPuntoX(fusion[0]) > obtenerPuntoX(fusion[1])) {
				xMayor(alturas, fusion[1]);
			} else // si la X del s1 es igual a la X del s2
			{
				xIgual(alturas, fusion);
			}
		}
	}

	private void mostrarLineas(LineaHorizonte s1, LineaHorizonte s2) {
		System.out.println("==== S1 ====");
		s1.imprimir();
		System.out.println("==== S2 ====");
		s2.imprimir();
		System.out.println("\n");
	}

	/*
	 * Método que carga los edificios que me pasan en el archivo cuyo nombre se
	 * encuentra en "fichero". El formato del fichero nos lo ha dado el profesor en
	 * la clase del 9/3/2020, pocos días antes del estado de alarma.
	 */
	public void cargarEdificios(String fichero) {
		try {
			Scanner sr = new Scanner(new File(fichero));

			while (sr.hasNext()) {
				leerFichero(sr);
			}
		} catch (Exception e) {
			System.out.println("Error al cargar edificios");
		}
	}

	public void leerFichero(Scanner sr) {

		int xi; 
		int y;
		int xd;

		xi = sr.nextInt();
		xd = sr.nextInt();
		y = sr.nextInt();

		Edificio salida = new Edificio(xi, y, xd);
		this.addEdificio(salida);
	}

	public void fijaMax(Punto paux, int max) {
		paux.setY(max);
	}

	public int obtenerPuntoYLinea(LineaHorizonte linea) {
		return obtenerPuntoY(linea);
	}

	public boolean comprobarVacio(LineaHorizonte linea) {
		return linea.isEmpty();
	}

	public int obtenerPuntoX(LineaHorizonte linea) {
		return linea.getPuntoX(0);
	}

	public Punto obtenerPunto(LineaHorizonte linea) {
		return linea.getPunto(0);
	}

	public void guardarPuntoX(Punto point, LineaHorizonte linea) {
		point.setX(obtenerPuntoX(linea));
	}

	public int obtenerY(Punto paux) {
		return paux.getY();
	}

	public void addPuntoLinea(Punto paux) {
		salidaAux.addPunto(paux);
	}

	public void actualizarLinea(LineaHorizonte fusion) {
		fusion.borrarPunto(0);
	}

	public int obtenerPuntoY(LineaHorizonte linea) {
		return linea.getPuntoY(0);
	}
}
