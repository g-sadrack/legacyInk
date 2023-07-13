alter table agendamento add codigo varchar(36) not null after id;
update agendamento set codigo = uuid();
alter table agendamento add constraint UK_AGENDAMENTO_CODIGO unique (codigo);