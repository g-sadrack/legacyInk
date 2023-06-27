CREATE TABLE tatuador (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   tempo_experiencia INT NULL,
   avaliacao DECIMAL NULL,
   CONSTRAINT pk_tatuador PRIMARY KEY (id)
);

CREATE TABLE tatuador_especialidades (
  tatuador_id BIGINT NOT NULL,
   especialidade VARCHAR(255) NULL
);