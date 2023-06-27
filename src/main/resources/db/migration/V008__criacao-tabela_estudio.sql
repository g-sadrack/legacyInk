CREATE TABLE estudio (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(120) NOT NULL,
   telefone VARCHAR(20) NOT NULL,
   email VARCHAR(120) NOT NULL,
   cnpj VARCHAR(20) NOT NULL,
   razao_social VARCHAR(255) NOT NULL,
   redes_sociais VARCHAR(255) NULL,
   endereco_cep VARCHAR(255) NULL,
   endereco_rua VARCHAR(255) NULL,
   endereco_numero VARCHAR(255) NULL,
   endereco_complemento VARCHAR(255) NULL,
   endereco_logradouro VARCHAR(255) NULL,
   endereco_cidade_id BIGINT NOT NULL,
   CONSTRAINT pk_estudio PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE
  estudio_tatuadores (
    estudio_id BIGINT NOT NULL,
    tatuador_id BIGINT NOT NULL,
    PRIMARY KEY (estudio_id, tatuador_id),
    FOREIGN KEY (estudio_id) REFERENCES estudio(id),
    FOREIGN KEY (tatuador_id) REFERENCES tatuador(id)
  )ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE
  estudio_clientes (
    estudio_id BIGINT NOT NULL,
    cliente_id BIGINT NOT NULL,
    PRIMARY KEY (estudio_id, cliente_id),
    FOREIGN KEY (estudio_id) REFERENCES estudio(id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
  )ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

