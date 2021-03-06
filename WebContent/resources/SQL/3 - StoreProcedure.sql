--//// ATENCAO//////
-- Executar separadamente:

create or replace PACKAGE types
as
TYPE ref_cur is REF CURSOR;
END;

--------------------------------------------------------


create or replace PROCEDURE MONTATIMELINE
	(C1 OUT Types.ref_cur,
  P_USUARIO IN INTEGER)
IS
	P_CODIGOAMIGO INTEGER;	
BEGIN
	select idCodigo_amigo into P_CODIGOAMIGO from codigo_amigo where usuario_idusuario = P_USUARIO;
  OPEN C1 FOR
    SELECT  T.IDTIMELINE "IDTIMELINE", 
            R.USUARIO_IDUSUARIO "USUARIO_IDUSUARIO",
            U.NOME,
            T.DATACADASTRO "DATACADASTRO",
            T.DESCRICAO "DESCRICAO"
    FROM RELACIONAMENTO R
    INNER JOIN TIMELINE T ON R.USUARIO_IDUSUARIO = T.USUARIO_IDUSUARIO
    INNER JOIN USUARIO  U ON R.USUARIO_IDUSUARIO = U.IDUSUARIO
    WHERE R.CODIGO_AMIGO_IDCODIGO_AMIGO = P_CODIGOAMIGO
    ORDER BY T.DATACADASTRO DESC;
END;