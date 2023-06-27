set foreign_key_checks = 0;

delete from estado;
delete from cidade;
delete from tatuador;
delete from tatuador_especialidades;
delete from tatuagem;
delete from cliente;
delete from agendamento;
delete from agendamento_tatuadores;
delete from estudio;
delete from estudio_clientes;
delete from estudio_tatuadores;
delete from item;
delete from estudio_estoque;

set foreign_key_checks = 1;

alter table estado auto_increment = 1;
alter table cidade auto_increment = 1;
alter table tatuador auto_increment = 1;
alter table tatuador_especialidades auto_increment = 1;
alter table tatuagem auto_increment = 1;
alter table cliente auto_increment = 1;
alter table agendamento auto_increment = 1;
alter table agendamento_tatuadores auto_increment = 1;
alter table estudio auto_increment = 1;
alter table estudio_clientes auto_increment = 1;
alter table estudio_tatuadores auto_increment = 1;
alter table item auto_increment = 1;

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
insert into `cliente` (`data_nascimento`, `email`, `endereco_cep`, `endereco_cidade_id`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `endereco_rua`,`idade`, `nome`, `sexo`, `telefone`) values ('1998-11-20', 'joao@gmail.com', '12345678', '2', 'Lt 01', 'Rua Brasil', '25', 'Sul', 25, 'Jorge Amado', 'MASCULINO', '(61) 9999-9987');
insert into `cliente` (`data_nascimento`, `email`, `endereco_cep`, `endereco_cidade_id`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `endereco_rua`, `idade`, `nome`, `sexo`, `telefone`) values ('1980-01-01', 'luh@yahoo.com', '30130011', '3', 'apto 404', 'Avenida do Contorno', '789', 'Savassi', 42, 'Luh Souza', 'MASCULINO', '3199999999');
insert into `cliente` (`data_nascimento`, `email`, `endereco_cep`, `endereco_cidade_id`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `endereco_rua`,`idade`, `nome`, `sexo`, `telefone`) values ('1977-01-25', 'duda@gmail.com', '12745678', '1', 'Lt 24', 'Rua Marechal Deodoro', '20', 'Norte', 20, 'Eduardo Silva', 'MASCULINO', '(11) 9999-7787');

-- agendamento
INSERT INTO	agendamento (data_cadastro, data_atualizacao, status, cliente_id, tatuagem_id) VALUES (NOW(), NOW(), 'AGENDADO', 1, 2);
INSERT INTO	agendamento (data_cadastro, data_atualizacao, status, cliente_id, tatuagem_id) VALUES (NOW(), NOW(), 'AGENDADO', 2, 3);
INSERT INTO	agendamento (data_cadastro, data_atualizacao, status, cliente_id, tatuagem_id) VALUES (NOW(), NOW(), 'CONCLUIDO', 3, 1);

-- agendamento_tatuadores
INSERT INTO agendamento_tatuadores (agendamento_id, tatuador_id) VALUES (1, 1);
INSERT INTO agendamento_tatuadores (agendamento_id, tatuador_id) VALUES (2, 1);
INSERT INTO agendamento_tatuadores (agendamento_id, tatuador_id) VALUES (2, 2);
INSERT INTO agendamento_tatuadores (agendamento_id, tatuador_id) VALUES (3, 1);

-- estudio
INSERT INTO estudio (nome, telefone, email, cnpj, razao_social, redes_sociais, endereco_cidade_id, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_rua)
VALUES ('Estúdio X', '(11) 1234-5678', 'estudiox@email.com', '12345678901234', 'Razão Social X', 'instagram.com/estudiox', 1, '01234567', 'Sala 101', 'Rua Principal', '100', 'Bairro Y'),
('Estúdio Y', '(22) 2345-6789', 'estudioy@email.com', '23456789012345', 'Razão Social Y', NULL, 2, '12345678', NULL, 'Avenida Secundária', '500', 'Bairro Z');

-- estudio_clientes
insert into `estudio_clientes` (`cliente_id`, `estudio_id`) values ('1', '1');
insert into `estudio_clientes` (`cliente_id`, `estudio_id`) values ('3', '1');
insert into `estudio_clientes` (`cliente_id`, `estudio_id`) values ('2', '1');

-- estudio_tatuadores
insert into `estudio_tatuadores` (`estudio_id`, `tatuador_id`) values ('1', '1');
insert into `estudio_tatuadores` (`estudio_id`, `tatuador_id`) values ('1', '2');
insert into `estudio_tatuadores` (`estudio_id`, `tatuador_id`) values ('1', '3');

-- item
insert into`item` (`data_atualizacao`, `data_cadastro`, `descricao`, `nome`, `quantidade`) values ('2023-04-25 00:00:00','2023-04-25 00:00:00','tinta parapreta para tatuagem','tinta','5');
insert into`item` (`data_atualizacao`, `data_cadastro`, `descricao`, `nome`, `quantidade`) values ('2023-04-25 00:00:00','2023-04-25 00:00:00','agulha preta para tatuagem','agulha','10');

-- estudio_estoque
insert into `estudio_estoque` (`estudio_id`, `item_id`) values ('2', '1');
insert into `estudio_estoque` (`estudio_id`, `item_id`) values ('2', '2');
insert into `estudio_estoque` (`estudio_id`, `item_id`) values ('1', '2');
