package org.sede.servicio.qys.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.sede.core.anotaciones.Esquema;
import org.sede.core.anotaciones.Permisos;
import org.sede.core.dao.EntidadBase;
import org.sede.core.utils.Funciones;

/**
 * Clase EntidadExterna
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name = "entidad-externa")
@Entity
@Table(name = "HB_ENTIDADESEXTERNAS", schema = Esquema.TICKETING)
@DynamicUpdate
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceGenerator(name = "SECUENCIA_SEQ_ENTIDADEXTERNA", sequenceName = "SEQ_ENTIDADEXTERNA", allocationSize = 1)
@BatchSize(size=100)
public class EntidadExterna extends EntidadBase implements java.io.Serializable {

	/**
	 *  variable id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECUENCIA_SEQ_ENTIDADEXTERNA")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	private BigDecimal id;
	/**
	 *  variable name
	 */
	@Column(name = "NOMBRE")
	@Size(max = 300)
	private String name;
	/**
	 *  variable phone
	 */
	@Column(name = "TELEFONO")
	@Size(max = 50)
	private String phone;
	/**
	 *  variable email
	 */
	@Column(name = "EMAIL")
	@Size(max = 75)
	private String email;
	/**
	 *  variable user_id
	 */
	@Column(name = "USER_ID")
	@Size(max = 20)
	@Permisos(Permisos.DET)
	private BigDecimal user_id;
	
	
	/**
	 * Constructor
	 */
	public EntidadExterna() {
		super();
	}

	public BigDecimal getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getUser_id() {
		return user_id;
	}

	public void setUser_id(BigDecimal user_id) {
		this.user_id = user_id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getUri() {
		return Funciones.obtenerPath(this.getClass()) + getId();
	}

	@Override
	public String toString() {
		return "Entidad [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", email=" + email + ", user_id=" + user_id + "]";
	}
	
	
}
