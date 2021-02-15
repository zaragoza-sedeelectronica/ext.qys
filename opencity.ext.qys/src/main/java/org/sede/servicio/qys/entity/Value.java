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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.dao.EntidadBase;

/**
 * Clase Value
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name="value")
public class Value extends EntidadBase {
	/**
	 *  variable key
	 */
	private String key;
	/**
	 *  variable value
	 */
	private String name;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * Constructor
	 * 
	 * @param key
	 * @param name
	 */
	public Value(String key, String name) {
		super();
		this.key = key;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Options [key=" + key + ", name=" + name + "]";
	}
	public static List<Value> getSiNo() {
		List<Value> listado = new ArrayList<Value>();
		listado.add(new Value("S", "Sí"));
		listado.add(new Value("N", "No"));
		return listado;
	}

	
	
}
