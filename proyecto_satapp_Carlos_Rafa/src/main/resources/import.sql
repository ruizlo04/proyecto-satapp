INSERT INTO usuario (id, username) VALUES (nextval ('usuario_seq'), 'RuizloCar');
INSERT INTO usuario (id, username) VALUES (nextval ('usuario_seq'), 'SoyYo' );
INSERT INTO usuario (id, username) VALUES (nextval ('usuario_seq'), 'User1234');
INSERT INTO usuario (id, username) VALUES (nextval ('usuario_seq'), 'User12345');

INSERT INTO ubicaciones (id, nombre) VALUES (nextval('ubicaciones_seq'), 'Ubicacion1');
INSERT INTO ubicaciones (id, nombre) VALUES (nextval('ubicaciones_seq'), 'Ubicacion2');
INSERT INTO equipos (id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipos_seq'), 'Equipo1', '["Característica 1", "Característica 2", "Característica 3"]', 1);
INSERT INTO equipos (id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipos_seq'), 'Equipo2', '["Característica 1", "Característica 2", "Característica 3"]', 1);
INSERT INTO equipos (id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipos_seq'), 'Equipo3', '["Característica 1", "Característica 2", "Característica 3"]', 51);

INSERT INTO incidencias (id, fecha_incidencia, titulo, descripcion, urgencia, ubicacion_id, estado, equipo_id, usuario_id)
VALUES (nextval('incidencias_seq'), '2025-01-29 13:10:00' ,'incidencia1', 'esta incidencia es de prueba',true, 1, 'ABIERTA', 1, 1);
INSERT INTO incidencias (id, fecha_incidencia, titulo, descripcion, urgencia, ubicacion_id, estado, equipo_id, usuario_id)
VALUES (nextval('incidencias_seq'), '2025-01-29 13:10:00' ,'incidencia2', 'esta incidencia es de prueba',true, 1, 'ABIERTA', 51, 151);

INSERT INTO alumno (id) VALUES (1);
INSERT INTO alumno (id) VALUES (151);


INSERT INTO tecnico (id) VALUES (51);

INSERT INTO personal (id, tipo) VALUES (101, 'PROFESOR');

INSERT INTO historico_cursos (id, curso_escolar, curso, alumno_id) VALUES (nextval ('historico_cursos_seq'), '2024-2025', '2ºDAM', 1);
INSERT INTO historico_cursos (id, curso_escolar, curso, alumno_id) VALUES (nextval ('historico_cursos_seq'), '2023-2024', '1ºDAM', 1);
INSERT INTO historico_cursos (id, curso_escolar, curso, alumno_id) VALUES (nextval ('historico_cursos_seq'),'2024-2025', '2ºDAM', 151);


