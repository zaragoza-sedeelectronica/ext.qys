/* Copyright (C) 2020 Oficina Técnica de Participación, Transparenica y Gobierno Abierto del Ayuntamiento de Zaragoza
 * 
 * Este fichero es parte del "Quejas y Sugerencias - Open City Zaragoza".
 *
 * "Quejas y Sugerencias - Open City Zaragoza" es un software libre; usted puede utilizar esta obra respetando la licencia GNU General Public License, versión 3 o posterior, publicada por Free Software Foundation
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL», SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones que establece la Licencia. 
 *
 * Para más información, puede contactar con los autores en: gobiernoabierto@zaragoza.es, sedelectronica@zaragoza.es
 */

package org.sede.servicio.qys.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.anotaciones.RequiredSinValidacion;
import org.sede.core.anotaciones.ResultsOnly;
import org.sede.core.dao.EntidadBase;
/**
 * Clase EntidadBase
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name="service")
@ResultsOnly(xmlroot="services")
public class Service extends EntidadBase {

	/**
	 *  variable service_code
	 */
	@RequiredSinValidacion
	private String service_code;
	/**
	 *  variable service_name
	 */
	@RequiredSinValidacion
	private String service_name;	
	/**
	 *  variable description
	 */
	private String description;	
	/**
	 *  variable metadata
	 */
	@RequiredSinValidacion
	private Boolean metadata = false;
	/**
	 *  variable type
	 */
	@RequiredSinValidacion
	private String type = "realtime";
	/**
	 *  variable keywords
	 */
	private String keywords; // No lo tenemos
	/**
	 *  variable group
	 */
	private String group; // Categori­a padre. No lo tenemos 
	/**
	 * Constructor
	 */
	public Service() {
		super();
	}
	/**
	 * Constructor
	 * 
	 * @param service_code
	 * @param service_name
	 * @param description
	 * @param metadata
	 * @param type
	 * @param keywords
	 * @param group
	 */
	public Service(String service_code, String service_name,
			String description, Boolean metadata, String type, String keywords,
			String group) {
		super();
		this.service_code = service_code;
		this.service_name = service_name;
		this.description = description;
		this.metadata = metadata;
		this.type = type;
		this.keywords = keywords;
		this.group = group;
	}
	public String getService_code() {
		return service_code;
	}
	public void setService_code(String service_code) {
		this.service_code = service_code;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getMetadata() {
		return metadata;
	}
	public void setMetadata(Boolean metadata) {
		this.metadata = metadata;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	@Override
	public String toString() {
		return "Service [service_code=" + service_code + ", service_name="
				+ service_name + ", description=" + description + ", metadata="
				+ metadata + ", type=" + type + ", keywords=" + keywords
				+ ", group=" + group + "]";
	}
	
	
	
	
}
