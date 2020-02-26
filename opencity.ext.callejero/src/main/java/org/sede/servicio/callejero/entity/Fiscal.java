package org.sede.servicio.callejero.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.SafeHtml;
import org.sede.core.anotaciones.Context;
import org.sede.core.anotaciones.Rdf;
import org.sede.core.dao.EntidadBase;
import org.sede.core.utils.Funciones;

@XmlRootElement(name = "datos-fiscal")
@Entity
@DynamicUpdate
@Table(name = "V02012_FISCAL_WEB", schema = "COMUN")
@XmlAccessorType(XmlAccessType.FIELD)
@BatchSize(size=50)
public class Fiscal extends EntidadBase implements java.io.Serializable {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@Digits(integer = 6, fraction = 0)
	@Rdf(contexto = Context.DCT, propiedad = "identifier")
	private Integer id;
	
	
	@Column(name = "ID_ENTRADA", unique = true, nullable = false)
	private Integer idCalleEntrada;
	
	@Column(name = "ENTRADA")
	@Size(max = 30)

	private String calleEntrada;
	
	@Column(name = "ID_SALIDA", unique = true, nullable = false)
	private Integer idCalleSalida;
	
	@Column(name = "SALIDA")
	@Size(max = 30)
	private String calleSalida;
	
	@Column(name = "PLANO")
	@Size(max = 10)
	@SafeHtml
	private String plano;
	
	@Column(name = "POLIGONO")
	@Size(max = 10)
	private String poligono;
	
	@Column(name = "CODIGO_POSTAL")
	@Size(max = 4000)
	private String codigoPostal;
	
	@Column(name = "FISCAL")
	@Size(max = 4000)
	private String fiscal;
	
	@Column(name = "IAE")
	@Size(max = 4000)
	private String iae;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public String getPoligono() {
		return poligono;
	}

	public void setPoligono(String poligono) {
		this.poligono = poligono;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getFiscal() {
		return fiscal;
	}

	public void setFiscal(String fiscal) {
		this.fiscal = fiscal;
	}

	public String getIae() {
		return iae;
	}

	public void setIae(String iae) {
		this.iae = iae;
	}

	public Integer getIdCalleEntrada() {
		return idCalleEntrada;
	}

	public void setIdCalleEntrada(Integer idCalleEntrada) {
		this.idCalleEntrada = idCalleEntrada;
	}

	public String getCalleEntrada() {
		return calleEntrada;
	}

	public void setCalleEntrada(String calleEntrada) {
		this.calleEntrada = calleEntrada;
	}

	public Integer getIdCalleSalida() {
		return idCalleSalida;
	}

	public void setIdCalleSalida(Integer idCalleSalida) {
		this.idCalleSalida = idCalleSalida;
	}

	public String getCalleSalida() {
		return calleSalida;
	}

	public void setCalleSalida(String calleSalida) {
		this.calleSalida = calleSalida;
	}

	
	@Override
	public String toString() {
		return "Fiscal [id=" + id + ", idCalleEntrada=" + idCalleEntrada
				+ ", calleEntrada=" + calleEntrada + ", idCalleSalida="
				+ idCalleSalida + ", calleSalida=" + calleSalida + ", plano="
				+ plano + ", poligono=" + poligono + ", codigoPostal="
				+ codigoPostal + ", fiscal=" + fiscal + ", iae=" + iae + "]";
	}

	@Transient
	public String getUri() {
		return Funciones.obtenerPath(this.getClass()) + getId();
	}	
}
