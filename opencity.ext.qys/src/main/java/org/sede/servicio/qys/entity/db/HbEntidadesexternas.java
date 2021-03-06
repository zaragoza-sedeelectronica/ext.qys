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
import org.sede.servicio.qys.ConfigQys;

/**
 * HbEntidadesexternas generated by hbm2java
 */
@XmlRootElement(name="external-entity")
@Entity
@Table(name = "HB_ENTIDADESEXTERNAS", schema = ConfigQys.ESQUEMA)
public class HbEntidadesexternas implements java.io.Serializable {

	private BigDecimal id;
	private String nombre;
	private String email;
	private String telefono;
	private BigDecimal userId;
	@Interno
	private Set<Hbrequests> hbrequestses = new HashSet<Hbrequests>(0);

	public HbEntidadesexternas() {
	}

	public HbEntidadesexternas(BigDecimal id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public HbEntidadesexternas(BigDecimal id, String nombre, String email, String telefono, BigDecimal userId,
			Set<Hbrequests> hbrequestses) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.userId = userId;
		this.hbrequestses = hbrequestses;
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@Column(name = "NOMBRE", nullable = false, length = 400)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "EMAIL", length = 400)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "TELEFONO", length = 300)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "USER_ID", precision = 22, scale = 0)
	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hbEntidadesexternas")
	public Set<Hbrequests> getHbrequestses() {
		return this.hbrequestses;
	}

	public void setHbrequestses(Set<Hbrequests> hbrequestses) {
		this.hbrequestses = hbrequestses;
	}

}
