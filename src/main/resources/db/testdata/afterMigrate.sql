set foreign_key_checks = 0;

delete from estado;
delete from cidade;
delete from tatuador;
delete from tatuador_especialidades;
delete from tatuagem;
delete from cliente;
delete from agendamento;

set foreign_key_checks = 1;

alter table estado auto_increment = 1;
alter table cidade auto_increment = 1;
alter table tatuador auto_increment = 1;
alter table tatuador_especialidades auto_increment = 1;
alter table tatuagem auto_increment = 1;
alter table cliente auto_increment = 1;
alter table agendamento auto_increment = 1;

-- estado
INSERT INTO estado (nome) VALUES ('São Paulo');
INSERT INTO estado (nome) VALUES ('Rio de Janeiro');
INSERT INTO estado (nome) VALUES('Minas Gerais');

-- cidade
INSERT INTO cidade (nome, estado_id) VALUES ('São Paulo', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Rio de Janeiro', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('Belo Horizonte', 3);

-- tatuador
INSERT INTO	tatuador (nome, tempo_experiencia, avaliacao) VALUES ('Jonas', 2, 4.5);
INSERT INTO	tatuador (nome, tempo_experiencia, avaliacao) VALUES ('Gabriel', 4, 4.9);
INSERT INTO	tatuador (nome, tempo_experiencia, avaliacao) VALUES ('Maria', 6, 4.1);

-- tatuador especialidades
INSERT INTO tatuador_especialidades (tatuador_id, especialidade) VALUES (1, 'TRIBAL');
INSERT INTO tatuador_especialidades (tatuador_id, especialidade) VALUES (1, 'ORIENTAL');
INSERT INTO tatuador_especialidades (tatuador_id, especialidade) VALUES (2, 'AQUARELA');
INSERT INTO tatuador_especialidades (tatuador_id, especialidade) VALUES (2, 'REALISMO');
INSERT INTO tatuador_especialidades (tatuador_id, especialidade) VALUES (3, 'NEO_TRADICIONAL');
INSERT INTO tatuador_especialidades (tatuador_id, especialidade) VALUES (3, 'BLACKWORK');

-- tatuagem
INSERT INTO tatuagem (descricao, preco, tamanho, cor, localizacao_no_corpo, imagem_url) VALUES ("Flor de Lótus", 150.00, 10, "COLORIDO", "Braço esquerdo", "https://urlimagem.com/flor-de-lotus");
INSERT INTO tatuagem (descricao, preco, tamanho, cor, localizacao_no_corpo, imagem_url) VALUES ("Leão", 300.00, 20, "PRETO_E_BRANCO", "Costas", "https://urlimagem.com/leao");
INSERT INTO tatuagem (descricao, preco, tamanho, cor, localizacao_no_corpo, imagem_url) VALUES ("Frase em inglês", 200.00, 15, "COLORIDO", "Antebraço direito", "https://urlimagem.com/frase-em-ingles");

-- cliente
INSERT INTO cliente (idade, nome, telefone, data_nascimento, sexo, endereco_cidade_id, email, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_rua) VALUES (30, 'Fulano Silva', '(11) 9999-9999', '1991-01-01', 'MASCULINO', 1, 'fulano.silva@example.com', '01234567', 'Apto. 123', 'Rua das Flores', '123', 'Centro');
insert into `cliente` (`data_nascimento`, `email`, `endereco_cep`, `endereco_cidade_id`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `endereco_rua`, `id`, `idade`, `nome`, `sexo`, `telefone`) values ('1998-11-20', 'joao@gmail.com', '12345678', '1', 'Lt 01', 'Rua Brasil', '25', 'Sul', '2', 25, 'Jorge Amado', 'MASCULINO', '(61) 9999-9987');
insert into `cliente` (`data_nascimento`, `email`, `endereco_cep`, `endereco_cidade_id`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `endereco_rua`, `id`, `idade`, `nome`, `sexo`, `telefone`) values ('1980-01-01', 'luh@yahoo.com', '30130011', '3', 'apto 404', 'Avenida do Contorno', '789', 'Savassi', '3', 42, 'Luh Souza', 'MASCULINO', '3199999999');

-- agendamento
INSERT INTO	agendamento (data_cadastro, data_atualizacao, data_hora, status, cliente_id, tatuagem_id, tatuador_id) VALUES (NOW(), NOW(), '2023-04-15 14:00:00', 'AGENDADO', 1, 2,3);
INSERT INTO	agendamento (data_cadastro, data_atualizacao, data_hora, status, cliente_id, tatuagem_id, tatuador_id) VALUES (NOW(), NOW(), '2023-04-16 15:00:00', 'AGENDADO', 2, 3, 2);
INSERT INTO	agendamento (data_cadastro, data_atualizacao, data_hora, status, cliente_id, tatuagem_id, tatuador_id) VALUES (NOW(), NOW(), '2023-04-20 10:00:00', 'CONCLUIDO', 3, 1, 1);