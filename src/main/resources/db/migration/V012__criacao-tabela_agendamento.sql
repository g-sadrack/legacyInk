CREATE TABLE agendamento (
  id BIGINT AUTO_INCREMENT NOT NULL,
   cliente_id BIGINT NOT NULL,
   tatuagem_id BIGINT NOT NULL,
   status VARCHAR(30) NULL,
   sessao datetime NULL,
   data_cadastro datetime NOT NULL,
   data_atualizacao datetime NOT NULL,
   CONSTRAINT pk_agendamento PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;