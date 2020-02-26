package org.sede.servicio.callejero.entity;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.sql.rowset.serial.SerialClob;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.sede.core.anotaciones.Context;
import org.sede.core.anotaciones.GeoJson;
import org.sede.core.anotaciones.HtmlContent;
import org.sede.core.anotaciones.Interno;
import org.sede.core.anotaciones.IsPropiedadSemanticaCentro;
import org.sede.core.anotaciones.PathId;
import org.sede.core.anotaciones.Rdf;
import org.sede.core.anotaciones.RdfMultiple;
import org.sede.core.anotaciones.SoloEnEstaEntidad;
import org.sede.core.anotaciones.ValidHTML;
import org.sede.core.dao.EntidadBase;
import org.sede.core.geo.Polygon;
import org.sede.core.rest.json.JSONArray;
import org.sede.core.utils.Funciones;
import org.sede.servicio.callejero.ConfigCallejero;

@XmlRootElement(name = "junta")
@Entity(name="junta-callejero")
@DynamicUpdate
@Table(name = "JUNTA", schema = ConfigCallejero.ESQUEMA)
@XmlAccessorType(XmlAccessType.FIELD)
@PathId("/servicio/distrito")
@GeoJson(title = "Juntas", link = "/ciudadania/gobierno-abierto/espacios/juntas/", icon = "participacion", description = "Juntas")
@Rdf(contexto = Context.ZAR, propiedad = "JuntaAdministrativa", prefijo = "junta")
public class Junta extends EntidadBase implements java.io.Serializable {

	@Id
	@Column(name = "ID_JUNTA", unique = true, nullable = false)
	@Rdf(contexto = Context.DCT, propiedad = "identifier")
	private Integer id;

	@Column(name = "NOMBRE", nullable = false)
	@Size(max = 50)
	@RdfMultiple({ @Rdf(contexto = Context.GEONAMES, propiedad = "name"),
			@Rdf(contexto = Context.RDFS, propiedad = "label") })
	private String title;

	@Column(name = "DESCRIPCION")
	@Size(max=4000)
	@ValidHTML @HtmlContent
	@RdfMultiple({@Rdf(contexto = Context.SCHEMA, propiedad = "description"), @Rdf(contexto = Context.RDFS, propiedad = "comment"), @Rdf(contexto = Context.DCT, propiedad = "description")})
	private String description;

	@Interno
	@Column(name = "ID_CEN", unique = true, nullable = false)
	private Integer idCen;

	@Interno
	@Column(name = "ID_MAPA", unique = true, nullable = false)
	private Integer idMapa;

	@Column(name = "GEOMETRIA")
	@Interno
	private java.sql.Clob geometria;

	@Transient
	@SoloEnEstaEntidad
	private Polygon geometry;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIdCen() {
		return idCen;
	}

	public void setIdCen(Integer idCen) {
		this.idCen = idCen;
	}


	public Integer getIdMapa() {
		return idMapa;
	}

	public void setIdMapa(Integer idMapa) {
		this.idMapa = idMapa;
	}

    public String getGeometria() throws IOException, SQLException {
		return convertClobToString(geometria);
	}

	public void setGeometria(String geometria) throws SQLException {
		this.geometria = new SerialClob(geometria.toCharArray());
	}

	public Polygon getGeometry() {
		try {
			String geo = getGeometria();
			if (geo != null) {
				JSONArray jsonArray = new JSONArray(geo);
				Double[][] coords = null;
				if (jsonArray != null) {
					int len = jsonArray.length();
					coords = new Double[len][2];
					for (int i = 0; i < len; i++) {
						JSONArray arr = jsonArray.getJSONArray(i);
						coords[i] = new Double[] {
								arr.getDouble(0),
								arr.getDouble(1) };
					}
				}
				this.geometry = new Polygon();
				geometry.setCoordinates(coords);
				return geometry;
			} else {
				return null;
			}
		} catch (Exception e) {
			
			return null;
		}
	}

	public void setGeometry(Polygon geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		return "Junta [id=" + id + ", title=" + title + "]";
	}

	public static String convertClobToString(java.sql.Clob clob)
			throws IOException, SQLException {
		if (clob != null) {
			Reader reader = clob.getCharacterStream();
			int c = -1;
			StringBuilder sb = new StringBuilder();
			while ((c = reader.read()) != -1) {
				sb.append(((char) c));
			}

			return sb.toString();
		} else {
			return null;
		}
	}
	public String getUri() {
		return Funciones.obtenerPath(this.getClass()) + getId();
	}


}
