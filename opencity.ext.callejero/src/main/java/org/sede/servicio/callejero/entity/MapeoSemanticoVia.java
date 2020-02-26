package org.sede.servicio.callejero.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.sede.core.anotaciones.Context;
import org.sede.core.anotaciones.Grafo;
import org.sede.core.anotaciones.InList;
import org.sede.core.anotaciones.IsUri;
import org.sede.core.anotaciones.PathId;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.anotaciones.Rdf;
import org.sede.core.utils.Funciones;
import org.sede.servicio.callejero.CallejeroController;
import org.sede.servicio.callejero.ConfigCallejero;

@XmlRootElement(name = "property")
@Entity
@DynamicUpdate
@Table(name = "MAPEO_SEMANTICO_VIA", schema = ConfigCallejero.ESQUEMA)
@SequenceGenerator(name = "SECUENCIA_seq_mapeos", sequenceName = "seq_mapeos", allocationSize = 1)
@XmlAccessorType(XmlAccessType.FIELD)
@PathId("/" + CallejeroController.MAPPING_MAPEO_SEMANTICO) //TODO preparar controller
@Rdf(contexto = Context.MAPEOSEM, propiedad = "Mapping")
@Grafo("http://www.zaragoza.es/urbanismo-infraestructuras/callejero/")
public class MapeoSemanticoVia implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECUENCIA_seq_mapeos")
	@Column(name = "ID", unique = true, nullable = false)
	@Rdf(contexto = Context.DCT, propiedad = "identifier")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "ID_VIA")
	@BatchSize(size=50)
	@Access(AccessType.FIELD)
	@Rdf(contexto = Context.MAPEOSEM, propiedad = "origin")
	@NotNull
	private Calle linkedResource;
	
	@OneToOne
	@JoinColumn(name = "ID_PROPIEDAD")
	@BatchSize(size=50)
	@Access(AccessType.FIELD)
	@Rdf(contexto = Context.MAPEOSEM, propiedad = "property")
	@NotNull
	private PropiedadSemantica property;
	
	@Column(name = "ESTADO") @Size(max = 1) @Permisos(Permisos.PUB) @InList({"S", "N", "P", "Z"})
	private String status;
	
	@Permisos(Permisos.DET)
	@Column(name = "PUNTUACION", nullable = true, precision = 10, scale = 2)
	private BigDecimal score;

	
	@Column(name = "URI_RECURSO", nullable = false)
	@Size(max = 1000)
	@IsUri
	@Rdf(contexto = Context.MAPEOSEM, propiedad = "destination")
	@NotNull
	private String matchedResourceUri;
	
	@Column(name = "NOMBRE_RECURSO", nullable = false)
	@Size(max = 1000)
	@NotNull
	private String matchedResourceName;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_ALTA")
	@Rdf(contexto = Context.DCT, propiedad = "created")
	private Date created;
	
	@Permisos(Permisos.DET)
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_MOD_PUNTUACION")
	private Date scoreUpdated;
	
	@Permisos(Permisos.DET)
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_MOD_ESTADO")
	private Date statusUpdated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calle getLinkedResource() {
		return linkedResource;
	}

	public void setLinkedResource(Calle linkedResource) {
		this.linkedResource = linkedResource;
	}

	public PropiedadSemantica getProperty() {
		return property;
	}

	public void setProperty(PropiedadSemantica property) {
		this.property = property;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getMatchedResourceUri() {
		return matchedResourceUri;
	}

	public void setMatchedResourceUri(String matchedResourceUri) {
		this.matchedResourceUri = matchedResourceUri;
	}

	public String getMatchedResourceName() {
		return matchedResourceName;
	}

	public void setMatchedResourceName(String matchedResourceName) {
		this.matchedResourceName = matchedResourceName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getScoreUpdated() {
		return scoreUpdated;
	}

	public void setScoreUpdated(Date scoreUpdated) {
		this.scoreUpdated = scoreUpdated;
	}

	public Date getStatusUpdated() {
		return statusUpdated;
	}

	public void setStatusUpdated(Date statusUpdated) {
		this.statusUpdated = statusUpdated;
	}

	@Override
	public String toString() {
		return "MapeoSemanticoVia [id=" + id + ", linkedResource="
				+ linkedResource + ", property=" + property + ", status="
				+ status + ", score=" + score + ", matchedResourceUri="
				+ matchedResourceUri + ", matchedResourceName="
				+ matchedResourceName + ", created=" + created
				+ ", scoreUpdated=" + scoreUpdated + ", statusUpdated="
				+ statusUpdated + "]";
	}
	
	@Rdf(contexto = Context.OWL, propiedad = "sameAs")
    @Transient
    @IsUri
    private String sameAs;
    
	public String getSameAs() {
		return "http://www.zaragoza.es/api/recurso/urbanismo-infraestructuras/mapeo-calle/" + this.getId();
	}

	public void setSameAs(String sameAs) {
		this.sameAs = sameAs;
	}	
	@Transient
	public String getUri() {
		return Funciones.obtenerPath(this.getClass()) + getId();
	}
	
}
