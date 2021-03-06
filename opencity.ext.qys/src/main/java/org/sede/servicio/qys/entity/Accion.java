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

import java.math.BigDecimal;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.anotaciones.ResultsOnly;
import org.sede.core.dao.EntidadBase;

/**
 * Clase accion
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name="action")
@ResultsOnly(xmlroot="actions")
public class Accion extends EntidadBase {
	/**
	 *  variable id_aviso
	 */
	private BigDecimal id_aviso;
	/**
	 *  variable id
	 */
	private BigDecimal id;
	/**
	 *  variable version
	 */
	private BigDecimal version;
	/**
	 *  variable description
	 */
	private String description;
	/**
	 *  variable creation_date
	 */
	private Date creation_date;
	// RQA_REQUESTACTIONDATETIME es igual que creationDate
	/**
	 *  variable elapsed_seconds
	 */
	private BigDecimal elapsed_seconds;
	/**
	 *  variable elapsed_time
	 */
	private String elapsed_time;
	/**
	 *  variable agent_id
	 */
	private BigDecimal agent_id;//USR_HBID_AGENT
	/**
	 *  variable operator
	 */
	private String operator;
	/**
	 *  variable agent_name
	 */
	private String agent_name;
	/**
	 *  variable agency_responsible
	 */
	private String agency_responsible;
	/**
	 *  variable type
	 */
	private BigDecimal type;
	/**
	 *  variable type_name
	 */
	private String type_name;
	/**
	 *  variable substate
	 */
	private BigDecimal substate;
	//RSS_SLA_STOP siempre es igual a 0
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getVersion() {
		return version;
	}
	public void setVersion(BigDecimal version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public BigDecimal getElapsed_seconds() {
		return elapsed_seconds;
	}
	public void setElapsed_seconds(BigDecimal elapsed_seconds) {
		this.elapsed_seconds = elapsed_seconds;
	}
	
	
	public BigDecimal getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(BigDecimal agent_id) {
		this.agent_id = agent_id;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public BigDecimal getType() {
		return type;
	}
	public void setType(BigDecimal type) {
		this.type = type;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public BigDecimal getSubstate() {
		return substate;
	}
	public void setSubstate(BigDecimal substate) {
		this.substate = substate;
	}
	public String getElapsed_time() {
		return elapsed_time;
	}
	public void setElapsed_time(String elapsed_time) {
		this.elapsed_time = elapsed_time;
	}
	public String getAgency_responsible() {
		return agency_responsible;
	}
	public void setAgency_responsible(String agency_responsible) {
		this.agency_responsible = agency_responsible;
	}
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	public BigDecimal getId_aviso() {
		return id_aviso;
	}
	public void setId_aviso(BigDecimal id_aviso) {
		this.id_aviso = id_aviso;
	}
	@Override
	public String toString() {
		return "Accion [id=" + id + ", version=" + version + ", description="
				+ description + ", creation_date=" + creation_date
				+ ", elapsed_seconds=" + elapsed_seconds + ", elapsed_time="
				+ elapsed_time + ", agent_id=" + agent_id + ", agent_name="
				+ agent_name + ", agency_responsible=" + agency_responsible
				+ ", type=" + type + ", type_name=" + type_name + ", substate="
				+ substate + "]";
	}
	
		
}
