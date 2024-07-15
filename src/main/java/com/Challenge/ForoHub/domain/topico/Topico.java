package com.Challenge.ForoHub.domain.topico;

import com.Challenge.ForoHub.domain.curso.Curso;
import com.Challenge.ForoHub.domain.respuesta.Respuesta;
import com.Challenge.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Table(name="topicos")
@Entity(name="Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    @Enumerated(EnumType.STRING)
    private EstadoDelTopico estadoDelTopico;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario autor;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    List<Respuesta> respuestas;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaFormateada = ahora.format(formateador);
        this.fecha_creacion = LocalDateTime.parse(horaFormateada, formateador);

        this.estadoDelTopico = EstadoDelTopico.SIN_SOLUCION;

    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico, Curso curso, Usuario autor){
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null){
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (curso != null){
            this.curso = curso;
        }
        if (autor != null){
            this.autor = autor;
        }
        if (datosActualizarTopico.estado() != null){
            this.estadoDelTopico = EstadoDelTopico.fromString(datosActualizarTopico.estado());
        }
    }

}
