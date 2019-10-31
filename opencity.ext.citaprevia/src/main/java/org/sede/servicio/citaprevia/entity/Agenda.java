package org.sede.servicio.citaprevia.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.anotaciones.HtmlContent;
import org.sede.core.anotaciones.PathId;
import org.sede.core.anotaciones.ValidHTML;
import org.sede.core.dao.EntidadBase;
import org.sede.core.utils.Funciones;
import org.sede.servicio.citaprevia.CitaPreviaController;


@XmlRootElement(name = "agenda")
@PathId("/" + CitaPreviaController.MAPPING)
public class Agenda extends EntidadBase {
	
	private Integer id;
	
	private String title;
	@ValidHTML @HtmlContent
	private String description;
	@ValidHTML @HtmlContent
	private String description_basic;
	private Integer tam_hueco;
	private Integer max_citas;
	private Integer id_estructura;
	private String title_estructura;
	private String address_estructura;

	public String getAddress_estructura() {
		return address_estructura;
	}
	public void setAddress_estructura(String address_estructura) {
		this.address_estructura = address_estructura;
	}
	private List<Horario> horarios;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title == null ? null : Funciones.removeHTMLEntity(title);
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public Integer getId_estructura() {
		return id_estructura;
	}
	public void setId_estructura(Integer id_estructura) {
		this.id_estructura = id_estructura;
	}
	public String getTitle_estructura() {
		return title_estructura;
	}
	public void setTitle_estructura(String title_estructura) {
		this.title_estructura = title_estructura;
	}
	
	public void setDescription(String descripcion) {
		if (descripcion.indexOf("</p>") == 0) {
			descripcion = descripcion.replaceFirst("</p>", "");
		}
		if (descripcion.lastIndexOf("<p>") == descripcion.length() - 3) {
			descripcion = descripcion.substring(0, descripcion.length() - 3);
		}
		this.description = descripcion;
	}
	
	
	public String getDescription_basic() {
		
		if (getDescription() != null) {
			
			return Funciones.htmlToTxt(getDescription());
		} else {
			return null;
		}
		
	}
	public void setDescription_basic(String description_basic) {
		this.description_basic = description_basic;
	}
	public Integer getTam_hueco() {
		return tam_hueco;
	}
	public void setTam_hueco(Integer tam_hueco) {
		this.tam_hueco = tam_hueco;
	}
	public Integer getMax_citas() {
		return max_citas;
	}
	public void setMax_citas(Integer max_citas) {
		this.max_citas = max_citas;
	}
	
	public List<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	
	public Agenda(int id, String nombre, String descripcion, int tam_hueco, int max_citas, int id_cen, String title_cen, String address_cen) {
		
		this.id = id;
		this.title = nombre;
		this.setDescription(descripcion);
		this.tam_hueco = tam_hueco;
		this.max_citas=max_citas;
		this.horarios=new ArrayList<Horario>();
		this.id_estructura=id_cen;
		this.title_estructura = title_cen;
		this.address_estructura = address_cen;
	}
	@Override
	public String toString() {
		return "Agenda [id=" + id + ", title=" + title + ", description="
				+ description + ", description_basic=" + description_basic
				+ ", tam_hueco=" + tam_hueco + ", max_citas=" + max_citas
				+ ", id_estructura=" + id_estructura + ", title_estructura="
				+ title_estructura + ", horarios=" + horarios + "]";
	}
	
	
}
