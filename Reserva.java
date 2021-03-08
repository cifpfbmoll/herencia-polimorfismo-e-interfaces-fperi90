package com.poortoys.examples;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Reserva implements Material {
	// inicialiazamos los atributos de reserva
	private Libro LibroReserva = new Libro();
	private String FechaReserva = new String();
	private String HoraReserva = new String();
	private String FechaDevolucion = new String();
	private Usuario usuario = new Usuario();

	// constructor vacio para reserva
	public Reserva() {
	}

	// constructor con parámetros
	public Reserva(Libro libroReserva, String fechaReserva, String horaReserva, String FechaDevolucion) {
		this.LibroReserva = libroReserva;
		this.FechaReserva = fechaReserva;
		this.HoraReserva = horaReserva;
		this.FechaDevolucion = FechaDevolucion;
	}

	// constructor copia para reserva
	public Reserva(Reserva r1) {
		this.setLibroReserva(r1.getLibroReserva());
		this.setFechaReserva(r1.getFechaReserva());
		this.setHoraReserva(r1.getHoraReserva());
		this.setFechaDevolucion(r1.getFechaDevolucion());
	}

	// DTO a partir de aqui, gettes y setters
	public Libro getLibroReserva() {
		return LibroReserva;
	}

	public void setLibroReserva(Libro libroReserva) {
		this.LibroReserva = libroReserva;
	}

	public String getFechaReserva() {
		return FechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {

		this.FechaReserva = fechaReserva;
	}

	public String getHoraReserva() {
		return HoraReserva;
	}

	public void setHoraReserva(String horaReserva) {
		HoraReserva = horaReserva;
	}

	public String getFechaDevolucion() {
		return FechaDevolucion;
	}

	public void setFechaDevolucion(String fechaDevolucion) {
		FechaDevolucion = fechaDevolucion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Reserva [LibroReserva=" + LibroReserva + ", FechaReserva=" + FechaReserva + ", HoraReserva="
				+ HoraReserva + ", FechaDevolucion=" + FechaDevolucion + ", usuario=" + usuario + "]";
	}

	// método para añadir reserva, recibe tanto el array de personas como el
	// de libros, pues los va a utilizar los dos.
	public static void añadirReserva(ArrayList<Persona> listaPersonas, ArrayList<Libro> listaLibros) {
		int posicion = Usuario.logInUsuario(listaPersonas);
		if (posicion != -1) {
			int posicionLibro = Libro.buscarLibroIsbn(listaLibros);
			if (posicionLibro != -1) {
				if (listaLibros.get(posicionLibro).getNum_copias_disponibles() > 0) {
					Reserva r1 = new Reserva();
					r1.setLibroReserva(listaLibros.get(posicionLibro));

					// todo este bloque es para señalar tanto la fecha y la hora con un formato
					// concreto.
					r1.setFechaReserva(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
					DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
					LocalDateTime now = LocalDateTime.now();
					r1.setHoraReserva(dateTimeFormatter.format(now));
					// aqui asignamos la fecha de devolucion del libro
					r1.asignarFechaDevolucion();
					System.out.println("Libro reservado con fecha: " + r1.getFechaReserva());
					System.out.println("A devolver :" + r1.getFechaDevolucion());
					((Usuario) listaPersonas.get(posicion)).getListaReservas().add(r1);
					listaLibros.get(posicionLibro)
							.setNum_copias_disponibles(listaLibros.get(posicionLibro).getNum_copias_disponibles() - 1);

				} else {
					System.out.println("Libro no disponible.");
				}
			}
		}
	}

	// método usado para devolver libro, recibe tanto el array de personas como el
	// de libros, pues los va a utilizar los dos.

	public static void devolverLibro(ArrayList<Persona> listaPersonas, ArrayList<Libro> listaLibros) {
		int posicion = Usuario.logInUsuario(listaPersonas);
		if (posicion != -1) {
			int posicionLibro = Libro.buscarLibroIsbn(listaLibros);
			if (posicionLibro != -1) {
				int t = 0;
				boolean encontrado = false;
				while (encontrado == false && t < ((Usuario) listaPersonas.get(posicion)).getListaReservas().size()) {
					if (((Usuario) listaPersonas.get(posicion)).getListaReservas().get(t).getLibroReserva()
							.equals(listaLibros.get(posicionLibro))) {
						((Usuario) listaPersonas.get(posicion)).getListaReservas().remove(t);
						System.out.println("Libro devuelto.");
						listaLibros.get(posicionLibro).setNum_copias_disponibles(
								listaLibros.get(posicionLibro).getNum_copias_disponibles() + 1);

					}
					t++;
				}
			}
		}
	}

	// método implementado desde la interfaz material por el cual conoceremos la
	// fecha de devolucion de la reserva
	// Para usar este método crearemos un objeto de tipo LocalDate, el cual hara la
	// función de hacer un setter en fecha de devolucion con un formato concreto.

	@Override
	public LocalDate asignarFechaDevolucion() {
		LocalDate devolucion = LocalDate.now().plusMonths(1);
		this.FechaDevolucion = devolucion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return devolucion;
	}

	// método implementado desde la interfaz material por el cual mostraremos la
	// información de la reserva
	@Override
	public void mostrarReservaChula() {
		System.out.println("                Detalles de la reserva                ");
		System.out.println("                ISBN: " + this.getLibroReserva().getISBN());
		System.out.println("                Título: " + this.getLibroReserva().getTitulo());
		System.out.println("                Fecha reserva: " + this.getFechaReserva() + " " + this.getHoraReserva());
		System.out.println("                Fecha devolucion: " + this.getFechaDevolucion());
		System.out.println("                Usuario que ha realizado la reserva: " + this.getUsuario().getNombre());
	}
}