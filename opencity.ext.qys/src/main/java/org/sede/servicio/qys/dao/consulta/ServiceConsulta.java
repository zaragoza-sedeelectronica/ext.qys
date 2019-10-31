package org.sede.servicio.qys.dao.consulta;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase datos de servicio de consulta
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name = "service-consulta")
public class ServiceConsulta {
	/**
	 *  variable id
	 */
    private int id;
    /**
	 *  variable title
	 */
    private String title;
    /**
	 *  variable total
	 */
    private int total;
    /**
	 *  variable resueltas
	 */
    private int resueltas;
    /**
	 *  variable pendientes
	 */
    private int pendientes;
    /**
	 *  variable pendientes mas de un año 
	 */
    private int pendientes_mas_1y;
    /**
	 *  variable barrio resuelta 
	 */
    List<BarrioDato> barrio_resuelta;
    /**
	 *  variable barrio pendiente 
	 */
    List<BarrioDato> barrio_pendiente;
    /**
	 *  variable entidad resuelta 
	 */
    List<ExternoDato> entidad_resuelta;
    /**
	 *  variable entidad pendiente 
	 */
    List<ExternoDato> entidad_pendiente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getResueltas() {
        return resueltas;
    }

    public void setResueltas(int resueltas) {
        this.resueltas = resueltas;
    }

    public int getPendientes_mas_1y() {
        return pendientes_mas_1y;
    }

    public void setPendientes_mas_1y(int pendientes_mas_1y) {
        this.pendientes_mas_1y = pendientes_mas_1y;
    }

    /**
     * Constructor
     * 
     */
    public ServiceConsulta() {
        super();
    }

    public List<ExternoDato> getEntidad_resuelta() {
        return entidad_resuelta;
    }

    public void setEntidad_resuelta(List<ExternoDato> entidad_resuelta) {
        this.entidad_resuelta = entidad_resuelta;
    }

    public List<ExternoDato> getEntidad_pendiente() {
        return entidad_pendiente;
    }

    public void setEntidad_pendiente(List<ExternoDato> entidad_pendiente) {
        this.entidad_pendiente = entidad_pendiente;
    }

    public List<BarrioDato> getBarrio_resuelta() {
        return barrio_resuelta;
    }

    public void setBarrio_resuelta(List<BarrioDato> barrio_resuelta) {
        this.barrio_resuelta = barrio_resuelta;
    }

    public List<BarrioDato> getBarrio_pendiente() {
        return barrio_pendiente;
    }

    public void setBarrio_pendiente(List<BarrioDato> barrio_pendiente) {
        this.barrio_pendiente = barrio_pendiente;
    }

    /**
     * Constructor
     * 
     * @param id
     * @param title
     * @param total
     * @param resueltas
     * @param pendientes_mas_1y
     * @param barrio_resuelta
     * @param barrio_pendiente
     * @param externo_resuelta
     * @param externo_pendiente
     */
    public ServiceConsulta(int id, String title, int total, int resueltas,
                           int pendientes_mas_1y, List<BarrioDato> barrio_resuelta,
                           List<BarrioDato> barrio_pendiente,
                           List<ExternoDato> externo_resuelta,
                           List<ExternoDato> externo_pendiente) {
        super();
        this.id = id;
        this.title = title;
        this.total = total;
        this.resueltas = resueltas;
        this.pendientes_mas_1y = pendientes_mas_1y;
        this.barrio_resuelta = barrio_resuelta;
        this.barrio_pendiente = barrio_pendiente;
        this.entidad_resuelta = externo_resuelta;
        this.entidad_pendiente = externo_pendiente;
    }

    public int getPendientes() {
		return pendientes;
	}

	public void setPendientes(int pendientes) {
		this.pendientes = pendientes;
	}

	public ServiceConsulta(int id, String title, int total) {
        super();
        this.id = id;
        this.title = title;
        this.total = total;
    }

    @Override
    public String toString() {
        return "ServiceConsulta [id=" + id + ", title=" + title + ", total="
                + total + ", resueltas=" + resueltas + ", pendientes_mas_1y="
                + pendientes_mas_1y + ", barrio_resuelta=" + barrio_resuelta
                + ", barrio_pendiente=" + barrio_pendiente
                + ", entidad_resuelta=" + entidad_resuelta
                + ", entidad_pendiente=" + entidad_pendiente + "]";
    }


}

