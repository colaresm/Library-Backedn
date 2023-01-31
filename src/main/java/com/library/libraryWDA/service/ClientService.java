package com.library.libraryWDA.service;

import com.library.libraryWDA.dto.client.ClientCreateRequest;
import com.library.libraryWDA.dto.client.ClientDetailsResponse;
import com.library.libraryWDA.dto.client.ClientListItemResponse;
import com.library.libraryWDA.dto.client.ClientUpdateRequest;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    
    void create(ClientCreateRequest professionalCreateRequest);

    Page<ClientListItemResponse> findAll(String status, Pageable pageable);

    void updateToDeactivated(UUID id);

    void updateToActivated(UUID id);

    void update(ClientUpdateRequest professionalUpdateRequest);

    Client getById(UUID id);

    ClientDetailsResponse getProfessionalById(UUID id);
    Client getProfessionalByUser(User user);

    List<ClientListItemResponse> findAllActiveAndDoctors();


    Page<ClientListItemResponse> findAllDoctors(Pageable pageable);
}
