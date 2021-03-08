package com.poortoys.examples;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {

	public static Scanner lb = new Scanner(System.in);
	private String nombreBiblioteca;
	private ArrayList<Libro> listaLibros = new ArrayList<Libro>();
	private ArrayList<Persona> usuariosBiblioteca = new ArrayList<Persona>();

	public String getNombreBiblioteca() {
		return nombreBiblioteca;
	}

	// constructor vacío para biblioteca
	public Biblioteca() {

	}

	// constructor con parámetros para biblioteca
	public Biblioteca(String nombreBiblioteca, ArrayList<Libro> listaLibros, ArrayList<Persona> usuariosBiblioteca) {
		this.nombreBiblioteca = nombreBiblioteca;
		this.listaLibros = listaLibros;
		this.usuariosBiblioteca = usuariosBiblioteca;
	}

	// DTO getters y setteres para biblioteca
	public static Scanner getLb() {
		return lb;
	}

	public static void setLb(Scanner lb) {
		Biblioteca.lb = lb;
	}

	public ArrayList<Libro> getListaLibros() {
		return listaLibros;
	}

	public void setListaLibros(ArrayList<Libro> listaLibros) {
		this.listaLibros = listaLibros;
	}

	public ArrayList<Persona> getUsuariosBiblioteca() {
		return usuariosBiblioteca;
	}

	public void setUsuariosBiblioteca(ArrayList<Persona> usuariosBiblioteca) {
		this.usuariosBiblioteca = usuariosBiblioteca;
	}

	public void setNombreBiblioteca(String nombreBiblioteca) {
		this.nombreBiblioteca = nombreBiblioteca;
	}

	// método que muestra los libros disponibles en la biblioteca.
	public void mostrarLibros() {
		for (int i = 0; i < listaLibros.size(); i++) {
			System.out.println(listaLibros.get(i).toString());
		}
	}
	// método que muestra los libros disponibles
	public void mostrarLibrosDisponibles() {
		for (int i = 0; i < listaLibros.size(); i++) {
			if (listaLibros.get(i).getNum_copias_disponibles() >= 1) {
				System.out.println(listaLibros.get(i).toString());
			}
		}
	}


}