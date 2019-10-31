package org.sede.servicio.citaprevia.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "horario")
public class Horario {

	private int id;
	private String title;
	private String start_date;
	private String end_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id_horario) {
		this.id = id_horario;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String nombre) {
		this.title = nombre;
	}
	
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String fechaInicio) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.parse(fechaInicio); 
		this.start_date = fechaInicio;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String fechaFin) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.parse(fechaFin);
		this.end_date = fechaFin;
	}
	
	public Horario(int idHorario, String nombre, String fechaInicio, String fechaFin) {
		this.id=idHorario;
		this.title=nombre;
		this.start_date=fechaInicio;
		this.end_date=fechaFin;
	}
}
