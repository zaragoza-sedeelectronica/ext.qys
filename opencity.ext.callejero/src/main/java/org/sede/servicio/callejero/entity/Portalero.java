package org.sede.servicio.callejero.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.SafeHtml;
import org.sede.core.anotaciones.GeoJson;
import org.sede.core.anotaciones.Grafo;
import org.sede.core.anotaciones.InList;
import org.sede.core.anotaciones.Interno;
import org.sede.core.anotaciones.IsUri;
import org.sede.core.anotaciones.PathId;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.anotaciones.Rdf;
import org.sede.core.anotaciones.RdfMultiple;
import org.sede.core.anotaciones.RequiredSinValidacion;
import org.sede.core.geo.Geometria;
import org.sede.core.geo.Punto;
import org.sede.core.rest.CheckeoParametros;
import org.sede.core.utils.Funciones;
import org.sede.core.anotaciones.Context;
import org.sede.core.dao.EntidadBase;
import org.sede.servicio.callejero.ConfigCallejero;
import org.sede.servicio.callejero.PortaleroController;

@XmlRootElement(name = "portalero")
@Entity
@DynamicUpdate
@Table(name = "PORTALERO", schema = ConfigCallejero.ESQUEMA)
@GeoJson(title = "Portales", link = "", icon = "generico", description = "Portales de la ciudad de Zaragoza")
@XmlAccessorType(XmlAccessType.FIELD)
@PathId("/" + PortaleroController.MAPPING)
@Rdf(uri = "/" + "datosabiertos/def" + "/urbanismo-infraestructuras/callejero#Portal", prefijo = "portalero")
@Grafo("http://www.zaragoza.es/urbanismo-infraestructuras/callejero/")
@BatchSize(size=50)
public class Portalero extends EntidadBase implements java.io.Serializable {

	@Id
	@Column(name = "ID_POR", unique = true, nullable = false)
	@Digits(integer = 6, fraction = 0)
	@Rdf(contexto = Context.DCT, propiedad = "identifier")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "ID")
	@Access(AccessType.FIELD)
	@Rdf(contexto = Context.ESCJR, propiedad = "via")
	@RequiredSinValidacion
	private Calle calle; // cambiar nombre?
	
	@Column(name = "NUMERO", unique = true, nullable = false)
	@Digits(integer = 6, fraction = 0)
	private Integer numero;

	
	@ManyToOne
	@JoinColumn(name = "ID_JUN")
	@BatchSize(size=50)
	@Access(AccessType.FIELD)
	@Rdf(contexto = Context.ESADM, propiedad = "juntaAdministrativa")
	private Junta junta;

	//FIXME: Comentado temporalmente ya que la información no es correcta a nivel de bbdd
//	@ManyToOne
//	@JoinColumn(name = "ID_BAR")
//	@BatchSize(size=50)
//	@Access(AccessType.FIELD)
//	@Rdf(contexto = Context.ESADM, propiedad = "barrio")
//	private Barrio barrio;
	
	@Column(name = "ETIQUETA")
	@Size(max = 100)
	@SafeHtml
	private String etiqueta;
	
	@Column(name = "COD_POS")
	@Size(max = 5)
	@RdfMultiple({@Rdf(contexto = Context.SCHEMA, propiedad = "postalCode"), @Rdf(contexto = Context.VCARD, propiedad = "postal-code"), @Rdf(contexto = Context.LOCN, propiedad = "postCode")})
	private String codPos;
	
	@Interno
	@Column(name = "X")
//	@Digits(integer = 20, fraction = 20)
	private BigDecimal x;

	@Interno
	@Column(name = "Y")
//	@Digits(integer = 20, fraction = 20)
	private BigDecimal y;
	

	@Interno
	@Column(name = "XWGS84")
//	@Digits(integer = 20, fraction = 20)
	private BigDecimal lon;

	@Interno
	@Column(name = "YWGS84")
