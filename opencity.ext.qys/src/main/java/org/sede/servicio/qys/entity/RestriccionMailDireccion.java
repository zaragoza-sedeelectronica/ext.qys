package org.sede.servicio.qys.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Interfaz RestriccionMailDireccion
 *
 * @author Ayuntamiento Zaragoza
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { RestriccionMailDireccionValidator.class })
@Documented
public @interface RestriccionMailDireccion {
	String message() default "Debe incluir un correo o un teléfono como forma de contacto";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
