create table TB_DOMINIO(
   ID bigserial primary key,
   DESCRICAO varchar(255) not null unique,
   CODIGO varchar(255) not null unique,
   ID_TIPO_DOMINIO bigserial not null
);

alter table TB_DOMINIO add foreign key (ID_TIPO_DOMINIO) references TB_TIPO_DOMINIO(ID);