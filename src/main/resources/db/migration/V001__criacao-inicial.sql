CREATE TABLE estado (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(nome)
) ENGINE=InnoDB;

create table cidade (
    id bigint not null auto_increment,
    nome varchar(60),
    estado_id bigint not null,
    primary key (id)
) engine = InnoDB;

CREATE TABLE tatuador (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    tempo_experiencia INTEGER NOT NULL,
    avaliacao DECIMAL(4,2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE tatuador_especialidades (
    tatuador_id BIGINT NOT NULL,
    especialidade VARCHAR(30),
    PRIMARY KEY (tatuador_id, especialidade)
) ENGINE=InnoDB;

CREATE TABLE tatuagem (
    id bigint NOT NULL AUTO_INCREMENT,
    descricao varchar(255) NOT NULL,
    preco decimal(10, 2) NOT NULL,
    tamanho integer NOT NULL,
    cor varchar(50),
    localizacao_no_corpo varchar(120),
    imagem_url varchar(255),
    primary key (id)
) ENGINE = InnoDB;

CREATE TABLE cliente (
    id BIGINT NOT NULL AUTO_INCREMENT,
    idade INTEGER,
    nome VARCHAR(120),
    telefone VARCHAR(20),
    data_nascimento DATE,
    sexo ENUM('MASCULINO', 'FEMININO'),
    endereco_cidade_id BIGINT NOT NULL,
    email VARCHAR(120) NOT NULL,
    endereco_cep VARCHAR(8) NOT NULL,
    endereco_complemento VARCHAR(150),
    endereco_logradouro VARCHAR(150),
    endereco_numero VARCHAR(10),
    endereco_rua VARCHAR(120),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE agendamento (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data_cadastro DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,
    data_hora DATETIME(6),
    status varchar(30) NOT NULL,
    cliente_id BIGINT,
    tatuagem_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id),
    CONSTRAINT fk_tatuagem FOREIGN KEY (tatuagem_id) REFERENCES tatuagem (id)
) ENGINE=InnoDB;
