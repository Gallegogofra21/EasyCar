package com.salesianos.triana.dam.EasyCar.validacion.validadores;

import com.salesianos.triana.dam.EasyCar.service.ConcesionarioService;
import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueNameConcesionario;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueNameValidatorConcesionario implements ConstraintValidator<UniqueNameConcesionario, String> {


    private final ConcesionarioService service;

    @Override
    public void initialize(UniqueNameConcesionario constraintAnnotation) { }

    @Override
    public boolean isValid(String nombre, ConstraintValidatorContext context) {
        return StringUtils.hasText(nombre) && !service.comprobarName(nombre);
    }
}
