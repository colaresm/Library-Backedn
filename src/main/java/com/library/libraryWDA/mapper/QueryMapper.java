package com.library.libraryWDA.mapper;

import com.library.libraryWDA.dto.query.QueryListItemResponse;
import com.library.libraryWDA.model.Query;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QueryMapper {
    QueryListItemResponse toQueryListItemResponse(Query query);  
}
