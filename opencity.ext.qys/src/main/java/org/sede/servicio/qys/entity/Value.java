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
