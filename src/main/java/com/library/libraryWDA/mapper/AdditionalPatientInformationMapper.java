package com.library.libraryWDA.mapper;

import org.mapstruct.Mapper;

import com.library.libraryWDA.dto.additionalPatientInformation.AdditionalPatientInformationCreateRequest;
import com.library.libraryWDA.model.AdditionalPatientInformation;

@Mapper(componentModel = "spring")
public interface AdditionalPatientInformationMapper {
    AdditionalPatientInformation toModel(AdditionalPatientInformationCreateRequest additionalPatientInformationCreateRequest);
}
