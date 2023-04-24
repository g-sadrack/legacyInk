CREATE TABLE
  estudio (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(120) NOT NULL,
    cnpj VARCHAR(20) NOT NULL,
    razao_social VARCHAR(255) NOT NULL,
    redes_sociais VARCHAR(40),
    horario DATE NOT NULL,
    endereco_cidade_id BIGINT NOT NULL,
    endereco_cep VARCHAR(8) NOT NULL,
    endereco_complemento VARCHAR(150),
    endereco_logradouro VARCHAR(150),
    endereco_numero VARCHAR(10),
    endereco_rua VARCHAR(120)
  )ENGINE = InnoDB;

CREATE TABLE
  estudio_tatuadores (
    estudio_id BIGINT NOT NULL,
    tatuador_id BIGINT NOT NULL,
    PRIMARY KEY (estudio_id, tatuador_id),
    FOREIGN KEY (estudio_id) REFERENCES estudio(id),
    FOREIGN KEY (tatuador_id) REFERENCES tatuador(id)
  )ENGINE = InnoDB;

CREATE TABLE
  estudio_clientes (
    estudio_id BIGINT NOT NULL,
    cliente_id BIGINT NOT NULL,
    PRIMARY KEY (estudio_id, cliente_id),
    FOREIGN KEY (estudio_id) REFERENCES estudio(id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
  )ENGINE = InnoDB;