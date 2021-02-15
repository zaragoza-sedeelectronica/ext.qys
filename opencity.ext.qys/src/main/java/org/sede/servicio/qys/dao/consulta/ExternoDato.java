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
