package org.sede.servicio.qys.entity.db;
// Generated 17-jul-2019 8:10:17 by Hibernate Tools 4.3.5.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Hbcategorylevels generated by hbm2java
 */
@XmlRootElement(name="category-level")
@Entity
@Table(name = "HBCATEGORYLEVELS", schema = "TICKETING", uniqueConstraints = @UniqueConstraint(columnNames = {
		"CAL_PARENT", "CAL_NAME" }))
public class Hbcategorylevels implements java.io.Serializable {

	private BigDecimal calHbid;
	private BigDecimal calHbversion;
	private BigDecimal calParent;
	private BigDecimal calLevel;
	private String calName;
	private BigDecimal calIscategory;
	private String autoassign;

	public Hbcategorylevels() {
	}

	public Hbcategorylevels(BigDecimal calHbid, BigDecimal calHbversion, BigDecimal calLevel, String calName,
			BigDecimal calIscategory) {
		this.calHbid = calHbid;
		this.calHbversion = calHbversion;
		this.calLevel = calLevel;
		this.calName = calName;
		this.calIscategory = calIscategory;
	}

	public Hbcategorylevels(BigDecimal calHbid, BigDecimal calHbversion, BigDecimal calParent, BigDecimal calLevel,
			String calName, BigDecimal calIscategory, String autoassign) {
		this.calHbid = calHbid;
		this.calHbversion = calHbversion;
		this.calParent = calParent;
		this.calLevel = calLevel;
		this.calName = calName;
		this.calIscategory = calIscategory;
		this.autoassign = autoassign;
	}

	@Id

	@Column(name = "CAL_HBID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getCalHbid() {
		return this.calHbid;
	}

	public void setCalHbid(BigDecimal calHbid) {
		this.calHbid = calHbid;
	}

	@Column(name = "CAL_HBVERSION", nullable = false, precision = 22, scale = 0)
	public BigDecimal getCalHbversion() {
		return this.calHbversion;
	}

	public void setCalHbversion(BigDecimal calHbversion) {
		this.calHbversion = calHbversion;
	}

	@Column(name = "CAL_PARENT", precision = 22, scale = 0)
	public BigDecimal getCalParent() {
		return this.calParent;
	}

	public void setCalParent(BigDecimal calParent) {
		this.calParent = calParent;
	}

	@Column(name = "CAL_LEVEL", nullable = false, precision = 22, scale = 0)
	public BigDecimal getCalLevel() {
		return this.calLevel;
	}

	public void setCalLevel(BigDecimal calLevel) {
		this.calLevel = calLevel;
	}

	@Column(name = "CAL_NAME", nullable = false, length = 60)
	public String getCalName() {
		return this.calName;
	}

	public void setCalName(String calName) {
		this.calName = calName;
	}

	@Column(name = "CAL_ISCATEGORY", nullable = false, precision = 22, scale = 0)
	public BigDecimal getCalIscategory() {
		return this.calIscategory;
	}

	public void setCalIscategory(BigDecimal calIscategory) {
		this.calIscategory = calIscategory;
	}

	@Column(name = "AUTOASSIGN", length = 1)
	public String getAutoassign() {
		return this.autoassign;
	}

	public void setAutoassign(String autoassign) {
		this.autoassign = autoassign;
	}

}