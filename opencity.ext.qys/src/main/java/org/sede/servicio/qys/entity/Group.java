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

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.dao.EntidadBase;
/**
 * Clase Group
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name = "group")
public class Group extends EntidadBase {

	/**
	 *  variable group_code
	 */
	private BigDecimal group_code;
	/**
	 *  variable group_name
	 */
	private String group_name;
	/**
	 *  variable email
	 */
	private String email;
	
	/**
	 * Constructor
	 */
	public Group() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param code
	 * @param name
	 * @param mail
	 */
	public Group(String code, String name, String mail) {
		this.group_code = new BigDecimal(code);
		this.group_name = name;
		this.email = mail;
	}

	public BigDecimal getGroup_code() {
		return group_code;
	}

	public void setGroup_code(BigDecimal group_code) {
		this.group_code = group_code;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Group [group_code=" + group_code + ", group_name=" + group_name
				+ ", email=" + email + "]";
	}

	
}
