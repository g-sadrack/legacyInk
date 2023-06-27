CREATE TABLE item (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(120) NULL,
   descricao VARCHAR(255) NULL,
   quantidade INT NULL,
   data_cadastro datetime NULL,
   data_atualizacao datetime NULL,
   CONSTRAINT pk_item PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;