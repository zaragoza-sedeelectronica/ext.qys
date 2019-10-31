package org.sede.servicio.qys.dao.consulta;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase datos de barrio
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name = "barrio")
public class BarrioDato {
	/**
	 *  variable nombre
	 */
    private String nombre;
    /**
	 *  variable numero
	 */
    private int numero;

    /**
     * Constructor
     */
    public BarrioDato() {
        super();
    }

    /**
     * Constructor
     * 
     * @param nombre
     * @param numero
     */
    public BarrioDato(String nombre, int numero) {
        super();
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "BarrioDato [nombre=" + nombre + ", numero=" + numero + "]";
    }

}
