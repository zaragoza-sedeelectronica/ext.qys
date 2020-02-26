package org.sede.servicio.callejero.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;
import org.sede.core.anotaciones.PathId;
import org.sede.core.anotaciones.Rdf;
import org.sede.core.anotaciones.RdfMultiple;
import org.sede.core.anotaciones.Context;
import org.sede.core.dao.EntidadBase;
import org.sede.servicio.callejero.ConfigCallejero;

@XmlRootElement(name = "barrio")
@Entity
@DynamicUpdate
@Table(name = "BARRIO", schema = ConfigCallejero.ESQUEMA)
@XmlAccessorType(XmlAccessType.FIELD)
@PathId("/recurso/sector-publico/territorio/barrio")
@Rdf(uri = "/" + "datosabiertos/def" + "/sector-publico/territorio#Barrio/", prefijo = "barrio")
public class Barrio extends EntidadBase implements java.io.Serializable {

	@Id
	@Column(name = "ID_BARRIO", unique = true, nullable = false)
	@Rdf(contexto = Context.DCT, propiedad = "identifier")
	private Integer id;
	
	@Column(name = "NOMBRE", nullable = false)
	@Size(max = 255)
	@RdfMultiple({@Rdf(contexto = Context.GEONAMES, propiedad = "name"), @Rdf(contexto = Context.RDFS, propiedad = "label")})
	private String title;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Barrio [id=" + id + ", title=" + title + "]";
	}
	
}
