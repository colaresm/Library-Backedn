package com.library.libraryWDA.service;

import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateListItemResponse;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateCreateRequest;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateUpdateRequest;
import com.library.libraryWDA.model.UnavailabilityDate;

import java.util.List;
import java.util.UUID;

public interface UnavailabilityDateService {

    void create(UnavailabilityDateCreateRequest unavailabilityDateCreateRequest);

    List<UnavailabilityDateListItemResponse> findAll();

    void update(UnavailabilityDateUpdateRequest unavailabilityDateUpdateRequest);

    void deleteById(UUID id);

    UnavailabilityDate getById(UUID id);
}
