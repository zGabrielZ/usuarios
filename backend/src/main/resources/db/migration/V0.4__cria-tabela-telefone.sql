create table TB_TELEFONE(
   ID bigserial primary key,
   NUMERO varchar(12) not null,
   DDD varchar(10) not null,
   DESCRICAO TEXT,
   ID_TIPO_TELEFONE bigserial not null,
   CREATED_AT TIMESTAMP not null,
   UPDATED_AT TIMESTAMP
);

alter table TB_TELEFONE add foreign key (ID_TIPO_TELEFONE) references TB_DOMINIO(ID);
alter table TB_TELEFONE alter column CREATED_AT set default current_timestamp at time zone 'UTC';
alter table TB_TELEFONE alter column UPDATED_AT set default current_timestamp at time zone 'UTC';