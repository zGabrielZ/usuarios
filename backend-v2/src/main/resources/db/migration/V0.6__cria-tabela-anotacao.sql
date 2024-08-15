create table TB_ANOTACAO(
   ID bigserial primary key,
   TITULO varchar(255) not null,
   DESCRICAO TEXT,
   ID_USUARIO bigserial not null,
   ID_TIPO_ANOTACAO bigserial not null,
   DATA_LEMBRETE TIMESTAMP,
   DATA_ESTUDO_INICIO TIMESTAMP,
   DATA_ESTUDO_FIM TIMESTAMP,
   ID_SITUACAO_TIPO_ANOTACAO bigserial not null,
   CREATED_AT TIMESTAMP not null,
   UPDATED_AT TIMESTAMP
);

alter table TB_ANOTACAO add foreign key (ID_USUARIO) references TB_USUARIO(ID);
alter table TB_ANOTACAO add foreign key (ID_TIPO_ANOTACAO) references TB_DOMINIO(ID);
alter table TB_ANOTACAO add foreign key (ID_SITUACAO_TIPO_ANOTACAO) references TB_DOMINIO(ID);
alter table TB_ANOTACAO alter column DATA_LEMBRETE set default current_timestamp at time zone 'UTC';
alter table TB_ANOTACAO alter column DATA_ESTUDO_INICIO set default current_timestamp at time zone 'UTC';
alter table TB_ANOTACAO alter column DATA_ESTUDO_FIM set default current_timestamp at time zone 'UTC';
alter table TB_ANOTACAO alter column CREATED_AT set default current_timestamp at time zone 'UTC';
alter table TB_ANOTACAO alter column UPDATED_AT set default current_timestamp at time zone 'UTC';