package com.poortoys.examples;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	public static Scanner lectorMain = new Scanner(System.in);
	// estos objetos son creados para poder acceder a los metodos de instancia.
	public static Bibliotecario bibliotecario = new Bibliotecario();
	public static Usuario usuario = new Usuario();
	public static Biblioteca biblioteca = new Biblioteca();

	public static void main(String[] args) {
		iniciar();
		int opcion = 0;
		while (opcion != 3) {
			System.out.println("Bienvenido a version Biblioteca 2.0");
			System.out.println("¿A que menú quieres acceder?");
			System.out.println("1 : Menu bibliotecario: ");
			System.out.println("2 : Menu usuarios");
			System.out.println("3 : Salir");
			opcion = Integer.parseInt(lectorMain.nextLine());
			switch (opcion) {
			case 1:
				gestionBibliotecario();
				break;
			case 2:
				mostrarMenuUsuarios();
				break;
			case 3:
				// esto se puede dejar vacio dado que el while una vez que reciba el valor 3
				// saldra del bucle
				break;
			}
		}
	}

	private static void mostrarMenuUsuarios() {
		System.out.println("Bienvenido a version Biblioteca 2.0");
		System.out.println("¿Que quieres hacer?");
		System.out.println("1 : Mostrar libros disponibles.");
		System.out.println("2 : Cambiar mi contraseña");
		System.out.println("3 : Reservar un libro.");
		
	}

	// este metodo se utiliza para inicializar la biblioteca con libros y personas
	// por defecto para pdoer ir haciendo pruebas con ellos.
	public static void iniciar() {
		Libro.inicializarBiblioteca(biblioteca.getListaLibros());
		Usuario.inicializarUsuarios(biblioteca.getUsuariosBiblioteca());
		Bibliotecario.inicializarBibliotecarios(biblioteca.getUsuariosBiblioteca());
	}

	public static void gestionBibliotecario() {
		System.out.println("       Autentificación.     ");
		int posicion = Bibliotecario.logInBibliotecario(biblioteca.getUsuariosBiblioteca());
		if (posicion !=-1) {
			//asigno a bibliotecario(variable) el nombre del bibliotecario que ha iniciado sesión
			bibliotecario = ((Bibliotecario) biblioteca.getUsuariosBiblioteca().get(posicion));
			int opcion = 0;
			while (opcion != 3) {
				System.out.println("A que menu quieres acceder: ");
				System.out.println("1- gestionar biblioteca (todo relacionado con libros).");
				System.out.println("2- gestionar personas (todo relacionado con alta baja usuarios/bibliotecarios).");
				System.out.println("3- salir.");
				opcion = Integer.parseInt(lectorMain.nextLine());
				switch (opcion) {
				case 1:
					mostrarMenuLibros();
					break;
				case 2:
					mostrarMenuPersonas();
					break;
				case 3:
					//asi reseteamos el bibliotecario al salir de la alicación
					bibliotecario = new Bibliotecario();
					break;
				}
			}

		} else {
			System.out.println("Autentificación fallida.");
		}
	}

	public static void mostrarMenuLibros() {
		// tengo que instanciar un objeto del tipo biblioteca dentro del metodo para
		// despues pasarlo por parametro a los metodos de libro y que vaya añadiendo
		// una vez creado el método biblioteca, llamo a inicializar Biblioteca de la1
		// clase libro, para que le vaya añadiendo unos libros para poder ir trabajando
		// con ellos.
		int opcion = 0;
		while (opcion != 9) {
			System.out.println("		Menú biblioteca     ");
			System.out.println("	  ¿Que quieres hacer?");
			System.out.println("1 - Añadir libro ");
			System.out.println("2 - Eliminar libro ");
			System.out.println("3 - Buscar libro por ISBN");
			System.out.println("4 - Buscar libro por titulo");
			System.out.println("5 - Mostrar lista de libros actual");
			System.out.println("6 - Mostrar libros disponibles ");
			System.out.println("7 - Reservar libro para usuario. ");
			System.out.println("8 - Devolver libro prestado.");
			System.out.println("9 - Salir.");
			opcion = Integer.parseInt(lectorMain.nextLine());
			switch (opcion) {
			case 1:
				System.out.println("1 - Añadir libro ");
				Libro.añadirLibro(biblioteca.getListaLibros());
				break;
			case 2:
				System.out.println("2 - Eliminar libro ");
				Libro.eliminarLibro(biblioteca.getListaLibros());
				break;
			case 3:
				System.out.println("3 - Buscar libro por ISBN");
				Libro.buscarLibroIsbn(biblioteca.getListaLibros());
				break;
			case 4:
				System.out.println("4 - Buscar libro por titulo");
				Libro.buscarLibroTitulo(biblioteca.getListaLibros());
				break;
			case 5:
				System.out.println("5 - Mostrar lista de libros actual");
				biblioteca.mostrarLibros();
				break;
			case 6:
				System.out.println("6 - Mostrar libros disponibles ");
				biblioteca.mostrarLibrosDisponibles();
				break;
			case 7:
				System.out.println("7 - Reservar libro para usuario. ");
				Reserva.añadirReserva(biblioteca.getUsuariosBiblioteca(),biblioteca.getListaLibros());
				break;
			case 8:
				System.out.println("8 - Devolver libro prestado.");
				Reserva.devolverLibro(biblioteca.getUsuariosBiblioteca(),biblioteca.getListaLibros());
				break;
			case 9:
				// esto se puede dejar vacio dado que el while una vez que reciba el valor 3
				// saldra del bucle
				break;
			}
		}
	}

	public static void mostrarMenuPersonas() {
		int opcion = 0;
		while (opcion != 6) {

			System.out.println("	Menú bibliotecarios    ");
			System.out.println("	  ¿Que quieres hacer?");
			System.out.println("1 - Dar de alta bibliotecario. ");
			System.out.println("2 - Dar de alta usuario. ");
			System.out.println("3 - Modificar contraseña usuario.");
			System.out.println("4 - Dar de baja usuario (por Nombre).");
			System.out.println("5 - Mostrar todos los usuarios(usuarios y bibliotecarios).");
			System.out.println("6 - Salir.");
			opcion = Integer.parseInt(lectorMain.nextLine());
			switch (opcion) {
			case 1:
				Bibliotecario.crearBibliotecario(biblioteca.getUsuariosBiblioteca());
				break;
			case 2:
				Usuario.crearUsuario(biblioteca.getUsuariosBiblioteca());
				break;
			case 3:
				modificarContraseña();
				break;
			case 4:
				Usuario.crearUsuario(biblioteca.getUsuariosBiblioteca());
				break;
			case 5:
				mostrarPersonas();
				break;
			case 6:
				// esto se puede dejar vacio dado que el while una vez que reciba el valor 3
				// saldra del bucle
				break;
			}
		}

	}

	public static void mostrarPersonas() {
		System.out.println(biblioteca.getUsuariosBiblioteca().toString());
	}

	public static void modificarContraseña() {
		int opcion = 0;
		while (opcion != 3) {
			System.out.println("De quien quieres modificar la contraseña?");
			System.out.println("1 - Bibliotecario.");
			System.out.println("2 - Usuario.");
			System.out.println("3 - Salir");
			opcion = Integer.parseInt(lectorMain.nextLine());
			switch (opcion) {
			case 1:
				bibliotecario.cambiarContraseña(biblioteca.getUsuariosBiblioteca());
				break;
			case 2:
				usuario.cambiarContraseña(biblioteca.getUsuariosBiblioteca());
				break;
			case 3:
				// esto se puede dejar vacio dado que el while una vez que reciba el valor 3
				// saldra del bucle
				break;
			}
		}

	}

}
