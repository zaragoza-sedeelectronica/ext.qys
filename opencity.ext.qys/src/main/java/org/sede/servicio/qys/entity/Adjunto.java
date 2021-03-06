/* Copyright (C) 2020 Oficina T�cnica de Participaci�n, Transparenica y Gobierno Abierto del Ayuntamiento de Zaragoza
 * 
 * Este fichero es parte del "Quejas y Sugerencias - Open City Zaragoza".
 *
 * "Quejas y Sugerencias - Open City Zaragoza" es un software libre; usted puede utilizar esta obra respetando la licencia GNU General Public License, versi�n 3 o posterior, publicada por Free Software Foundation
 *
 * Salvo cuando lo exija la legislaci�n aplicable o se acuerde por escrito, el programa distribuido con arreglo a la Licencia se distribuye �TAL CUAL�, SIN GARANT�AS NI CONDICIONES DE NING�N TIPO, ni expresas ni impl�citas.
 * V�ase la Licencia en el idioma concreto que rige los permisos y limitaciones que establece la Licencia. 
 *
 * Para m�s informaci�n, puede contactar con los autores en: gobiernoabierto@zaragoza.es, sedelectronica@zaragoza.es
 */

package org.sede.servicio.qys.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import org.sede.core.anotaciones.ResultsOnly;
import org.sede.core.dao.EntidadBase;

/**
 * Clase adjunto
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name="file")
@ResultsOnly(xmlroot="files")
public class Adjunto extends EntidadBase {

	/**
	 *  variable id
	 */
	private BigDecimal id;
	/**
	 *  variable version
	 */
	private BigDecimal version;
	/**
	 *  variable description
	 */
	private String description;
	/**
	 *  variable file_name
	 */
	private String file_name;
	/**
	 *  variable media_url
	 */
	private String media_url;
	/**
	 *  variable user_id
	 */
	private BigDecimal user_id;
	/**
	 *  variable user_name
	 */
	private String user_name;
	/**
	 *  variable creation_date
	 */
	private Date creation_date;
	
	/**
	 * Constructor
	 */
	public Adjunto() {
		super();
	}
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getVersion() {
		return version;
	}
	public void setVersion(BigDecimal version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	public BigDecimal getUser_id() {
		return user_id;
	}

	public void setUser_id(BigDecimal user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getMedia_url() {
		return media_url;
	}

	public void setMedia_url(String media_url) {
		this.media_url = media_url;
	}

	@Override
	public String toString() {
		return "Adjunto [id=" + id + ", version=" + version + ", description="
				+ description + ", file_name=" + file_name + ", media_url="
				+ media_url + ", user_id=" + user_id + ", user_name="
				+ user_name + ", creation_date=" + creation_date + "]";
	}

	

	
}
