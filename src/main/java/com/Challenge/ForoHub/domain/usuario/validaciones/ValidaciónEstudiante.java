package com.Challenge.ForoHub.domain.usuario.validaciones;

import com.Challenge.ForoHub.domain.usuario.DatosRegistroUsuario;
import com.Challenge.ForoHub.domain.usuario.TipoDeUsario;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidaciónEstudiante implements ValidacionDeUsuario {
    @Override
    public void validar(DatosRegistroUsuario datosRegistroUsuario) {
        if (!datosRegistroUsuario.tipoDeUsario().equalsIgnoreCase(String.valueOf(TipoDeUsario.ESTUDIANTE))){
            throw new ValidationException("El usuario se debe registrar como ESTUDIANTE");
        }
    }
}
