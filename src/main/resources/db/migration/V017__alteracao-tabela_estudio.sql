alter table estudio add aberto tinyint(1) not null;
update estudio set aberto = true;