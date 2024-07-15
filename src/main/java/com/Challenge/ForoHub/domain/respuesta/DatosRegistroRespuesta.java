package com.Challenge.ForoHub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(@NotBlank(message = "El Mensaje es obligatorio")
                                     String mensaje,
                                     @NotNull(message = "El Topico_id es obligatorio")
                                     Long topico_id,
                                     @NotNull(message = "El Usuario_id es obligatorio")
                                     Long usuario_id
) {
}
