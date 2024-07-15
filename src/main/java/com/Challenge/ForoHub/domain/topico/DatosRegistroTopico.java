package com.Challenge.ForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(@NotBlank(message = "El Título es obligatorio")
                                  String titulo,
                                  @NotBlank(message = "El Mensaje es obligatorio")
                                  String mensaje,
                                  @NotBlank(message = "El Nombre del curso es obligatorio")
                                  String nombreCurso,
                                  @NotNull(message = "El Usuario_id es obligatorio")
                                  Long usuario_id
) {

    public DatosRegistroTopico(String titulo, String mensaje, String nombreCurso, Long usuario_id){
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.nombreCurso = nombreCurso;
        this.usuario_id = usuario_id;
    }
}
