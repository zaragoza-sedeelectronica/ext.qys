package org.sede.servicio.citaprevia.entity;


import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "turno")
@Entity
public class Turno {
	
	private String hora;
	
	private int max_citas;
	
	private int libres;

	public Turno() {
		super();
	}
	public String getHora() {
		return hora;
	}
	
	public void setHora(String horaInicio) {
		this.hora = horaInicio;
	}
	
	public int getLibres() {
		return libres;
	}

	public void setLibres(int libres) {
		this.libres = libres;
	}
	
	public int getMax_citas() {
		return max_citas;
	}
	
	public void setMax_citas(int max_citas) {
		this.max_citas = max_citas;
	}
	
	public Turno(String horaInicio, int libres,int max_citas) {
		this.hora=horaInicio;
		this.libres=libres;
		this.max_citas=max_citas;
	}
	
	public Turno(String horaInicio) {
		this.hora=horaInicio;
	}

	@Override
	public String toString() {
		return "Turno [hora=" + hora + ", max_citas=" + max_citas + ", libres="
				+ libres + "]";
	}
	
}
