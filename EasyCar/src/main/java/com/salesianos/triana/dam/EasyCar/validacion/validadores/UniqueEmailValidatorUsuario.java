package com.salesianos.triana.dam.EasyCar.validacion.validadores;

import com.salesianos.triana.dam.EasyCar.users.service.impl.UserEntityServiceImpl;
import com.salesianos.triana.dam.EasyCar.validacion.anotaciones.UniqueEmailUser;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidatorUsuario implements ConstraintValidator<UniqueEmailUser, String> {
    private final UserEntityServiceImpl service;

    @Override
    public void initialize(UniqueEmailUser constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return StringUtils.hasText(email) && !service.comprobarEmail(email);
    }
}
