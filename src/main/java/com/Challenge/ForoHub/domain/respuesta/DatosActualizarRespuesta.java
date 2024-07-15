package com.Challenge.ForoHub.domain.respuesta;

public record DatosActualizarRespuesta(String mensaje,
                                       Long topico_id,
                                       Long usuario_id,
                                       Boolean solucion
) {
}
