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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.dao.EntidadBase;

/**
 * Clase attribute
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name="attribute")
public class Attribute extends EntidadBase {
	/**
	 *  variable variable
	 */
	private Boolean variable;
	/**
	 *  variable code
	 */
	private String code;
	/**
	 *  variable datatype 
	 */
	private String datatype;
	/**
	 *  variable required
	 */
	private Boolean required;
	/**
	 *  variable datatype_description
	 */
	private String datatype_description;
	/**
	 *  variable order
	 */
	private Integer order;
	/**
	 *  variable description
	 */
	private String description;
	/**
	 *  variable values 
	 */
	private List<Value> values;

	public Boolean getVariable() {
		return variable;
	}

	public void setVariable(Boolean variable) {
		this.variable = variable;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getDatatype_description() {
		return datatype_description;
	}

	public void setDatatype_description(String datatype_description) {
		this.datatype_description = datatype_description;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

	/**
	 * Constructor
	 * 
	 * @param variable
	 * @param code
	 * @param datatype
	 * @param required
	 * @param datatype_description
	 * @param order
	 * @param description
	 * @param values
	 */
	public Attribute(Boolean variable, String code, String datatype,
			Boolean required, String datatype_description, Integer order,
			String description, List<Value> values) {
		super();
		this.variable = variable;
		this.code = code;
		this.datatype = datatype;
		this.required = required;
		this.datatype_description = datatype_description;
		this.order = order;
		this.description = description;
		this.values = values;
	}
	
	
	@Override
	public String toString() {
		return "Attribute [variable=" + variable + ", code=" + code
				+ ", datatype=" + datatype + ", required=" + required
				+ ", datatype_description=" + datatype_description + ", order="
				+ order + ", description=" + description + ", values=" + values
				+ "]";
	}

	public static List<Attribute> getLista() {
		List<Attribute> lista = new ArrayList<Attribute>();
		int i = 0;
		lista.add(new Attribute(true, "account_id","number",true,"ID Usuario",++i,"Identificador del usuario que realiza la queja", null));
		lista.add(new Attribute(true, "first_name","string",false,"Nombre",++i,"", null));
		lista.add(new Attribute(true, "last_name","string",false,"Apellidos",++i,"", null));
		lista.add(new Attribute(true, "phone","string",false,"Tel�fono",++i,"", null));
		lista.add(new Attribute(true, "email","string",false,"Correo electr�nico",++i,"Correo Electr�nico donde enviarle la confirmaci�n de recepci�n de sus datos as� como la respuesta a su sugerencia", null));
		lista.add(new Attribute(true, "user_address","string",false,"Direcci�n",++i,"Direcci�n del usuario que realiza la queja/sugerencia", null));
		lista.add(new Attribute(true, "title","string",true,"Asunto",++i,"Descripci�n  breve de la sugerencia", null));
		lista.add(new Attribute(true, "description","text",true,"Descripci�n",++i,"Introduzca la descripci�n de la queja/sugerencia de la forma m�s detallada posible", null));
		lista.add(new Attribute(true, "address","string",false,"Direcci�n",++i,"Descripci�n de la localizaci�n de la queja/sugerencia", null));
		lista.add(new Attribute(true, "lat","number",false,"Latitud",++i,"Sistema de referencia wgs84", null));
		lista.add(new Attribute(true, "lon","number",false,"Longitud",++i,"Sistema de referencia wgs84", null));
		lista.add(new Attribute(true, "public","singlevaluelist",true,"Publicar",++i,"Desea que la sugerencia se publique en www.zaragoza.es", Value.getSiNo()));
		lista.add(new Attribute(true, "media_url","string",false,"Adjunto",++i,"URL del documento asociado a la queja/sugerencia", null));
		return lista;
	}

	

	
	
}
