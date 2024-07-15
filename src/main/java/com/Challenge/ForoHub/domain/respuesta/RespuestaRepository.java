package com.Challenge.ForoHub.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    @Query("SELECT r FROM Respuesta r ORDER BY r.fecha_creacion ASC")
    Page<Respuesta> listarRespuestas(Pageable paginacion);

    @Modifying
    @Query("delete from Respuesta respuesta where respuesta.id=:id")
    void borrarRespuesta(Long id);
}
