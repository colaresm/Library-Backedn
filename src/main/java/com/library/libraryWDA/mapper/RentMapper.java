package com.library.libraryWDA.mapper;

import org.mapstruct.Mapper;
import com.library.libraryWDA.dto.rent.RentCreateRequest;
import com.library.libraryWDA.model.Rent;


@Mapper(componentModel = "spring")
public interface RentMapper {
    
    Rent toModel(RentCreateRequest rentCreateRequest);

}
