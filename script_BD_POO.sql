CREATE TABLE Cargo (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nomeCargo VARCHAR NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE Chamados (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Colaboradores_id INTEGER UNSIGNED NOT NULL,
  Computadores_id INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(id),
  INDEX Chamados_FKIndex4(Computadores_id)
);

CREATE TABLE Colaboradores (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Cargo_id INTEGER UNSIGNED NOT NULL,
  nomeColaborador VARCHAR NOT NULL,
  descricaoColaborador VARCHAR NOT NULL,
  PRIMARY KEY(id),
  INDEX Colaboradores_FKIndex1(Cargo_id)
);

CREATE TABLE Computadores (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  descricaoHardware VARCHAR NOT NULL,
  decricaoSoftware VARCHAR NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE Estados (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE Mensagens (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Chamados_id INTEGER UNSIGNED NOT NULL,
  Estados_id INTEGER UNSIGNED NOT NULL,
  conteudoMensagem VARCHAR NOT NULL,
  horarioMensagem DATE NOT NULL,
  PRIMARY KEY(id),
  INDEX Mensagns_FKIndex1(Estados_id),
  INDEX Mensagns_FKIndex2(Chamados_id)
);


