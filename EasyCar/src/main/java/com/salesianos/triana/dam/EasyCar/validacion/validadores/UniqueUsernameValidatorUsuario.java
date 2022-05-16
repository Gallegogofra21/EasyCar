package com.salesianos.triana.dam.EasyCar.validacion.validadores;

import com.salesianos.triana.dam.EasyCar.users.service.UserEntityService;
import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueUsernameUser;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUsernameValidatorUsuario implements ConstraintValidator<UniqueUsernameUser, String> {

    private final UserEntityService service;

    @Override
    public void initialize(UniqueUsernameUser constraintAnnotation) {

    }

    @Override
    public boolean isValid(String nombre, ConstraintValidatorContext context) {
        return StringUtils.hasText(nombre) && !service.comprobarUsername(nombre);
    }
}
