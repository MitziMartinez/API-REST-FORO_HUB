package com.Challenge.ForoHub.domain.topico;

public record DatosRespuestaTopico(Long id,
                                   String titulo,
                                   String mensaje,
                                   String fecha_creacion,
                                   String estado,
                                   Long curso_id,
                                   Long usuario_id) {
}
