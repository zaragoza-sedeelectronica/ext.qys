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
 * Hbrequestorigins generated by hbm2java
 */
@XmlRootElement(name="origin")
@Entity
@Table(name = "HBREQUESTORIGINS", schema = ConfigQys.ESQUEMA)
public class Hbrequestorigins implements java.io.Serializable {

	private BigDecimal rogHbid;
	private String rogName;
	private BigDecimal rogHbversion;
	private BigDecimal rogDefault;
	@Interno
	private Set<HbusersOrigin> hbusersOrigins = new HashSet<HbusersOrigin>(0);

	public Hbrequestorigins() {
	}

	public Hbrequestorigins(BigDecimal rogHbid, String rogName, BigDecimal rogHbversion, BigDecimal rogDefault) {
		this.rogHbid = rogHbid;
		this.rogName = rogName;
		this.rogHbversion = rogHbversion;
		this.rogDefault = rogDefault;
	}

	public Hbrequestorigins(BigDecimal rogHbid, String rogName, BigDecimal rogHbversion, BigDecimal rogDefault,
			Set<HbusersOrigin> hbusersOrigins) {
		this.rogHbid = rogHbid;
		this.rogName = rogName;
		this.rogHbversion = rogHbversion;
		this.rogDefault = rogDefault;
		this.hbusersOrigins = hbusersOrigins;
	}

	@Id

	@Column(name = "ROG_HBID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getRogHbid() {
		return this.rogHbid;
	}

	public void setRogHbid(BigDecimal rogHbid) {
		this.rogHbid = rogHbid;
	}

	@Column(name = "ROG_NAME", nullable = false, length = 30)
	public String getRogName() {
		return this.rogName;
	}

	public void setRogName(String rogName) {
		this.rogName = rogName;
	}

	@Column(name = "ROG_HBVERSION", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRogHbversion() {
		return this.rogHbversion;
	}

	public void setRogHbversion(BigDecimal rogHbversion) {
		this.rogHbversion = rogHbversion;
	}

	@Column(name = "ROG_DEFAULT", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRogDefault() {
		return this.rogDefault;
	}

	public void setRogDefault(BigDecimal rogDefault) {
		this.rogDefault = rogDefault;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hbrequestorigins")
	public Set<HbusersOrigin> getHbusersOrigins() {
		return this.hbusersOrigins;
	}

	public void setHbusersOrigins(Set<HbusersOrigin> hbusersOrigins) {
		this.hbusersOrigins = hbusersOrigins;
	}

}
