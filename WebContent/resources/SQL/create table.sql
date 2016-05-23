
CREATE TABLE Usuario (
  idUsuario INTEGER NOT NULL,
  nome VARCHAR2(50) NOT NULL,
  sobrenome VARCHAR2(50) NULL,
  foto VARCHAR2(150) NULL,
  telefone VARCHAR2(50) NULL,
  email VARCHAR2(50) NOT NULL,
  senha VARCHAR2(10) NOT NULL,
  CONSTRAINT pk_idUsuario PRIMARY KEY(idUsuario)
);

CREATE SEQUENCE idUsuario_SEQUENCE 
MINVALUE 1
MAXVALUE 100000
INCREMENT BY 1
START WITH 1;

CREATE TABLE ADMINISTRADOR(
  idUsuario INTEGER NOT NULL,
  nome VARCHAR2(50) NOT NULL,
  sobrenome VARCHAR2(50) NULL,
  foto VARCHAR2(150) NULL,
  telefone VARCHAR2(50) NULL,
  email VARCHAR2(50) NOT NULL,
  senha VARCHAR2(10) NOT NULL,
  CPF VARCHAR2(14) NOT NULL,
  CONSTRAINT pk_adm_idUsuario PRIMARY KEY(idUsuario)
);

CREATE SEQUENCE ADMUSUARIO_SEQUENCE
MINVALUE 1
MAXVALUE 100000
INCREMENT BY 1
START WITH 1;


CREATE TABLE TpCombust (
  idTpCombust INTEGER NOT NULL,
  descricao VARCHAR2(50) NULL,
  CONSTRAINT pk_idTpCombust PRIMARY KEY(idTpCombust)
);


CREATE TABLE CARRO 
(
  idCarro INTEGER NOT NULL,
  TpCombust_idTpCombust INTEGER NOT NULL,
  Usuario_idUsuario INTEGER NOT NULL,
  apelidoCarro VARCHAR2(50) NULL,
  marca VARCHAR2(50) NULL,
  modelo VARCHAR2(50) NULL,
  anoFabricacao VARCHAR2(50) NULL,
  anoModelo VARCHAR2(50) NULL,
  cor VARCHAR2(50) NULL,
  placa VARCHAR2(50) NULL,
  kilometragem INTEGER NULL,
  foto VARCHAR2(150) NULL,
  CONSTRAINT pk_idCarro PRIMARY KEY (idCarro),
  CONSTRAINT fk_Usuario_idUsuario FOREIGN KEY (Usuario_idUsuario) REFERENCES USUARIO (idUsuario),
  CONSTRAINT fk_TpCombust_idTpCombust FOREIGN KEY (TpCombust_idTpCombust) REFERENCES TPCOMBUST (idTpCombust)
);

CREATE SEQUENCE idCarro_SEQUENCE 
MINVALUE 1
MAXVALUE 100000
INCREMENT BY 1
START WITH 1;

CREATE TABLE Acessorios (
  idAcessorios INTEGER NOT NULL,
  Carro_idCarro INTEGER NOT NULL,
  nome VARCHAR2(50) NOT NULL,
  descricao VARCHAR2(500) NULL,
  marca VARCHAR2(50) NULL,
  modelo VARCHAR2(50) NULL,
  foto VARCHAR2(150) NULL,
  CONSTRAINT pk_idAcessorios PRIMARY KEY(idAcessorios),
  CONSTRAINT fk_Carro_idCarro FOREIGN KEY(Carro_idCarro) REFERENCES Carro (idCarro)
);

CREATE SEQUENCE  IDACESSORIOS_SEQUENCE 
MINVALUE 1
MAXVALUE 100000
INCREMENT BY 1
START WITH 1;


