==================================================================
=================USUARIO==========================================
==================================================================

DELETE FROM USUARIO;

DROP SEQUENCE idUsuario_SEQUENCE;

CREATE SEQUENCE idUsuario_SEQUENCE 
MINVALUE 1
MAXVALUE 100000
INCREMENT BY 1
START WITH 1;

INSERT INTO USUARIO (IDUSUARIO,NOME,SOBRENOME,TELEFONE,EMAIL,SENHA) VALUES (idUsuario_SEQUENCE.NEXTVAL,'admin','master','0800100001','admin@mygarage.com','admin');
INSERT INTO USUARIO (IDUSUARIO,NOME,SOBRENOME,TELEFONE,EMAIL,SENHA) VALUES (idUsuario_SEQUENCE.NEXTVAL,'Juliano','Zapelini','554199996860','zapelini@mygarage.com','juliano');
INSERT INTO USUARIO (IDUSUARIO,NOME,SOBRENOME,TELEFONE,EMAIL,SENHA) VALUES (idUsuario_SEQUENCE.NEXTVAL,'Tiago','Mazzer','554188886860','mazzer@mygarage.com','tiago');
INSERT INTO USUARIO (IDUSUARIO,NOME,SOBRENOME,TELEFONE,EMAIL,SENHA) VALUES (idUsuario_SEQUENCE.NEXTVAL,'admin','master','0800100001','admin@mygarage.com','admin');
INSERT INTO USUARIO (IDUSUARIO,NOME,SOBRENOME,TELEFONE,EMAIL,SENHA) VALUES (idUsuario_SEQUENCE.NEXTVAL,'Joao','Batista','554188889999','joao@mygarage.com','joao');
INSERT INTO USUARIO (IDUSUARIO,NOME,SOBRENOME,TELEFONE,EMAIL,SENHA) VALUES (idUsuario_SEQUENCE.NEXTVAL,'Maria','Silva','554133299999','maria@mygarage.com','maria');
INSERT INTO USUARIO (IDUSUARIO,NOME,SOBRENOME,TELEFONE,EMAIL,SENHA) VALUES (idUsuario_SEQUENCE.NEXTVAL,'Pedro','Rocha','554133291234','pedro@mygarage.com','pedro');
INSERT INTO USUARIO (IDUSUARIO,NOME,SOBRENOME,TELEFONE,EMAIL,SENHA) VALUES (idUsuario_SEQUENCE.NEXTVAL,'Ana Paula','Pereira','554133451234','anapaula@mygarage.com','anapaula');

SELECT * FROM USUARIO;

==================================================================
=====================PROXIMA TABELA==============================
==================================================================