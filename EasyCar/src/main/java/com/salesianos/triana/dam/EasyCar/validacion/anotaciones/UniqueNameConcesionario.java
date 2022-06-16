package com.salesianos.triana.dam.EasyCar.validacion.anotaciones;

import com.salesianos.triana.dam.EasyCar.validacion.validadores.UniqueNameValidatorConcesionario;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNameValidatorConcesionario.class)
@Documented
public @interface UniqueNameConcesionario {
    String message() default "El nombre del concesionario debe ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
