package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.AnotacaoEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnotacaoEntityMapper {

    AnotacaoEntity createAnotacaoEntity(AnotacaoDomain anotacaoDomain);

    @Mapping(target = "usuario.telefone", ignore = true)
    @Mapping(target = "usuario.genero", ignore = true)
    @Mapping(target = "usuario.anotacoes", ignore = true)
    AnotacaoDomain toAnotacaoDomain(AnotacaoEntity anotacaoEntity);

    @Mapping(target = "usuario", ignore = true)
    AnotacaoDomain toAnotacaoResumidoDomain(AnotacaoEntity anotacaoEntity);

    default List<AnotacaoDomain> toAnotacoesDomains(Page<AnotacaoEntity> anotacaoEntities){
        return anotacaoEntities.stream().map(this::toAnotacaoResumidoDomain).toList();
    }

    default AnotacaoDomain createAnotacaoDomainRascunho(AnotacaoDomain anotacaoDomainCreate, DominioDomain tipoAnotacaoDomain, DominioDomain situacaoAnotacaoDomain, UsuarioDomain usuarioDomain){
        anotacaoDomainCreate.setTipoAnotacao(tipoAnotacaoDomain);
        anotacaoDomainCreate.setSituacaoTipoAnotacao(situacaoAnotacaoDomain);
        anotacaoDomainCreate.setUsuario(usuarioDomain);
        return anotacaoDomainCreate;
    }

    default AnotacaoDomain createAnotacaoDomainEstudo(AnotacaoDomain anotacaoDomainCreate, DominioDomain tipoAnotacaoDomain, DominioDomain situacaoAnotacaoDomain, UsuarioDomain usuarioDomain){
        AnotacaoDomain anotacaoDomain = createAnotacaoDomainRascunho(anotacaoDomainCreate, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);
        anotacaoDomain.setDataEstudoInicio(anotacaoDomainCreate.getDataEstudoInicio());
        anotacaoDomain.setDataEstudoFim(anotacaoDomainCreate.getDataEstudoFim());
        return anotacaoDomain;
    }

    default AnotacaoDomain createAnotacaoDomainLembrete(AnotacaoDomain anotacaoDomainCreate, DominioDomain tipoAnotacaoDomain, DominioDomain situacaoAnotacaoDomain, UsuarioDomain usuarioDomain){
        AnotacaoDomain anotacaoDomain = createAnotacaoDomainRascunho(anotacaoDomainCreate, tipoAnotacaoDomain, situacaoAnotacaoDomain, usuarioDomain);
        anotacaoDomain.setDataLembrete(anotacaoDomainCreate.getDataLembrete());
        return anotacaoDomain;
    }

    default AnotacaoDomain updateFinalizarReabrirAnotacaoDomain(AnotacaoDomain anotacaoDomainFound, DominioDomain situacaoAnotacaoDomain){
        anotacaoDomainFound.setSituacaoTipoAnotacao(situacaoAnotacaoDomain);
        return anotacaoDomainFound;
    }

    default AnotacaoDomain updateAnotacao(AnotacaoDomain anotacaoDomainFound, AnotacaoDomain anotacaoDomainUpdate){
        anotacaoDomainFound.setTitulo(anotacaoDomainUpdate.getTitulo());
        anotacaoDomainFound.setDescricao(anotacaoDomainUpdate.getDescricao());
        anotacaoDomainFound.setDataEstudoInicio(anotacaoDomainUpdate.getDataEstudoInicio());
        anotacaoDomainFound.setDataEstudoFim(anotacaoDomainUpdate.getDataEstudoFim());
        anotacaoDomainFound.setDataLembrete(anotacaoDomainUpdate.getDataLembrete());
        return anotacaoDomainFound;
    }
}
