package org.sede.servicio.citaprevia.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class RestriccionMailTelefonoValidator implements ConstraintValidator<RestriccionMailTelefono, Cita> {
	//@Override
    public void initialize(RestriccionMailTelefono constraintAnnotation) {
    }

   // @Override
    public boolean isValid(Cita solicitud, ConstraintValidatorContext context) {
        if ( solicitud == null ) {
            return true;
        }
        return !StringUtils.isEmpty(solicitud.getEmail()) 
        		|| !StringUtils.isEmpty(solicitud.getTelefono());
    }
}
