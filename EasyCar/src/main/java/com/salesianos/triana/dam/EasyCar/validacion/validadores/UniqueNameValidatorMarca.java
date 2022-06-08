package com.salesianos.triana.dam.EasyCar.validacion.validadores;

import com.salesianos.triana.dam.EasyCar.service.ConcesionarioService;
import com.salesianos.triana.dam.EasyCar.service.MarcaService;
import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueNameConcesionario;
import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueNameMarca;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueNameValidatorMarca implements ConstraintValidator<UniqueNameMarca, String> {


    private final MarcaService service;

    @Override
    public void initialize(UniqueNameMarca constraintAnnotation) { }

    @Override
    public boolean isValid(String nombre, ConstraintValidatorContext context) {
        return StringUtils.hasText(nombre) && !service.comprobarName(nombre);
    }
}
