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

package org.sede.servicio.qys.entity.db;
// Generated 17-jul-2019 8:10:17 by Hibernate Tools 4.3.5.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.anotaciones.Interno;
import org.sede.core.anotaciones.SoloEnEstaEntidad;
import org.sede.servicio.qys.ConfigQys;

/**
 * Hbcategories generated by hbm2java
 */
@XmlRootElement(name="category")
@Entity
@Table(name = "HBCATEGORIES", schema = ConfigQys.ESQUEMA)
public class Hbcategories implements java.io.Serializable {

	private BigDecimal catHbid;
	private BigDecimal rqtHbidTemplatecategory;
	private String catTemplaterequestdescription;
	private BigDecimal catExpectedresolutionminutes;
	private BigDecimal catExpectresolutionminreal;
	private BigDecimal catObjectivepercentage;
	@SoloEnEstaEntidad
	private Set<Hbcategoryscalinggroups> hbcategoryscalinggroupses = new HashSet<Hbcategoryscalinggroups>(0);
	@Interno
	private Set<Hbrequests> hbrequestses = new HashSet<Hbrequests>(0);

	public Hbcategories() {
	}

	public Hbcategories(BigDecimal catHbid, String catTemplaterequestdescription,
			BigDecimal catExpectedresolutionminutes, BigDecimal catExpectresolutionminreal,
			BigDecimal catObjectivepercentage) {
		this.catHbid = catHbid;
		this.catTemplaterequestdescription = catTemplaterequestdescription;
		this.catExpectedresolutionminutes = catExpectedresolutionminutes;
		this.catExpectresolutionminreal = catExpectresolutionminreal;
		this.catObjectivepercentage = catObjectivepercentage;
	}

	public Hbcategories(BigDecimal catHbid, BigDecimal rqtHbidTemplatecategory, String catTemplaterequestdescription,
			BigDecimal catExpectedresolutionminutes, BigDecimal catExpectresolutionminreal,
			BigDecimal catObjectivepercentage, Set<Hbcategoryscalinggroups> hbcategoryscalinggroupses,
			Set<Hbrequests> hbrequestses) {
		this.catHbid = catHbid;
		this.rqtHbidTemplatecategory = rqtHbidTemplatecategory;
		this.catTemplaterequestdescription = catTemplaterequestdescription;
		this.catExpectedresolutionminutes = catExpectedresolutionminutes;
		this.catExpectresolutionminreal = catExpectresolutionminreal;
		this.catObjectivepercentage = catObjectivepercentage;
		this.hbcategoryscalinggroupses = hbcategoryscalinggroupses;
		this.hbrequestses = hbrequestses;
	}

	@Id

	@Column(name = "CAT_HBID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getCatHbid() {
		return this.catHbid;
	}

	public void setCatHbid(BigDecimal catHbid) {
		this.catHbid = catHbid;
	}

	@Column(name = "RQT_HBID_TEMPLATECATEGORY", precision = 22, scale = 0)
	public BigDecimal getRqtHbidTemplatecategory() {
		return this.rqtHbidTemplatecategory;
	}

	public void setRqtHbidTemplatecategory(BigDecimal rqtHbidTemplatecategory) {
		this.rqtHbidTemplatecategory = rqtHbidTemplatecategory;
	}

	@Column(name = "CAT_TEMPLATEREQUESTDESCRIPTION", nullable = false, length = 4000)
	public String getCatTemplaterequestdescription() {
		return this.catTemplaterequestdescription;
	}

	public void setCatTemplaterequestdescription(String catTemplaterequestdescription) {
		this.catTemplaterequestdescription = catTemplaterequestdescription;
	}

	@Column(name = "CAT_EXPECTEDRESOLUTIONMINUTES", nullable = false, precision = 22, scale = 0)
	public BigDecimal getCatExpectedresolutionminutes() {
		return this.catExpectedresolutionminutes;
	}

	public void setCatExpectedresolutionminutes(BigDecimal catExpectedresolutionminutes) {
		this.catExpectedresolutionminutes = catExpectedresolutionminutes;
	}

	@Column(name = "CAT_EXPECTRESOLUTIONMINREAL", nullable = false, precision = 22, scale = 0)
	public BigDecimal getCatExpectresolutionminreal() {
		return this.catExpectresolutionminreal;
	}

	public void setCatExpectresolutionminreal(BigDecimal catExpectresolutionminreal) {
		this.catExpectresolutionminreal = catExpectresolutionminreal;
	}

	@Column(name = "CAT_OBJECTIVEPERCENTAGE", nullable = false, precision = 22, scale = 0)
	public BigDecimal getCatObjectivepercentage() {
		return this.catObjectivepercentage;
	}

	public void setCatObjectivepercentage(BigDecimal catObjectivepercentage) {
		this.catObjectivepercentage = catObjectivepercentage;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hbcategories")
	public Set<Hbcategoryscalinggroups> getHbcategoryscalinggroupses() {
		return this.hbcategoryscalinggroupses;
	}

	public void setHbcategoryscalinggroupses(Set<Hbcategoryscalinggroups> hbcategoryscalinggroupses) {
		this.hbcategoryscalinggroupses = hbcategoryscalinggroupses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hbcategories")
	public Set<Hbrequests> getHbrequestses() {
		return this.hbrequestses;
	}

	public void setHbrequestses(Set<Hbrequests> hbrequestses) {
		this.hbrequestses = hbrequestses;
	}

}