CREATE TABLE OpcionaisCarro (
  Carro_idCarro INTEGER NOT NULL,
  alarme VARCHAR2(1) NULL,
  abs VARCHAR2(1) NULL,
  airBag VARCHAR2(1) NULL,
  arQuente VARCHAR2(1) NULL,
  arCondicionado VARCHAR2(1) NULL,
  bancoDeCouro VARCHAR2(1) NULL,
  centralMultimidia VARCHAR2(1) NULL,
  desembTraseiro_2 VARCHAR2(1) NULL,
  direcaoEletrica VARCHAR2(1) NULL,
  farolNeblina VARCHAR2(1) NULL,
  rodaLigaLeve VARCHAR2(1) NULL,
  som VARCHAR2(1) NULL,
  tetoSolar VARCHAR2(1) NULL,
  tavaEletrica VARCHAR2(1) NULL,
  desembTraseiro VARCHAR2(1) NULL,
  volanteCouro VARCHAR2(1) NULL,
  cambioAutomatico VARCHAR2(1) NULL,
  CONSTRAINT pk_Carro_idCarro PRIMARY KEY(Carro_idCarro),
  CONSTRAINT fk2_Carro_idCarro FOREIGN KEY(Carro_idCarro) REFERENCES Carro (idCarro)
);

CREATE TABLE Codigo_amigo (
  idCodigo_amigo INTEGER NOT NULL,
  Usuario_idUsuario INTEGER NOT NULL,
  CONSTRAINT pk_idCodigo_amigo PRIMARY KEY(idCodigo_amigo),
  CONSTRAINT fk2_Usuario_idUsuario FOREIGN KEY(Usuario_idUsuario) REFERENCES Usuario (idUsuario)
);

CREATE SEQUENCE IDCODIGO_AMIGO_SEQUENCE 
MINVALUE 1
MAXVALUE 100000
INCREMENT BY 1
START WITH 1;

CREATE TABLE tipo_grupo (
  idtipo_grupo INTEGER NOT NULL,
  desc_tp_grupo VARCHAR2(50) NOT NULL,
  CONSTRAINT pk_idtipo_grupo PRIMARY KEY(idtipo_grupo)
);

CREATE TABLE RELACIONAMENTO (
  idRELACIONAMENTO INTEGER NOT NULL,
  Codigo_amigo_idCodigo_amigo INTEGER NOT NULL,
  Usuario_idUsuario INTEGER NOT NULL,
  tipo_grupo_idtipo_grupo INTEGER NOT NULL,
  dtInicioRelacionamento DATE NULL,
  dtFimRelacionamento DATE NULL,
  statusRelacionamento CHAR NULL,
  CONSTRAINT pk_idRELACIONAMENTO PRIMARY KEY(idRELACIONAMENTO),
  CONSTRAINT fk3_Usuario_idUsuario FOREIGN KEY(Usuario_idUsuario) REFERENCES Usuario (idUsuario),
  CONSTRAINT fk_Codigo_amigo_idCodigo_amigo FOREIGN KEY(Codigo_amigo_idCodigo_amigo) REFERENCES Codigo_amigo (idCodigo_amigo)
);

CREATE SEQUENCE IDRELACIONAMENTO_SEQUENCE 
MINVALUE 1
MAXVALUE 100000
INCREMENT BY 1
START WITH 1;

CREATE TABLE Evento (
  idEvento INTEGER NOT NULL,
  REL_idRELACIONAMENTO INTEGER NOT NULL,
  Usuario_idUsuario INTEGER NOT NULL,
  tituloEvento VARCHAR2(50) NOT NULL,
  descricao VARCHAR2(500) NULL,
  localizacao VARCHAR2(50) NULL,
  dataEvento DATE NULL,
  CONSTRAINT pk_idEvento PRIMARY KEY(idEvento),
  CONSTRAINT fk4_Usuario_idUsuario FOREIGN KEY(Usuario_idUsuario) REFERENCES Usuario (idUsuario),
  CONSTRAINT fk_REL_idRELACIONAMENTO FOREIGN KEY(REL_idRELACIONAMENTO) REFERENCES RELACIONAMENTO (idRELACIONAMENTO)
);

CREATE TABLE Grupo_Discussao (
  idGrupo_DiscuSSao INTEGER NOT NULL,
  REL_idRELACIONAMENTO INTEGER NOT NULL,
  Usuario_idUsuario INTEGER NOT NULL,
  tituloGrupo VARCHAR2(50) NOT NULL,
  descricao VARCHAR2(500) NULL,
  dataCriacao DATE NULL,
  CONSTRAINT pk_idGrupo_DiscuSSao PRIMARY KEY(idGrupo_Discussao),
  CONSTRAINT fk5_Usuario_idUsuario FOREIGN KEY(Usuario_idUsuario) REFERENCES Usuario (idUsuario),
  CONSTRAINT fk2_REL_idRELACIONAMENTO FOREIGN KEY(REL_idRELACIONAMENTO) REFERENCES RELACIONAMENTO (idRELACIONAMENTO)
);

