package com.Challenge.ForoHub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListadoRespuestaEnTopico(Long id,
                                            String mensaje,
                                            LocalDateTime fecha_creacion,
                                            String autor,
                                            Boolean solucion
) {
    public DatosListadoRespuestaEnTopico(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFecha_creacion(),
                respuesta.getAutor().getNombre(), respuesta.getSolucion());
    }
}
