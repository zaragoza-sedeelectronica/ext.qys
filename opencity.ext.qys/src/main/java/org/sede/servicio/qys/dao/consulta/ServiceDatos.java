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
	@Override
	public String toString() {
		return "ServiceDatos [id=" + id + ", title=" + title + ", open=" + open
				+ ", closed=" + closed + ", parent=" + parent + "]";
	}

   

}