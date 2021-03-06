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

package org.sede.servicio.qys.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
/**
 * Clase que implemente la interfaz RestriccionMailDireccion
 *
 * @author Ayuntamiento Zaragoza
 *
 */
public class RestriccionMailDireccionValidator implements ConstraintValidator<RestriccionMailDireccion, SolicitudInformacionPublica> {
	/**
	 * Metodo initialize
	 * 
	 */
	@Override
    public void initialize(RestriccionMailDireccion constraintAnnotation) {
    }

	/**
	 * Metodo isValid
	 * 
	 */
    @Override
    public boolean isValid(SolicitudInformacionPublica solicitud, ConstraintValidatorContext context) {
        if (solicitud == null) {
            return true;
        }
        return !StringUtils.isEmpty(solicitud.getMail()) 
        		|| !StringUtils.isEmpty(solicitud.getTelefono()) 
        		;
    }
}
