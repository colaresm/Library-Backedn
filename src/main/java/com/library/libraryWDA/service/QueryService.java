package com.library.libraryWDA.service;

import com.library.libraryWDA.dto.query.QueryCreateRequest;
import com.library.libraryWDA.dto.query.QueryListItemResponse;
import com.library.libraryWDA.dto.query.QueryUpdateRequest;
import com.library.libraryWDA.model.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public interface QueryService {
    void create(QueryCreateRequest queryCreateRequest, HttpServletRequest request, HttpServletResponse response) throws IOException;

    Page<QueryListItemResponse> findAllByPatientId(UUID patientId, Pageable pageable);

    Query getById(UUID id);

    void update(QueryUpdateRequest queryUpdateRequest);
}
