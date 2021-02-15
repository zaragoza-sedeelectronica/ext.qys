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
import javax.persistence.Embeddable;

/**
 * HbrequesttokenId generated by hbm2java
 */
@Embeddable
public class HbrequesttokenId implements java.io.Serializable {

	private BigDecimal rqtHbid;
	private BigDecimal rqaHbid;

	public HbrequesttokenId() {
	}

	public HbrequesttokenId(BigDecimal rqtHbid, BigDecimal rqaHbid) {
		this.rqtHbid = rqtHbid;
		this.rqaHbid = rqaHbid;
	}

	@Column(name = "RQT_HBID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRqtHbid() {
		return this.rqtHbid;
	}

	public void setRqtHbid(BigDecimal rqtHbid) {
		this.rqtHbid = rqtHbid;
	}

	@Column(name = "RQA_HBID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRqaHbid() {
		return this.rqaHbid;
	}

	public void setRqaHbid(BigDecimal rqaHbid) {
		this.rqaHbid = rqaHbid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HbrequesttokenId))
			return false;
		HbrequesttokenId castOther = (HbrequesttokenId) other;

		return ((this.getRqtHbid() == castOther.getRqtHbid()) || (this.getRqtHbid() != null
				&& castOther.getRqtHbid() != null && this.getRqtHbid().equals(castOther.getRqtHbid())))
				&& ((this.getRqaHbid() == castOther.getRqaHbid()) || (this.getRqaHbid() != null
						&& castOther.getRqaHbid() != null && this.getRqaHbid().equals(castOther.getRqaHbid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getRqtHbid() == null ? 0 : this.getRqtHbid().hashCode());
		result = 37 * result + (getRqaHbid() == null ? 0 : this.getRqaHbid().hashCode());
		return result;
	}

}
