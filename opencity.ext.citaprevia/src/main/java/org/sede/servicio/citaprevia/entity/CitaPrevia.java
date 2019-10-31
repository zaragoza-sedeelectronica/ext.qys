package org.sede.servicio.citaprevia.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.anotaciones.PathId;
import org.sede.core.utils.Funciones;
import org.sede.servicio.citaprevia.CitaPreviaController;

@XmlRootElement(name = "cita-previa")
@PathId("/" + CitaPreviaController.MAPPING)
public class CitaPrevia {
	@NotNull
	private Integer id;
	@NotNull
	private String title;
	@NotNull
	private String uri;

	private List<Agenda> agendas;

	public CitaPrevia() {
		super();
	}

	public CitaPrevia(int idCen, String nombre) {
		this.id = idCen;
		this.title = nombre;
	}

	public CitaPrevia(int idCen, String nombre, String uri) {
		
		this.id = idCen;
		this.title = nombre;
		this.uri = uri;
		this.agendas=new ArrayList<Agenda>();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idCen) {
		this.id = idCen;
	}

	public String getTitle() {
		return title == null ? null : Funciones.removeHTMLEntity(title);
	}

	public void setTitle(String nombre) {
		this.title = nombre;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public List<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	@Override
	public String toString() {
		return "CentroServicios [id=" + id + ", title="
				+ title + ", uri=" + uri + "]";
	}

}
