package com.salesianos.triana.dam.EasyCar.validacion.anotaciones;

import com.salesianos.triana.dam.EasyCar.validacion.validadores.UniqueNameValidatorConcesionario;
import com.salesianos.triana.dam.EasyCar.validacion.validadores.UniqueNameValidatorTipo;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNameValidatorTipo.class)
@Documented
public @interface UniqueNameTipo {
    String message() default "El nombre del tipo debe ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
