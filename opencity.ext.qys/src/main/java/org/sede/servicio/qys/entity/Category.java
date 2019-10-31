package org.sede.servicio.qys.entity;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.dao.EntidadBase;

/**
 * Clase attribute
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name = "category")
public class Category extends EntidadBase {

	/**
	 *  variable service_code
	 */
	private BigDecimal service_code;
	/**
	 *  variable service_name
	 */
	private String service_name;
	/**
	 *  variable service_description
	 */
	private String service_description;
	/**
	 *  variable service_parent_code
	 */
	private BigDecimal service_parent_code;
	/**
	 *  variable agency_responsible_code
	 */
	private BigDecimal agency_responsible_code;
	/**
	 *  variable agency_responsible
	 */
	private String agency_responsible;
	/**
	 *  variable group_code
	 */
	private BigDecimal group_code;
	/**
	 *  variable group_name
	 */
	private String group_name;
	/**
	 *  variable service_level
	 */
	private BigDecimal service_level;
	/**
	 *  variable autoassign
	 */
	private String autoassign;
	/**
	 *  Constructor
	 */
	public Category() {
		super();
	}

	public BigDecimal getService_code() {
		return service_code;
	}

	public void setService_code(BigDecimal service_code) {
		this.service_code = service_code;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public BigDecimal getAgency_responsible_code() {
		return agency_responsible_code;
	}

	public void setAgency_responsible_code(BigDecimal agency_responsible_code) {
		this.agency_responsible_code = agency_responsible_code;
	}

	public String getAgency_responsible() {
		return agency_responsible;
	}

	public void setAgency_responsible(String agency_responsible_name) {
		this.agency_responsible = agency_responsible_name;
	}

	public String getService_description() {
		return service_description;
	}

	public void setService_description(String service_description) {
		this.service_description = service_description;
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

	public String getAutoassign() {
		return autoassign;
	}

	public void setAutoassign(String autoassign) {
		this.autoassign = autoassign;
	}

	public BigDecimal getService_parent_code() {
		return service_parent_code;
	}

	public void setService_parent_code(BigDecimal service_parent_code) {
		this.service_parent_code = service_parent_code;
	}

	public BigDecimal getService_level() {
		return service_level;
	}

	public void setService_level(BigDecimal service_level) {
		this.service_level = service_level;
	}

	@Override
	public String toString() {
		return "Category [service_code=" + service_code + ", service_name="
				+ service_name + ", service_description=" + service_description
				+ ", service_parent_code=" + service_parent_code
				+ ", agency_responsible_code=" + agency_responsible_code
				+ ", agency_responsible=" + agency_responsible
				+ ", group_code=" + group_code + ", group_name=" + group_name
				+ ", service_level=" + service_level + ", autoassign="
				+ autoassign + "]";
	}

	
	
	
}
