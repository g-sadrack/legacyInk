CREATE TABLE cliente (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(120) NOT NULL,
   idade INT NOT NULL,
   sexo VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   telefone VARCHAR(255) NULL,
   data_nascimento date NULL,
   endereco_cep VARCHAR(150) NULL,
   endereco_rua VARCHAR(60) NULL,
   endereco_numero VARCHAR(60) NULL,
   endereco_complemento VARCHAR(150) NULL,
   endereco_logradouro VARCHAR(150) NULL,
   endereco_cidade_id BIGINT NOT NULL,
   CONSTRAINT pk_cliente PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;