package com.poortoys.examples;

import java.util.ArrayList;
import java.util.Scanner;

public class Usuario extends Persona {
	private String telefono;
	private String direccion;
	private int codigoPostal;
	private String correoElectronico;
	private String contraseña;
	private ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();

	private static Scanner us = new Scanner(System.in);

	// constructor vacio
	public Usuario() {
	}

	public Usuario(String nombre, String apellido1, String apellido2, int edad, String telefono, String direccion,
			int codigoPostal, String correoElectronico, String contraseña) {
		super(nombre, apellido1, apellido2, edad);
		this.telefono = telefono;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.correoElectronico = correoElectronico;
		this.contraseña = contraseña;
		this.listaReservas = listaReservas;
	}

	// DTO a partir de aqui, getters y setters de Usuario
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public ArrayList<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(ArrayList<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	// constructor copia
	public Usuario(Usuario u1) {
		this.setNombre(u1.getNombre());
		this.setApellido1(u1.getApellido1());
		this.setApellido2(u1.getApellido2());
		this.setEdad(u1.getEdad());
		this.setTelefono(u1.getTelefono());
		this.setDireccion(u1.getDireccion());
		this.setCodigoPostal(u1.getCodigoPostal());
		this.setCorreoElectronico(u1.getCorreoElectronico());
		this.setContraseña(u1.getContraseña());
	}

	@Override
	public String toString() {
		return super.toString() + "Usuario [telefono=" + telefono + ", direccion=" + direccion + ", codigoPostal="
				+ codigoPostal + ", correoElectronico=" + correoElectronico + ", listaReservas=" + listaReservas + "]"
				+ "la contraseña es privada";
	}

	// método que crea usuarios
	public static void crearUsuario(ArrayList<Persona> listaPersonas) {
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.solicitarDatosPersona();
		listaPersonas.add(nuevoUsuario);
		System.out.println("Nombre del usuario " + nuevoUsuario.getNombre());
	}

	// solicitar datos persona solicita los datos del usuario. Primeramente hace una
	// llamada a la clase super
	@Override
	public void solicitarDatosPersona() {
		super.solicitarDatosPersona();
		System.out.println("Introduce el telefono del usuario:");
		this.setTelefono(us.nextLine());
		System.out.println("Introduce la dirección : ");
		this.setDireccion(us.nextLine());
		System.out.println("Introduce el código postal:");
		this.setCodigoPostal(Integer.parseInt(us.nextLine()));
		System.out.println("Introduce el correo electronico :");
		this.setCorreoElectronico(us.nextLine());
	}

	public static void eliminarUsuario(ArrayList<Persona> listaPersonas) {
		System.out.println("Introduce el NIF del usuario que quieres dar de baja: ");
		String findName = us.nextLine();
		boolean nombreEncontrado = false;
		int p = 0;
		while (nombreEncontrado == false && p < listaPersonas.size()) {
			if (findName.equals(listaPersonas.get(p).getNombre())) {
				System.out.println("Encontrado el registro: " + listaPersonas.get(p).toString());
				System.out.println("Registro con nombre " + listaPersonas.get(p).getNombre() + " eliminado.");
				listaPersonas.remove(p);
				nombreEncontrado = true;
			}
			p++;
		}
		if (nombreEncontrado == false) {
			System.out.println("No se ha encontrado ningún nombre coincidente.");
		}
	}

	public static void inicializarUsuarios(ArrayList<Persona> listaPersonas) {
		Usuario u1 = new Usuario("Enrique", "Perez", "Allende", 30, "678545874", "calle pepito", 07141,
				"soyEnriquePerez@hotmail.com", "contraseña");
		System.out.println(u1.toString());
		Usuario u2 = new Usuario("Sara", "Perez", "Lende", 30, "678544585", "calle pepito", 07141,
				"soySaraPerez@hotmail.com", "contraseña");
		listaPersonas.add(u1);
		listaPersonas.add(u2);
	}

	@Override
	public void cambiarContraseña(ArrayList<Persona> listaPersonas) {
		System.out.println("Introduce el teléfono del usuario que vas a cambiar la contraseña:");
		String findPhone = us.nextLine();
		String oldPassword;
		String newPassword;
		boolean nombreEncontrado = false;
		int p = 0;
		while (nombreEncontrado == false && p < listaPersonas.size()) {
			if (listaPersonas.get(p) instanceof Usuario) {
				if (((Usuario) listaPersonas.get(p)).getTelefono().equals(findPhone)) {
					System.out.println("Encontrado el registro: " + listaPersonas.get(p).getNombre() + " con Telefono: "
							+ findPhone);
					System.out.println("Introduce la vieja contraseña: ");
					oldPassword = us.nextLine();
					if (((Usuario) listaPersonas.get(p)).getContraseña().equals(oldPassword)) {
						System.out.println("Contraseña correcta.");
						System.out.println("Introduce la nueva contraseña: ");
						newPassword = us.nextLine();
						((Usuario) listaPersonas.get(p)).setContraseña(newPassword);
						System.out.println("Registro con nombre " + listaPersonas.get(p).getNombre() + " modificado.");
						nombreEncontrado = true;
					}
				}
			}
			p++;
		}
		if (nombreEncontrado == false) {
			System.out.println("No se ha encontrado ningún telefono coincidente.");
		}
	}

	public static int logInUsuario(ArrayList<Persona> listaPersonas) {
		String findPhone;
		String email;
		int posicion = -1;
		boolean presente = false;
		System.out.println("Introduce el teléfono: ");
		findPhone = us.nextLine();
		System.out.println("Introduce el correo: ");
		email = us.nextLine();
		int p = 0;
		while (presente == false && p < listaPersonas.size()) {
			if (listaPersonas.get(p) instanceof Usuario) {
				if (((Usuario) listaPersonas.get(p)).getTelefono().equals(findPhone)
						&& ((Usuario) listaPersonas.get(p)).getCorreoElectronico().equals(email)) {
					System.out.println("	Acceso permitido    ");
					System.out.println("Bienvenido sr/sra " + ((Usuario) listaPersonas.get(p)).getNombre());
					posicion = p;
					presente = true;
				}
			}
			p++;
		}
		if (posicion == -1) {
			System.out.println("No se ha encontrado ningún telefono coincidente.");
		}
		return posicion;
	}

}
