INSERT INTO usuario  (id, username) VALUES (nextval ('usuario_seq'), 'Rafahm03');
INSERT INTO usuario (id, username) VALUES (nextval ('usuario_seq'), 'RuizloCar');
INSERT INTO usuario (id, username) VALUES (nextval ('usuario_seq'), 'SoyYo' );

INSERT INTO ubicaciones (id, nombre) VALUES (nextval('ubicaciones_seq'), 'Ubicacion1');

INSERT INTO incidencias (id, fecha_incidencia, titulo, descripcion, urgencia, ubicacion_id, estado)
VALUES (nextval('incidencias_seq'), '2025-01-29 13:10:00' ,'incidencia1', 'esta incidencia es de prueba',true, 1, 'ABIERTA');

INSERT INTO alumno (id) VALUES (1);

INSERT INTO tecnico (id) VALUES (51);

INSERT INTO personal (id, tipo) VALUES (101, 'PROFESOR');

