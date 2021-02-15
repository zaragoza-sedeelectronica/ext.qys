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

package org.sede.servicio.qys.dao;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.sede.servicio.qys.entity.EntidadExterna;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

/**
 * Interfaz EntidadExternaGenericDAO
 *
 * @author Ayuntamiento Zaragoza
 *
 */
public interface EntidadExternaGenericDAO extends GenericDAO<EntidadExterna, BigDecimal>  {
	/**
	 * Metodo interfaz validar
	 * 
	 * @param registro
	 * @return
	 */
	public Set<ConstraintViolation<Object>> validar(Object registro);
}
