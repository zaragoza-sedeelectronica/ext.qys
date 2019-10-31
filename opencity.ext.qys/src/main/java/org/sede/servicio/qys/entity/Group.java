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
