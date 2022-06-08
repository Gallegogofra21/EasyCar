package com.salesianos.triana.dam.EasyCar.validacion.anotaciones;

import com.salesianos.triana.dam.EasyCar.validacion.validadores.UniqueNameValidatorConcesionario;
import com.salesianos.triana.dam.EasyCar.validacion.validadores.UniqueNameValidatorMarca;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNameValidatorMarca.class)
@Documented
public @interface UniqueNameMarca {
    String message() default "El nombre de la marca debe ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}