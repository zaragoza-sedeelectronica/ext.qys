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
