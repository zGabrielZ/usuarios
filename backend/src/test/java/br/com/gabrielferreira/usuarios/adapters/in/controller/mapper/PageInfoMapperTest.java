package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PageInfoMapperTest {

    private final PageInfoMapper pageInfoMapper = Mappers.getMapper(PageInfoMapper.class);

    @Test
    @DisplayName("Deve criar page info")
    @Order(1)
    void deveCriarPageInfo(){
        Sort sort = Sort.by(Sort.Order.asc("nome"));
        PageRequest pageRequest = PageRequest.of(1, 1, sort);

        PageInfo pageInfo = pageInfoMapper.toPageInfo(pageRequest);
        assertEquals(pageRequest.getPageNumber(), pageInfo.getPageNumber());
        assertEquals(pageRequest.getPageSize(), pageInfo.getPageSize());
        assertFalse(pageInfo.getSortBy().isEmpty());
    }
}
