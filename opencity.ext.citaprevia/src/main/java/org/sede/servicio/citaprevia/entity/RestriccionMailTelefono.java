package org.sede.servicio.citaprevia.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

//@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { RestriccionMailTelefonoValidator.class })
@Documented
public @interface RestriccionMailTelefono {
	String message() default "Debe incluir el teléfono o el correo como forma de contacto";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
