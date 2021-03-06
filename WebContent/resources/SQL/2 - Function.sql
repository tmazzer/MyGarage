

--//// ATENCAO//////
-- Executar separadamente as functions



CREATE OR REPLACE FUNCTION COUNT_LIKES
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



create or replace FUNCTION COUNT_COMENTARIOS
(F_IDTIMELINE IN INTEGER)
 RETURN INTEGER
IS
  F_COUNT INTEGER;
BEGIN
    SELECT COUNT(*) INTO F_COUNT
    FROM TIMELINE_ACAO
    WHERE TIMELINE_IDTIMELINE = F_IDTIMELINE AND COMENTARIO IS NOT NULL;
  RETURN  F_COUNT;  
END COUNT_COMENTARIOS;