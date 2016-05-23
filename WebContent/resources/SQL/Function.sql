create or replace FUNCTION COUNT_LIKES
(F_IDTIMELINE IN INTEGER)
 RETURN INTEGER
IS
  F_COUNT INTEGER;
BEGIN
    SELECT COUNT(*) INTO F_COUNT
    FROM TIMELINE_ACAO
    WHERE TIMELINE_IDTIMELINE = F_IDTIMELINE AND CURTIR = 'S';
  RETURN  F_COUNT;  
END COUNT_LIKES;



create or replace Function consulta_nome
(p_id_funcionario in integer)
 return varchar
IS
  p_nome varchar2(20);
BEGIN
SELECT nome_funcionario into p_nome
  FROM salario
 WHERE id_pessoa = p_id_funcionario;
 return  P_nome;
END consulta_nome;