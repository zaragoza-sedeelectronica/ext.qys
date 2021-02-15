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

package org.sede.servicio.qys.dao.consulta;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase datos de servicio de datos
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name = "service-datos")
public class ServiceDatos {
	/**
	 *  variable id
	 */
    private BigDecimal id;
    /**
	 *  variable title
	 */
    private String title;
    /**
	 *  variable open
	 */
    private BigDecimal open;
    /**
	 *  variable closed
	 */
    private BigDecimal closed;
    
    /**
	 *  variable closed
	 */
    private BigDecimal total;
    /**
	 *  variable parent
	 */
    private BigDecimal parent;
    
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getClosed() {
		return closed;
	}
	public void setClosed(BigDecimal closed) {
		this.closed = closed;
	}
	public BigDecimal getParent() {
		return parent;
	}
	public void setParent(BigDecimal parent) {
		this.parent = parent;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "ServiceDatos [id=" + id + ", title=" + title + ", open=" + open
				+ ", closed=" + closed + ", parent=" + parent + "]";
	}

   

}