//	@Digits(integer = 20, fraction = 20)
	private BigDecimal lat;
	
	@Permisos(Permisos.PUB)
	@Column(name = "GCZ_PUBLICADO")
	@Size(max = 1)
	@InList({ "S", "N" })
	private String visible;

	@Permisos(Permisos.DET)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GCZ_FECHAALTA")
	private Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GCZ_FECHAMOD")
	@Rdf(contexto = Context.DCT, propiedad = "modified")
	private Date lastUpdated;
	
	@Permisos(Permisos.DET)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GCZ_FECHAPUB")
	private Date pubDate;
	
	@Permisos(Permisos.DET)
	@Column(name = "GCZ_USUARIOALTA")
	@Size(max = 100)
	private String usuarioAlta;

	@Permisos(Permisos.DET)
	@Column(name = "GCZ_USUARIOMOD")
	@Size(max = 100)
	private String usuarioMod;
	
	@Permisos(Permisos.DET)
	@Column(name = "GCZ_USUARIOPUB")
	@Size(max = 100)
	private String usuarioPub;
	
	@Transient
	@Rdf(contexto = Context.GEO, propiedad = "geometry")
	private Punto geometry;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Junta getJunta() {
		return junta;
	}

	public void setJunta(Junta junta) {
		this.junta = junta;
	}

//	public Barrio getBarrio() {
//		return barrio;
//	}
//
//	public void setBarrio(Barrio barrio) {
//		this.barrio = barrio;
//	}

	public Calle getCalle() {
		return calle;
	}

	public void setCalle(Calle calle) {
		this.calle = calle;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigDecimal getX() {
		return x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}
	
	
	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getCodPos() {
		return codPos;
	}

	public void setCodPos(String codPos) {
		this.codPos = codPos;
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

	public Punto getGeometry() {
		if (getX() != null) {
			geometry = new Punto(Punto.POINT, new Double[] {
					getX().doubleValue(), getY().doubleValue() });
		}
		return geometry;
	}

	public void setGeometry(Punto geometry) {
		if (geometry != null) {
			if (geometry.formatoWgs84()) {
				geometry.setCoordinates(Geometria.pasarAUTM30(
						geometry.getCoordinates()[0],
						geometry.getCoordinates()[1]));
				
			} else {
				double[] geo = Geometria.transform(geometry.getCoordinates()[0],
						geometry.getCoordinates()[1], CheckeoParametros.SRSUTM30N, CheckeoParametros.SRSWGS84);
				this.lon = BigDecimal.valueOf(geo[0]);
				this.lat = BigDecimal.valueOf(geo[1]);
			}
			
			this.x = new BigDecimal(geometry.getCoordinates()[0]);
			this.y = new BigDecimal(geometry.getCoordinates()[1]);
		}
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		return "Portalero [id=" + id + ", junta=" + junta
				//+ ", barrio=" + barrio
				+ ", calle=" + calle + ", numero=" + numero
				+ ", x=" + x + ", y=" + y + ", etiqueta=" + etiqueta
				+ ", codPos=" + codPos + ", visible=" + visible
				+ ", creationDate=" + creationDate + ", lastUpdated="
				+ lastUpdated + ", pubDate=" + pubDate + ", usuarioAlta="
				+ usuarioAlta + ", usuarioMod=" + usuarioMod + ", usuarioPub="
				+ usuarioPub + ", geometry=" + geometry + "]";
	}
	@Rdf(contexto = Context.OWL, propiedad = "sameAs")
    @Transient
    @IsUri
    private String sameAs;
    
	public String getSameAs() {
		return "http://www.zaragoza.es/api/recurso/urbanismo-infraestructuras/portalero/" + this.getId();
	}

	public void setSameAs(String sameAs) {
		this.sameAs = sameAs;
	}	
	@Transient
	public String getUri() {
		return Funciones.obtenerPath(this.getClass()) + getId();
	}	
}
