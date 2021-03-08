package com.poortoys.examples;

import java.util.ArrayList;
import java.util.Scanner;

public class Bibliotecario extends Persona {
	private String PuestoDeTrabajo;
	private String nif;
	private String Contraseña;
	private static Scanner bs = new Scanner(System.in);

	// constructor vacio
	public Bibliotecario() {
	}

	// Constructor con todos los parámetros , cinluidos los de la superclase
	public Bibliotecario(String nombre, String apellido1, String apellido2, int edad, String puestoDeTrabajo,
			String nif, String contraseña) {
		super(nombre, apellido1, apellido2, edad);
		this.PuestoDeTrabajo = puestoDeTrabajo;
		this.nif = nif;
		this.Contraseña = contraseña;
	}

	// constructor copia
	public Bibliotecario(Bibliotecario b1) {
		this.setNombre(b1.getNombre());
		this.setApellido1(b1.getApellido1());
		this.setApellido2(b1.getApellido2());
		this.setEdad(b1.getEdad());
		this.setPuestoDeTrabajo(b1.getPuestoDeTrabajo());
		this.setNif(b1.getNif());
		this.setContraseña(b1.getContraseña());
	}

	// DTO a paertir de aqui, getters y setters
	public String getPuestoDeTrabajo() {
		return PuestoDeTrabajo;
	}

	public void setPuestoDeTrabajo(String puestoDeTrabajo) {
		PuestoDeTrabajo = puestoDeTrabajo;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getContraseña() {
		return Contraseña;
	}

	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}

	// toString sin los campos de la superclase
	@Override
	public String toString() {
		return "Bibliotecario [PuestoDeTrabajo=" + PuestoDeTrabajo + ", nif=" + nif + ", Contraseña=" + Contraseña
				+ "]";
	}

	@Override
	public void solicitarDatosPersona() {
		super.solicitarDatosPersona();
		System.out.println("Introduce el puesto de trabajo:");
		this.setPuestoDeTrabajo(bs.nextLine());
		System.out.println("Introduce el nif :");
		this.setNif(bs.nextLine());
		System.out.println("Introduce la contraseña:");
		this.setContraseña(bs.nextLine());
	}

	public static void crearBibliotecario(ArrayList<Persona> listaPersonas) {
		Bibliotecario nuevoBibliotecario = new Bibliotecario();
		nuevoBibliotecario.solicitarDatosPersona();
		listaPersonas.add(nuevoBibliotecario);
		System.out.println("Nombre del usuario " + nuevoBibliotecario.getNombre());
	}

	public static void inicializarBibliotecarios(ArrayList<Persona> listaPersonas) {
		Bibliotecario b1 = new Bibliotecario("Franco", "Peri", "Bustos", 31, "Jefe de biblioteca", "x3378273F",
				"dnierroneo");
		listaPersonas.add(b1);
		System.out.println(b1.getNif() + b1.getContraseña());
	}

	public static int logInBibliotecario(ArrayList<Persona> listaPersonas) {
		String leeNif;
		String leeContraseña;
		int posicion = -1;
		boolean presente = false;
		System.out.println("Introduce el Nif: ");
		leeNif = bs.nextLine();
		System.out.println("Introduce la contraseña");
		leeContraseña = bs.nextLine();
		int p = 0;
		while (presente == false && p < listaPersonas.size()) {
			if (listaPersonas.get(p) instanceof Bibliotecario) {
				if (((Bibliotecario) listaPersonas.get(p)).getNif().equals(leeNif)
						&& ((Bibliotecario) listaPersonas.get(p)).getContraseña().equals(leeContraseña)) {
					System.out.println("	Acceso permitido    ");
					System.out.println("Bienvenido sr/sra " + ((Bibliotecario) listaPersonas.get(p)).getNombre());
					presente = true;
					posicion = p;
				}
			}
			p++;
		}
		System.out.println("el valor de presente es :" + presente);
		return posicion;
	}

	@Override
	public void cambiarContraseña(ArrayList<Persona> listaPersonas) {
		System.out.println("Introduce el NIF del bibliotecario que vas a cambiar la contraseña:");
		String findNif = bs.nextLine();
		String oldPassword;
		String newPassword;
		boolean nombreEncontrado = false;
		int p = 0;
		while (nombreEncontrado == false && p < listaPersonas.size()) {
			if (listaPersonas.get(p) instanceof Bibliotecario) {
				if (((Bibliotecario) listaPersonas.get(p)).getNif().equals(findNif)) {
					System.out.println(
							"Encontrado el registro: " + listaPersonas.get(p).getNombre() + " con NIF: " + findNif);
					System.out.println("Introduce la vieja contraseña: ");
					oldPassword = bs.nextLine();
					if (((Bibliotecario) listaPersonas.get(p)).getContraseña().equals(oldPassword)) {
						System.out.println("Contraseña correcta.");
						System.out.println("Introduce la nueva contraseña");
						newPassword = bs.nextLine();
						((Bibliotecario) listaPersonas.get(p)).setContraseña(newPassword);
						System.out.println("Registro con nombre " + listaPersonas.get(p).getNombre() + " modificado.");
						nombreEncontrado = true;
					}
				}
			}
			p++;
		}
		if (nombreEncontrado == false) {
			System.out.println("No se ha encontrado ningún NIF coincidente.");
		}
	}

}
