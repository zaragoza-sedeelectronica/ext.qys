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

package org.sede.servicio.qys.dao;

import java.math.BigDecimal;

import org.sede.core.rest.Mensaje;
import org.sede.servicio.acceso.entity.Ciudadano;
import org.sede.servicio.qys.entity.db.Hbusers;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

/**
 * Interfaz Quejas y sugerencias
 *
 * @author Ayuntamiento Zaragoza
 *
 */
public interface HbUserGenericDao extends GenericDAO<Hbusers, BigDecimal> {

	public Mensaje importData(Ciudadano user, String email, String pass);
	
	
}