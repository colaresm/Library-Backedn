package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.dto.client.ClientCreateRequest;
import com.library.libraryWDA.dto.client.ClientDetailsResponse;
import com.library.libraryWDA.dto.client.ClientListItemResponse;
import com.library.libraryWDA.dto.client.ClientUpdateRequest;
import com.library.libraryWDA.exception.professional.ProfessionalNotFoundException;
import com.library.libraryWDA.mapper.ClientMapper;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.User;
import com.library.libraryWDA.model.enums.Position;
import com.library.libraryWDA.repository.ClientRepository;
import com.library.libraryWDA.service.ClientService;
import com.library.libraryWDA.service.UserService;
import com.library.libraryWDA.validation.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final ClientValidator clientValidator;

    private final UserService userService;

    @Autowired
    public ClientServiceImpl(ClientRepository  clientRepository, ClientMapper clientMapper,
                                   ClientValidator clientValidator, UserService userService) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.clientValidator = clientValidator;
        this.userService = userService;
    }

    @Override
    public void create(ClientCreateRequest professionalCreateRequest) {
        clientValidator.validateForCreation(professionalCreateRequest);

        User userCreated = userService.create(professionalCreateRequest.getUserCreateRequest(), professionalCreateRequest.getPosition());

        Client professionalToCreate = clientMapper.toModel(professionalCreateRequest);
        professionalToCreate.setUser(userCreated);
        clientRepository.save(professionalToCreate);
    }
    @Override
    public Page<ClientListItemResponse> findAll(String status, Pageable pageable) {
        if(status != null) {
            return clientRepository.findAllByIsActive(status.equals("active"), pageable).map(clientMapper::toProfessionalListItemResponse);
        } else {
            return clientRepository.findAll(pageable).map(clientMapper::toProfessionalListItemResponse);
        }
    }

    @Override
    public void updateToDeactivated(UUID id) {
        Client professionalToUpdate = getById(id);
        professionalToUpdate.setIsActive(false);
        clientRepository.save(professionalToUpdate);
    }

    @Override
    public void updateToActivated(UUID id) {
        Client professionalToUpdate = getById(id);
        professionalToUpdate.setIsActive(true);

        clientRepository.save(professionalToUpdate);
    }

    @Override
    public void update(ClientUpdateRequest professionalUpdateRequest) {
        Client foundProfessional = getById(professionalUpdateRequest.getId());

        clientValidator.validateForUpdate(foundProfessional, professionalUpdateRequest);

        User userUpdated = userService.update(professionalUpdateRequest.getUserUpdateRequest());

        Client professionalToUpdate = clientMapper.toModel(professionalUpdateRequest);
        professionalToUpdate.setUser(userUpdated);
        professionalToUpdate.setCreatedAt(foundProfessional.getCreatedAt());
        professionalToUpdate.setIsActive(foundProfessional.getIsActive());


        clientRepository.save(professionalToUpdate);
    }

    @Override
    public Client getById(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> new ProfessionalNotFoundException(id));
    }
    @Override
    public ClientDetailsResponse getClientById(UUID id) {
        Client foundProfessional= getById(id);
        return clientMapper.toProfessionalDetailsResponse(foundProfessional);
    }

    @Override
    public Client geClientByUser(User user){
        return clientRepository.findByUser(user);
    }

    @Override
    public List<ClientListItemResponse> findAllActiveAndDoctors() {
        return clientRepository.findAllByIsActiveTrueAndPosition(Position.PEOPLE).stream().map(clientMapper::toProfessionalListItemResponse).collect(Collectors.toList());
    }

    @Override
    public Page<ClientListItemResponse> findAllDoctors( Pageable pageable) {
        return clientRepository.findAllByPosition(Position.PEOPLE, pageable).map(clientMapper::toProfessionalListItemResponse);
    }
}
