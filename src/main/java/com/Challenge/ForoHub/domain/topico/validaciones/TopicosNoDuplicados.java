package com.Challenge.ForoHub.domain.topico.validaciones;

import com.Challenge.ForoHub.domain.topico.DatosRegistroTopico;
import com.Challenge.ForoHub.domain.topico.Topico;
import com.Challenge.ForoHub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicosNoDuplicados implements ValidacionDeTopicos{
    @Autowired
    public TopicoRepository topicoRepository;

    public void validar(DatosRegistroTopico datosRegistroTopico){

        List<Topico> topicos = topicoRepository.findByTitulo(datosRegistroTopico.titulo());
        topicos.stream()
                .filter(t -> t.getMensaje().equalsIgnoreCase(datosRegistroTopico.mensaje()))
                .findFirst()
                .ifPresent(t -> {
                    throw new ValidationException("ERROR TOPICO DUPLICADO");
                });

    }
}

