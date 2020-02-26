package org.sede.servicio.callejero.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.sede.core.anotaciones.Context;
import org.sede.core.anotaciones.IsUri;
import org.sede.core.anotaciones.PathId;
import org.sede.core.anotaciones.Rdf;
import org.sede.core.anotaciones.RdfMultiple;
import org.sede.core.anotaciones.RequiredSinValidacion;
import org.sede.core.anotaciones.SoloEnEstaEntidad;
import org.sede.core.dao.EntidadBase;
import org.sede.core.utils.Funciones;
import org.sede.servicio.callejero.CallejeroController;

@XmlRootElement(name = "via")
@Entity
@DynamicUpdate
@Table(name = "V02014_CALLES_WEB", schema = "comun")
@XmlAccessorType(XmlAccessType.FIELD)
@PathId("/" + CallejeroController.MAPPING)
//@Rdf(uri = "/" + "datosabiertos/def" + "/urbanismo-infraestructuras/callejero#Via", prefijo = "via")
//@Grafo("http://www.zaragoza.es/urbanismo-infraestructuras/callejero/")
@BatchSize(size=50)
public class CalleTerritorio extends EntidadBase implements java.io.Serializable {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@Rdf(contexto = Context.DCT, propiedad = "identifier")
	private Integer id;
	
	@Column(name = "NOMBRE", nullable = false)
	@Size(max = 260)
	@RequiredSinValidacion
	@RdfMultiple({@Rdf(contexto = Context.GEONAMES, propiedad = "name"), @Rdf(contexto = Context.RDFS, propiedad = "label")})
	private String title;

	@Column(name = "NOMBRE_COMPLETO", nullable = false)
	@Size(max = 260)
	private String title_ext;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID", referencedColumnName="ID")
	@Access(AccessType.FIELD)
	@SoloEnEstaEntidad
	private Fiscal fiscal;
	
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

	public String getTitle_ext() {
		return title_ext;
	}

	public void setTitle_ext(String title_ext) {
		this.title_ext = title_ext;
	}


	public Fiscal getFiscal() {
		return fiscal;
	}

	public void setFiscal(Fiscal fiscal) {
		this.fiscal = fiscal;
	}

	@Override
	public String toString() {
		return "CalleTerritorio [id=" + id + ", title=" + title + ", title_ext=" + title_ext + "]";
	}


	@Rdf(contexto = Context.OWL, propiedad = "sameAs")
    @Transient
    @IsUri
    private String sameAs;
    
	public String getSameAs() {
		return "http://www.zaragoza.es/api/recurso/urbanismo-infraestructuras/callejero/via/" + this.getId();
	}

	public void setSameAs(String sameAs) {
		this.sameAs = sameAs;
	}	
	@Transient
	public String getUri() {
		return Funciones.obtenerPath(this.getClass()) + getId();
	}
}
