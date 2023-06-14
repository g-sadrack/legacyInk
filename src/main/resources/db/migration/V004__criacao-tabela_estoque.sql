CREATE TABLE item (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120),
    descricao VARCHAR(150),
    quantidade INT,
  	data_cadastro datetime,
  	data_atualizacao datetime,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE estudio_estoque (
    estudio_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    PRIMARY KEY (estudio_id, item_id),
    FOREIGN KEY (estudio_id) REFERENCES estudio (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
);