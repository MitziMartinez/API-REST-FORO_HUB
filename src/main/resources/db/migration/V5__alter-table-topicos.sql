alter table topicos

    add constraint fk_topico_curso foreign key(curso_id) references cursos(id),
    add constraint fk_topico_usuario foreign key(usuario_id) references usuarios(id) ON DELETE CASCADE;