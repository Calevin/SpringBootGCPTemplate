insert into category_example
(name                           , description ) values
('Primera División de Argentina', 'Es el torneo de la primera categoría del fútbol masculino argentino, organizado desde 1893 por la Asociación del Fútbol Argentino'),
('Primera B Nacional'           , 'Cuyo nombre de fantasía es Primera Nacional —también conocida como Nacional B, B Nacional o Torneo Nacional B— es el campeonato de fútbol de segunda división de Argentina.'),
('Primera B'                    , 'También llamada Primera B Metropolitana, o informalmente B Metropolitana o B Metro) es la tercera categoría del fútbol argentino para los clubes directamente afiliados a la AFA.');

insert into entity_example
(name                         , category_example_id,  description ) values
('Club Atlético Boca Juniors' , 1 , 'Es una entidad deportiva argentina con sede en el barrio de La Boca, Buenos Aires. Fue fundado en dicho barrio el 3 de abril de 1905 por seis vecinos adolescentes hijos de italianos.' ),
('Club Atlético River Plate'  , 1 , 'Es una entidad polideportiva con sede en Buenos Aires, Argentina. Fue fundado el 25 de mayo de 1901 en el barrio de La Boca'                                                            ),
('Club Atlético Independiente', 1 , 'Conocido popularmente como Independiente o por su sigla CAI, es una entidad deportiva de Argentina de la ciudad de Avellaneda'                                                          ),
('Racing Club'                , 1 , 'Conocido como Racing Club de Avellaneda o simplemente Racing, es una entidad deportiva oriunda de Argentina, fundada el 25 de marzo de 1903 en la Ciudad de Avellaneda'                 ),
('Club Atlético Atlanta'      , 2 , 'Es una institución social, cultural y deportiva argentina, radicada en el barrio de Villa Crespo'                                                                                       ),
('Club Atlético Belgrano'     , 2 , 'Es una institución deportiva de la ciudad de Córdoba en Argentina. Fue fundado oficialmente un lunes 19 de marzo de 1905'                                                               ),
('Club Atlético San Miguel'   , 3 , 'Es un club cuya actividad de mayor referencia es el fútbol. Fue fundado el 7 de agosto de 1922. Tiene su sede social en el Partido de San Miguel.'                                      );

insert into user_roles (user_id, roles) values (1,'USER');
insert into user_roles (user_id, roles) values (1,'ADMIN');
--insert into user_roles (user_id, roles) values (2,'USER');

-- password: Admin1
insert into users (id, username, password, creation_date, last_date_password_modification)
values (1, 'admin', '$2a$10$vPaqZvZkz6jhb7U7k/V/v.5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- password: User1
--insert into users (id, username, password, creation_date, last_date_password_modification)
--values (2, 'user', '$2a$10$cIvIRhQBmF57i3cEtOxi8OyOqPD19KcRNcHrj1Goi9y/3kp5YfZim', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
