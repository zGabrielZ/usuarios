create table TB_PERFIL(
    ID bigserial primary key,
    TITULO varchar(255) not null unique,
    AUTORIEDADE varchar(255) not null unique
);

create table TB_USUARIO_PERFIL(
    ID_USUARIO bigserial not null,
    ID_PERFIL bigserial not null
);

alter table TB_USUARIO_PERFIL add foreign key (ID_USUARIO) references TB_USUARIO(ID);
alter table TB_USUARIO_PERFIL add foreign key (ID_PERFIL) references TB_PERFIL(ID);