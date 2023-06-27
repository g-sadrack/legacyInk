CREATE TABLE estudio_estoque (
    estudio_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    PRIMARY KEY (estudio_id, item_id),
    FOREIGN KEY (estudio_id) REFERENCES estudio (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;