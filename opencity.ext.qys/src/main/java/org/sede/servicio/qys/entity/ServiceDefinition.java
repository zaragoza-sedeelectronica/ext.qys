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

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.dao.EntidadBase;
/**
 * Clase EntidadBase
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name="service_definition")
public class ServiceDefinition extends EntidadBase {

	/**
	 *  variable service_code
	 */
	private String service_code;
	/**
	 *  variable attributes
	 */
	private List<Attribute> attributes;
	public String getService_code() {
		return service_code;
	}
	public void setService_code(String service_code) {
		this.service_code = service_code;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	@Override
	public String toString() {
		return "ServiceDefinition [service_code=" + service_code
				+ ", attributes=" + attributes + "]";
	}
	
	
}
