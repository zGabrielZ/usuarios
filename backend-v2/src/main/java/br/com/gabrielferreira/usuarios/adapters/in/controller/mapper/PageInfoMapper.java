package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PageInfoMapper {

    @Mapping(target = "pageNumber", source = "pageable.pageNumber")
    @Mapping(target = "pageSize", source = "pageable.pageSize")
    @Mapping(target = "sortBy", expression = "java(sortByPageInfo(pageable))")
    PageInfo toPageInfo(Pageable pageable);

    @Named("sortByPageInfo")
    default List<String[]> sortByPageInfo(Pageable pageable){
        List<String[]> sorts = new ArrayList<>();
        pageable.getSort().forEach(sort -> {
            String[] sortBy = new String[2];
            sortBy[0] = sort.getDirection().name();
            sortBy[1] = sort.getProperty();
            sorts.add(sortBy);
        });
        return sorts;
    }
}
