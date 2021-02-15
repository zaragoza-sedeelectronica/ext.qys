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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase datos de externo
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@XmlRootElement(name = "externo")
public class ExternoDato {
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
     * 
     */
    public ExternoDato() {
        super();
    }

    /**
     * Constructor
     * 
     * @param nombre
     * @param numero
     */
    public ExternoDato(String nombre, int numero) {
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
