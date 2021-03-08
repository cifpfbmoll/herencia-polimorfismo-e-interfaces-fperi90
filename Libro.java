package com.poortoys.examples;

import java.util.ArrayList;
import java.util.Scanner;

public class Libro {
	private static int contadorLibro;
	private static int numCopias;
	public static Scanner lector = new Scanner(System.in);
	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private int numero_copias;
	private int num_copias_disponibles;

	// constructor vacio para Libro.
	public Libro() {
		contadorLibro++;
	}

	// constructor con parametros para Libro
	public Libro(String ISBN, String titulo, String autor, String editorial, int numero_copias,
			int num_copias_disponibles) {
		contadorLibro++;
		this.ISBN = ISBN;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.numero_copias = numero_copias;
		this.num_copias_disponibles = num_copias_disponibles;
	}

	// DTO gettes y setters para Libro
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getNumero_copias() {
		return numero_copias;
	}

	public void setNumero_copias(int numero_copias) {
		this.numero_copias = numero_copias;
	}

	public int getNum_copias_disponibles() {
		return num_copias_disponibles;
	}

	public void setNum_copias_disponibles(int num_copias_disponibles) {
		this.num_copias_disponibles = num_copias_disponibles;
	}

	// constructor tostring para Libro
	@Override
	public String toString() {
		return "Libro [ISBN=" + ISBN + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial
				+ ", numero_copias=" + numero_copias + ", num_copias_disponibles=" + num_copias_disponibles + "]";
	}

	// constructor copia para Libro
	public Libro(Libro l1) {
		contadorLibro++;
		this.setISBN(l1.getISBN());
		this.setTitulo(l1.getTitulo());
		this.setAutor(l1.getAutor());
		this.setEditorial(l1.getEditorial());
		this.setNumero_copias(l1.getNumero_copias());
		this.setNum_copias_disponibles(l1.getNum_copias_disponibles());
	}

	public static void añadirLibro(ArrayList<Libro> listaLibros) {
		Libro lbr = new Libro();
		numCopias = 0;
		System.out.println("Introduce el ISBN del libro: ");
		lbr.setISBN(lector.nextLine());
		System.out.println("Introduce el título del libro: ");
		lbr.setTitulo(lector.nextLine());
		System.out.println("Introduce el autor del libro: ");
		lbr.setAutor(lector.nextLine());
		System.out.println("Introduce la editorial del libro: ");
		lbr.setEditorial(lector.nextLine());
		System.out.println("Introduce el numero de copias: ");
		numCopias = Integer.parseInt(lector.nextLine());
		lbr.setNumero_copias(numCopias);
		lbr.setNum_copias_disponibles(numCopias);
		System.out.println(" Libro añadido a la biblioteca.");
		listaLibros.add(lbr);
	}

//			REPASAR ESTE METODO Y CAMBIARLO A WHILE
	public static void eliminarLibro(ArrayList<Libro> listaLibros) {
		System.out.println("Introduce el ISBN que quieres eliminar:");
		String sbn = lector.nextLine();
		boolean isbnEncontrado = false;
		int p = 0;
		while (isbnEncontrado == false && p < listaLibros.size()) {
			if (sbn.equals(listaLibros.get(p).getISBN())) {
				System.out.println("ISBN encontrado en la posición " + p);
				System.out.println("Título: " + listaLibros.get(p).getTitulo());
				System.out.println("Titulo con ISBN " + listaLibros.get(p).getISBN() + " eliminado.");
				listaLibros.remove(p);
				contadorLibro--;
				isbnEncontrado = true;
			}
			p++;
		}
		if (isbnEncontrado == false) {
			System.out.println("No se ha encontrado el ISBN señalado.");

		}
	}

	public static int buscarLibroIsbn(ArrayList<Libro> listaLibros) {
		int encontrado = -1;
		int p = 0;
		boolean isbnExiste = false;
		System.out.println("Introduce el ISBN : ");
		String sbn = lector.nextLine();
		while (isbnExiste == false && p <= listaLibros.size()) {
			if (p == listaLibros.size()) {
				System.out.println("Libro no encontrado");
			} else if (sbn.equals(listaLibros.get(p).getISBN())) {
				System.out.println("ISBN encontrado en la posicion : " + (p + 1));
				System.out.println(listaLibros.get(p).getTitulo());
				isbnExiste = true;
				encontrado = p;
			}
			p++;
		}
		return encontrado;
	}

	public static void buscarLibroTitulo(ArrayList<Libro> listaLibros) {
		// aqui instancio t como variable de control, que lanzara un aviso o otro en
		// caso de encontrar al menos un libro. En caso de no encontrar ningún libro,
		// lanzará el mensaje de libros no encontrados;
		System.out.println("Introduce la palabra o conjunto de letras que quieras bsucar: ");
		String str = lector.nextLine();
		int t = 0;
		for (int i = 0; i < listaLibros.size(); i++) {
			if (listaLibros.get(i).getTitulo().contains(str) == true && t == 0) {
				t++;
				System.out.println("Encontradas las siguientes conincidencias: ");
			}
			if (listaLibros.get(i).getTitulo().contains(str)) {
				System.out.println(listaLibros.get(i).getTitulo());
			}
		}
		if (t == 0) {
			System.out.println("No se han encontrado coincidencias.");
		}
	}

	public static void inicializarBiblioteca(ArrayList<Libro> listaLibros) {
		Libro l0 = new Libro("14521452", "Inés del alma mía", "Isabel Allende", "Santillana", 5, 5);
		Libro l2 = new Libro("14522563", "La mano de Fatima", "Ildefonso Falcones", "Circulo de lectores", 2, 2);
		Libro l3 = new Libro("14525236", "Un mundo sin fin", "Ken Follett", "Plaza Janés", 2, 2);
		Libro l4 = new Libro("14525896", "Los pilares de la tierra", "Ken Follett", "Plaza Janés", 3, 3);
		Libro l5 = new Libro("14528569", "La catedral del mar", "Ildefonso Falcones", "Circulo de lectores", 1, 1);
		Libro l6 = new Libro("14528547", "La sombra del viento", "Carlos Ruiz Zafón", "Planeta", 2, 2);
		Libro l7 = new Libro("14527458", "El juego del ángel", "Carlos Ruiz Zafón", "Planeta", 3, 3);
		Libro l8 = new Libro("14527412", "El prisionero del cielo", "Carlos Ruiz Zafón", "Planeta", 1, 1);
		listaLibros.add(l0);
		listaLibros.add(l2);
		listaLibros.add(l3);
		listaLibros.add(l4);
		listaLibros.add(l5);
		listaLibros.add(l6);
		listaLibros.add(l7);
		listaLibros.add(l8);
	}

}
