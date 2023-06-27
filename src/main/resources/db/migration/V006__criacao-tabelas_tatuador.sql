CREATE TABLE tatuagem (
   id BIGINT AUTO_INCREMENT NOT NULL,
   descricao VARCHAR(255) NULL,
   tamanho INT NULL,
   cor VARCHAR(50) NULL,
   localizacao_no_corpo VARCHAR(120) NULL,
   imagem_url VARCHAR(255) NULL,
   preco DECIMAL NOT NULL,
   CONSTRAINT pk_tatuagem PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;