CREATE TABLE cargo (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(30) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE chamados (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Colaboradores_id INTEGER UNSIGNED NOT NULL,
  computadores_id INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(id),
  INDEX chamados_FKIndex4(computadores_id)
);

CREATE TABLE colaboradores (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  cargo_id INTEGER UNSIGNED NOT NULL,
  nome VARCHAR(30) NOT NULL,
  descricao VARCHAR(100) NOT NULL,
  PRIMARY KEY(id),
  INDEX colaboradores_FKIndex1(cargo_id)
);

CREATE TABLE computadores (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  descricaoHardware VARCHAR(200) NOT NULL,
  decricaoSoftware VARCHAR(200) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE estados (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE mensagens (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  chamados_id INTEGER UNSIGNED NOT NULL,
  estados_id INTEGER UNSIGNED NOT NULL,
  conteudo VARCHAR(500) NOT NULL,
  horario DATE NOT NULL,
  PRIMARY KEY(id),
  INDEX mensagens_FKIndex1(estados_id),
  INDEX mensagens_FKIndex2(Chamados_id)
);


