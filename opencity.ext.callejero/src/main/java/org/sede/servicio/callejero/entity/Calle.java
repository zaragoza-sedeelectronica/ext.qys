package org.sede.servicio.callejero.entity;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.sede.core.anotaciones.Grafo;
import org.sede.core.anotaciones.InList;
import org.sede.core.anotaciones.IsUri;
import org.sede.core.anotaciones.PathId;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.anotaciones.Rdf;
import org.sede.core.anotaciones.RdfMultiple;
import org.sede.core.anotaciones.RequiredSinValidacion;
import org.sede.core.anotaciones.Context;
import org.sede.core.dao.EntidadBase;
import org.sede.core.utils.Funciones;
import org.sede.servicio.callejero.CallejeroController;
import org.sede.servicio.callejero.ConfigCallejero;

@XmlRootElement(name = "via")
@Entity
@DynamicUpdate
@Table(name = "CALLE", schema = ConfigCallejero.ESQUEMA)
@XmlAccessorType(XmlAccessType.FIELD)
@PathId("/" + CallejeroController.MAPPING)
@Rdf(uri = "/" + "datosabiertos/def" + "/urbanismo-infraestructuras/callejero#Via", prefijo = "via")
@Grafo("http://www.zaragoza.es/urbanismo-infraestructuras/callejero/")
@BatchSize(size=50)
public class Calle extends EntidadBase implements java.io.Serializable {

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
	
	
	@ManyToOne
	@JoinColumn(name = "TIPO_VIA")
	@BatchSize(size=50)
	@Access(AccessType.FIELD)
	@Rdf(contexto = Context.ESCJR, propiedad = "tipoVia")
	private TipoVia tipo;
	
	
	@Column(name = "GCZ_PUBLICADO") @Size(max = 1) @Permisos(Permisos.PUB) @InList({"S", "N"})
	private String visible;

	@Temporal(TemporalType.TIMESTAMP) @Column(name = "GCZ_FECHAALTA") @Permisos(Permisos.DET)
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP) @Column(name = "GCZ_FECHAMOD") 
	@Rdf(contexto = Context.DCT, propiedad = "modified")
	private Date lastUpdated;

	@Temporal(TemporalType.TIMESTAMP) @Column(name = "GCZ_FECHAPUB")
	private Date pubDate;

	@Column(name = "GCZ_USUARIOALTA") @Size(max = 100) @Permisos(Permisos.DET)
	private String usuarioAlta;

	@Column(name = "GCZ_USUARIOMOD") @Size(max = 100) @Permisos(Permisos.DET)
	private String usuarioMod;

	@Column(name = "GCZ_USUARIOPUB") @Size(max = 100) @Permisos(Permisos.DET)
	private String usuarioPub;
	
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

	public TipoVia getTipo() {
		return tipo;
	}

	public void setTipo(TipoVia tipo) {
		this.tipo = tipo;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public String getUsuarioMod() {
		return usuarioMod;
	}

	public void setUsuarioMod(String usuarioMod) {
		this.usuarioMod = usuarioMod;
	}

	public String getUsuarioPub() {
		return usuarioPub;
	}

	public void setUsuarioPub(String usuarioPub) {
		this.usuarioPub = usuarioPub;
	}

	@Override
	public String toString() {
		return "Calle [id=" + id + ", title=" + title + ", title_ext="
				+ title_ext + ", tipo=" + tipo + ", visible=" + visible
				+ ", creationDate=" + creationDate + ", lastUpdated="
				+ lastUpdated + ", pubDate=" + pubDate + ", usuarioAlta="
				+ usuarioAlta + ", usuarioMod=" + usuarioMod + ", usuarioPub="
				+ usuarioPub + "]";
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
