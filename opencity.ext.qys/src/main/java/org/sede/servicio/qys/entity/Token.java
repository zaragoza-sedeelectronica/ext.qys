package org.sede.servicio.qys.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.dao.EntidadBase;

/**
 * Clase EntidadBase
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name="service")
public class Token extends EntidadBase {

	/**
	 *  variable id
	 */
	private String id;
	/**
	 *  variable title
	 */
	private String title;
	/**
	 *  variable description
	 */
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	@Override
	public String toString() {
		return "Token [id=" + id + ", title=" + title + ", description="
				+ description + "]";
	}
	
}
