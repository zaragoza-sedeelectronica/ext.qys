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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.sede.servicio.qys.ConfigQys;

/**
 * Hbrequestsubstates generated by hbm2java
 */
@XmlRootElement(name="substate")
@Entity
@Table(name = "HBREQUESTSUBSTATES", schema = ConfigQys.ESQUEMA, uniqueConstraints = @UniqueConstraint(columnNames = "RSS_NAME"))
public class Hbrequestsubstates implements java.io.Serializable {

	private BigDecimal rssHbid;
	private String rssName;
	private BigDecimal rssIdRequeststate;
	private BigDecimal rssIsstate;
	private BigDecimal rssSlaStop;
	private BigDecimal rssHbversion;
	private BigDecimal rssSendmail;
	private String rssMailto;

	public Hbrequestsubstates() {
	}

	public Hbrequestsubstates(BigDecimal rssHbid, String rssName, BigDecimal rssIdRequeststate, BigDecimal rssIsstate,
			BigDecimal rssSlaStop, BigDecimal rssHbversion, BigDecimal rssSendmail, String rssMailto) {
		this.rssHbid = rssHbid;
		this.rssName = rssName;
		this.rssIdRequeststate = rssIdRequeststate;
		this.rssIsstate = rssIsstate;
		this.rssSlaStop = rssSlaStop;
		this.rssHbversion = rssHbversion;
		this.rssSendmail = rssSendmail;
		this.rssMailto = rssMailto;
	}

	@Id

	@Column(name = "RSS_HBID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getRssHbid() {
		return this.rssHbid;
	}

	public void setRssHbid(BigDecimal rssHbid) {
		this.rssHbid = rssHbid;
	}

	@Column(name = "RSS_NAME", unique = true, nullable = false, length = 30)
	public String getRssName() {
		return this.rssName;
	}

	public void setRssName(String rssName) {
		this.rssName = rssName;
	}

	@Column(name = "RSS_ID_REQUESTSTATE", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRssIdRequeststate() {
		return this.rssIdRequeststate;
	}

	public void setRssIdRequeststate(BigDecimal rssIdRequeststate) {
		this.rssIdRequeststate = rssIdRequeststate;
	}

	@Column(name = "RSS_ISSTATE", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRssIsstate() {
		return this.rssIsstate;
	}

	public void setRssIsstate(BigDecimal rssIsstate) {
		this.rssIsstate = rssIsstate;
	}

	@Column(name = "RSS_SLA_STOP", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRssSlaStop() {
		return this.rssSlaStop;
	}

	public void setRssSlaStop(BigDecimal rssSlaStop) {
		this.rssSlaStop = rssSlaStop;
	}

	@Column(name = "RSS_HBVERSION", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRssHbversion() {
		return this.rssHbversion;
	}

	public void setRssHbversion(BigDecimal rssHbversion) {
		this.rssHbversion = rssHbversion;
	}

	@Column(name = "RSS_SENDMAIL", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRssSendmail() {
		return this.rssSendmail;
	}

	public void setRssSendmail(BigDecimal rssSendmail) {
		this.rssSendmail = rssSendmail;
	}

	@Column(name = "RSS_MAILTO", nullable = false)
	public String getRssMailto() {
		return this.rssMailto;
	}

	public void setRssMailto(String rssMailto) {
		this.rssMailto = rssMailto;
	}

}
