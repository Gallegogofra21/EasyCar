package com.salesianos.triana.dam.EasyCar.validacion.validadores;

import com.salesianos.triana.dam.EasyCar.service.ConcesionarioService;
import com.salesianos.triana.dam.EasyCar.service.TipoService;
import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueNameConcesionario;
import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueNameTipo;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueNameValidatorTipo implements ConstraintValidator<UniqueNameTipo, String> {


    private final TipoService service;

    @Override
    public void initialize(UniqueNameTipo constraintAnnotation) { }

    @Override
    public boolean isValid(String nombre, ConstraintValidatorContext context) {
        return StringUtils.hasText(nombre) && !service.comprobarName(nombre);
    }
}
