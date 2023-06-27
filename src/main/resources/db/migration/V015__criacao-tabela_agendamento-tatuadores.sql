CREATE TABLE
  `agendamento_tatuadores` (
    `agendamento_id` bigint NOT NULL,
    `tatuador_id` bigint NOT NULL,
    PRIMARY KEY (`agendamento_id`, `tatuador_id`),
    KEY `tatuador_id` (`tatuador_id`),
   	CONSTRAINT fk_agendamento_agendamento FOREIGN KEY (agendamento_id) REFERENCES agendamento (id),
  	CONSTRAINT fk_agendamento_tatuador FOREIGN KEY (tatuador_id) REFERENCES tatuador (id)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
