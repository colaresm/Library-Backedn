package com.library.libraryWDA.mapper;

import org.mapstruct.Mapper;

import com.library.libraryWDA.model.ProfessionalAvailability;
@Mapper(componentModel = "spring")
public interface ProfessionalAvailabilityMapper {
ProfessionalAvailability toAvailabilityProfessionalListItemResponse(ProfessionalAvailability availabilityProfessional);   
}
