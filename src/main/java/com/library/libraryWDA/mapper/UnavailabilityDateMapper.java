package com.library.libraryWDA.mapper;

import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateListItemResponse;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateCreateRequest;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateUpdateRequest;
import com.library.libraryWDA.model.UnavailabilityDate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnavailabilityDateMapper {

    UnavailabilityDate toModel(UnavailabilityDateCreateRequest unavailabilityDateCreateRequest);

    UnavailabilityDateListItemResponse toUnavaibilityDateListItemResponse(UnavailabilityDate unavailabilityDate);

    UnavailabilityDate toModel(UnavailabilityDateUpdateRequest unavailabilityDateUpdateRequest);

}