CREATE TABLE Grupo_Discussao_Acao (
  idGrupo_Discussao_Acao INTEGER NOT NULL,
  Usuario_idUsuario INTEGER NOT NULL,
  Grp_Disc_idGrupo_Discussao INTEGER NOT NULL,
  dataAcao DATE NULL,
  comentario VARCHAR2(500) NULL,
  CONSTRAINT pk_idGrupo_Discussao_Acao PRIMARY KEY(idGrupo_Discussao_Acao),
  CONSTRAINT fk6_Usuario_idUsuario FOREIGN KEY(Usuario_idUsuario) REFERENCES Usuario (idUsuario),
    CONSTRAINT fk_Grp_Disc_idGrupo_Discussao FOREIGN KEY(Grp_Disc_idGrupo_Discussao) REFERENCES Grupo_Discussao (idGrupo_DiscuSSao)
);

CREATE TABLE Timeline (
  idTimeline INTEGER NOT NULL,
  Usuario_idUsuario INTEGER NOT NULL,
  dataCadastro DATE NULL,
  descricao VARCHAR2(500) NULL,  
  CONSTRAINT pk_idTimeline PRIMARY KEY(idTimeline),
  CONSTRAINT fk7_Usuario_idUsuario FOREIGN KEY(Usuario_idUsuario) REFERENCES Usuario (idUsuario)
);


CREATE SEQUENCE IDTIMELINE_SEQUENCE 
MINVALUE 1
MAXVALUE 100000
INCREMENT BY 1
START WITH 1;

CREATE TABLE Timeline_acao (
  idTimeline_acao INTEGER NOT NULL,
  Usuario_idUsuario INTEGER NOT NULL,
  Timeline_idTimeline INTEGER NOT NULL,
  dataAcao DATE NULL,
  curtir VARCHAR2(1) NULL,
  comentario VARCHAR2(500) NULL,
  CONSTRAINT pk_idTimeline_acao PRIMARY KEY(idTimeline_acao),
  CONSTRAINT fk8_Usuario_idUsuario FOREIGN KEY(Usuario_idUsuario) REFERENCES Usuario (idUsuario),
  CONSTRAINT fk_Timeline_idTimeline FOREIGN KEY(Timeline_idTimeline) REFERENCES Timeline (idTimeline)
);

CREATE SEQUENCE IDTIMELINE_ACAO_SEQUENCE 
MINVALUE 1
MAXVALUE 100000
INCREMENT BY 1
START WITH 1;

CREATE TABLE LOG (
  idLog INTEGER NOT NULL,
  Usuario_idUsuario INTEGER NOT NULL,
  Timeline_idTimeline INTEGER NOT NULL,
  dataAcao DATE NULL,
  curtir VARCHAR2(1) NULL,
  comentario VARCHAR2(500) NULL,
  CONSTRAINT pk_idTimeline_acao PRIMARY KEY(idTimeline_acao),
  CONSTRAINT fk8_Usuario_idUsuario FOREIGN KEY(Usuario_idUsuario) REFERENCES Usuario (idUsuario),
  CONSTRAINT fk_Timeline_idTimeline FOREIGN KEY(Timeline_idTimeline) REFERENCES Timeline (idTimeline)
);


CREATE TABLE TRILHA_AUDITORIA (
  idTRILHA_AUDITORIA INTEGER NOT NULL,
  Usuario_idUsuario INTEGER NOT NULL,
  tabelaOrigem VARCHAR2(50) NOT NULL,
  dataAlteracao DATE NOT NULL,
  valorAnterior VARCHAR2(600) NULL,
  CONSTRAINT pk_idTRILHA_AUDITORIA PRIMARY KEY(idTRILHA_AUDITORIA),
  CONSTRAINT fk9_Usuario_idUsuario FOREIGN KEY(Usuario_idUsuario) REFERENCES Usuario (idUsuario)
);

CREATE SEQUENCE idTRILHA_AUDITORIA_SEQUENCE 
MINVALUE 1
MAXVALUE 100000
INCREMENT BY 1
START WITH 1;