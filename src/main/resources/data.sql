insert into PRODUCTO(ID_PRODUCTO, NOMBRE, CANTIDAD, PRECIO_UNITARIO, PRECIO_IVA) values(1, 'Zapatos', 12, 100, 110 );


insert into AUTOR(AUTOR_ID, NOMBRE) values(1, 'Ricardo Piglia' );
insert into AUTOR(AUTOR_ID, NOMBRE) values(2, 'Yuri Herrera' );


insert into LIBRO(LIBRO_ID, ISBN, TITULO, PRECIO, AUTOR_ID) values(1, '2545784532', 'Detectives Salvajes', 150, 1 );
insert into LIBRO(LIBRO_ID, ISBN, TITULO, PRECIO, AUTOR_ID) values(2, '5421758468', 'Planeta Quemada', 950, 1 );
insert into LIBRO(LIBRO_ID, ISBN, TITULO, PRECIO, AUTOR_ID) values(3, '9452124347', 'Los trabajos del reino', 500, 2 );



insert into APP_USER(ID, PASSWORD, USERNAME) values(1, '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'admin');
insert into USER_ROLE(APP_USER_ID, ROLE) values(1, 'ADMIN');


--insert into APP_USER(USERNAME, ENABLED, PASSWORD ) values('admin', 1, '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G');
--insert into USER_ROLE(USER_ROLE_ID, ROLE, USERNAME) values(1, 'ADMIN', 'admin');






