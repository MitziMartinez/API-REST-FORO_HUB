package com.Challenge.ForoHub.domain.usuario;

import com.Challenge.ForoHub.domain.usuario.validaciones.ValidacionDeUsuario;
import com.Challenge.ForoHub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidacionDeUsuario> validaciones;

    public ResponseEntity<DatosRespuestaUsuario> registarUsuario(DatosRegistroUsuario datosRegistroUsuario,
                                                                 UriComponentsBuilder uriComponentsBuilder) {
        var usuario = new Usuario(datosRegistroUsuario);
        validaciones.forEach(v -> v.validar(datosRegistroUsuario));
        Usuario usuarioConId = usuarioRepository.save(usuario);

        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuarioConId);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioConId.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(DatosActualizarUsuario datosActualizarUsuario, Long id, UriComponentsBuilder uriComponentsBuilder) {

        if (usuarioRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado. Verifique el id.");
        }

        Usuario usuario = usuarioRepository.getReferenceById(id);

        DatosRegistroUsuario datosRegistroUsuario = realizarCopiaActualizado(usuario, datosActualizarUsuario);

        validaciones.forEach(v -> v.validar(datosRegistroUsuario));

        if (datosActualizarUsuario.nombre() != null){
            usuario.setNombre(datosActualizarUsuario.nombre());
        }
        if (datosActualizarUsuario.email() != null){
            usuario.setEmail(datosActualizarUsuario.email());
        }
        if (datosActualizarUsuario.contrasena() != null){
            usuario.setContrasena(datosActualizarUsuario.contrasena());
        }
        if (datosActualizarUsuario.tipoDeUsuario() != null){
            usuario.setTipoDeUsario(TipoDeUsario.fromString(datosActualizarUsuario.tipoDeUsuario()));
        }

        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaUsuario);

    }

    public ResponseEntity<Page> listarUsuarios(Pageable paginacion) {

        return ResponseEntity.ok(usuarioRepository.listarUsuarios(paginacion)
                .map(DatosRespuestaUsuario::new));
    }

    public ResponseEntity eliminarUsuario(Long id) {

        if (usuarioRepository.findById(id).isEmpty()){
            throw new ValidacionDeIntegridad("El usuario no fue encontrado. Verifique el id.");
        }

        usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    private DatosRegistroUsuario realizarCopiaActualizado(Usuario usuario, DatosActualizarUsuario datosActualizarUsuario){
        String nombre = usuario.getNombre();
        String email = usuario.getEmail();
        String contrasena = usuario.getContrasena();
        String tipoDeUsuario = usuario.getTipoDeUsario().toString();

        if (datosActualizarUsuario.nombre() != null){
            nombre = datosActualizarUsuario.nombre();
        }
        if (datosActualizarUsuario.email() != null){
            email = datosActualizarUsuario.email();
        }
        if (datosActualizarUsuario.contrasena() != null){
            contrasena = datosActualizarUsuario.contrasena();
        }
        if (datosActualizarUsuario.tipoDeUsuario() != null){
            tipoDeUsuario = datosActualizarUsuario.tipoDeUsuario();
        }

        DatosRegistroUsuario datosRegistroUsuario = new DatosRegistroUsuario(nombre, email, contrasena, tipoDeUsuario);
        return datosRegistroUsuario;
    }
}
