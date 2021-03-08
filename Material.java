package com.poortoys.examples;
import java.time.LocalDate;

// Interfaz material
public interface Material {

	//método por el cual obtenemos la fecha en la que se tiene que devolver el libro 
	public LocalDate asignarFechaDevolucion();
	//método para mostrar la reserva de una manera organizada y mas vistosa.
	public void mostrarReservaChula();
}
