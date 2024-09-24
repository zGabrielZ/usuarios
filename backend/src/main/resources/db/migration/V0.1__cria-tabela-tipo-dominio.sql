create table TB_TIPO_DOMINIO(
   ID bigserial primary key,
   DESCRICAO varchar(255) not null unique,
   CODIGO varchar(255) not null unique
);