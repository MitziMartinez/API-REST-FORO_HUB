alter table respuestas

    add constraint fk_respuesta_usuario_id foreign key(usuario_id) references usuarios(id) ON DELETE CASCADE,
    add constraint fk_respuesta_topico_id foreign key(topico_id) references topicos(id) ON DELETE CASCADE;