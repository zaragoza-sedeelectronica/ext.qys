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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.sede.core.anotaciones.Interno;
import org.sede.servicio.qys.ConfigQys;

/**
 * Hbrequeststates generated by hbm2java
 */
@XmlRootElement(name="state")
@Entity
@Table(name = "HBREQUESTSTATES", schema = ConfigQys.ESQUEMA, uniqueConstraints = @UniqueConstraint(columnNames = "RST_NAME"))
public class Hbrequeststates implements java.io.Serializable {

	private BigDecimal rstHbid;
	private String rstName;
	private BigDecimal rstHbversion;
	@Interno
	private Set<Hbrequests> hbrequestses = new HashSet<Hbrequests>(0);

	public Hbrequeststates() {
	}

	public Hbrequeststates(BigDecimal rstHbid, String rstName, BigDecimal rstHbversion) {
		this.rstHbid = rstHbid;
		this.rstName = rstName;
		this.rstHbversion = rstHbversion;
	}

	public Hbrequeststates(BigDecimal rstHbid, String rstName, BigDecimal rstHbversion, Set<Hbrequests> hbrequestses) {
		this.rstHbid = rstHbid;
		this.rstName = rstName;
		this.rstHbversion = rstHbversion;
		this.hbrequestses = hbrequestses;
	}

	@Id

	@Column(name = "RST_HBID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getRstHbid() {
		return this.rstHbid;
	}

	public void setRstHbid(BigDecimal rstHbid) {
		this.rstHbid = rstHbid;
	}

	@Column(name = "RST_NAME", unique = true, nullable = false, length = 30)
	public String getRstName() {
		return this.rstName;
	}

	public void setRstName(String rstName) {
		this.rstName = rstName;
	}

	@Column(name = "RST_HBVERSION", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRstHbversion() {
		return this.rstHbversion;
	}

	public void setRstHbversion(BigDecimal rstHbversion) {
		this.rstHbversion = rstHbversion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hbrequeststates")
	public Set<Hbrequests> getHbrequestses() {
		return this.hbrequestses;
	}

	public void setHbrequestses(Set<Hbrequests> hbrequestses) {
		this.hbrequestses = hbrequestses;
	}

}
