create table TB_USUARIO(
   ID bigserial primary key,
   NOME varchar(255) not null,
   EMAIL varchar(155) not null unique,
   CPF varchar(14) not null unique,
   RENDA decimal(12, 2),
   DATA_NASCIMENTO date not null,
   QUANTIDADE_FILHOS numeric default 0,
   ID_TELEFONE bigserial not null,
   ID_GENERO bigserial not null,
   CREATED_AT TIMESTAMP not null,
   UPDATED_AT TIMESTAMP
);

alter table TB_USUARIO add foreign key (ID_TELEFONE) references TB_TELEFONE(ID);
alter table TB_USUARIO add foreign key (ID_GENERO) references TB_DOMINIO(ID);
alter table TB_USUARIO alter column CREATED_AT set default current_timestamp at time zone 'UTC';
alter table TB_USUARIO alter column UPDATED_AT set default current_timestamp at time zone 'UTC';