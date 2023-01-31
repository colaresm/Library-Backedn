package com.library.libraryWDA.mapper;


import com.library.libraryWDA.dto.anamnese.AnamneseRequest;
import com.library.libraryWDA.model.Anamnese;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnamneseMapper {

    Anamnese  toModel(AnamneseRequest anamneseRequest);
}