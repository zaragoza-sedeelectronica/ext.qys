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
import org.sede.servicio.callejero.ConfigCallejero;
import org.sede.core.anotaciones.Context;



@XmlRootElement(name = "property")
@Entity
@DynamicUpdate
@Table(name = "PROPIEDAD", schema = ConfigCallejero.ESQUEMA)
@XmlAccessorType(XmlAccessType.FIELD)
@PathId("/recurso/urbanismo-infraestructuras/callejero/mapeo-semantico/propiedad/")
@Rdf(contexto = Context.MAPEOSEM, propiedad = "Property")
public class PropiedadSemantica implements java.io.Serializable {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@Rdf(contexto = Context.DCT, propiedad = "identifier")
	private Integer id;
	
	@Column(name = "NOMBRE", nullable = false)
	@Size(max = 1000)
	@RdfMultiple({@Rdf(contexto = Context.RDFS, propiedad = "label"), @Rdf(contexto = Context.DCT, propiedad = "title"), @Rdf(contexto = Context.SCHEMA, propiedad = "name")})
	private String name;

	@Column(name = "URI", nullable = false)
	@Size(max = 1000)
	@Rdf(contexto = Context.MAPEOSEM, propiedad = "uri")
	private String uri;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "PropiedadSemantica [id=" + id + ", name=" + name + ", uri="
				+ uri + "]";
	}
	
	
	
}
