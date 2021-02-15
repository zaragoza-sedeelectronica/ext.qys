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
