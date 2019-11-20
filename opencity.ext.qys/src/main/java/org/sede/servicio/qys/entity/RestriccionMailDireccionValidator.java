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
	
    public void initialize(RestriccionMailDireccion constraintAnnotation) {
    }

	/**
	 * Metodo isValid
	 * 
	 */
    
    public boolean isValid(SolicitudInformacionPublica solicitud, ConstraintValidatorContext context) {
        if (solicitud == null) {
            return true;
        }
        return !StringUtils.isEmpty(solicitud.getMail()) 
        		|| !StringUtils.isEmpty(solicitud.getTelefono()) 
        		;
    }
}
