/* Copyright (C) 2020 Oficina Técnica de Participación, Transparenica y Gobierno Abierto del Ayuntamiento de Zaragoza
 * 
 * Este fichero es parte del "Quejas y Sugerencias - Open City Zaragoza".
 *
 * "Quejas y Sugerencias - Open City Zaragoza" es un software libre; usted puede utilizar esta obra respetando la licencia GNU General Public License, versión 3 o posterior, publicada por Free Software Foundation
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL», SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones que establece la Licencia. 
 *
 * Para más información, puede contactar con los autores en: gobiernoabierto@zaragoza.es, sedelectronica@zaragoza.es
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