ALTER TABLE agendamento ADD tatuador_id BIGINT;

ALTER TABLE agendamento ADD CONSTRAINT fk_agendamento_tatuador FOREIGN KEY (tatuador_id) REFERENCES Tatuador(id);
