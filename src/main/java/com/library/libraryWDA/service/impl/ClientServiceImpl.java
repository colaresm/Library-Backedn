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
import com.library.libraryWDA.validation.ProfessionalValidator;
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

    private final ClientRepository professionalRepository;

    private final ClientMapper professionalMapper;

    private final ProfessionalValidator professionalValidator;

    private final UserService userService;

    @Autowired
    public ClientServiceImpl(ClientRepository professionalRepository, ClientMapper professionalMapper,
                                   ProfessionalValidator professionalValidator, UserService userService) {
        this.professionalRepository = professionalRepository;
        this.professionalMapper = professionalMapper;
        this.professionalValidator = professionalValidator;
        this.userService = userService;
    }

    @Override
    public void create(ClientCreateRequest professionalCreateRequest) {
        professionalValidator.validateForCreation(professionalCreateRequest);

        User userCreated = userService.create(professionalCreateRequest.getUserCreateRequest(), professionalCreateRequest.getPosition());

        Client professionalToCreate = professionalMapper.toModel(professionalCreateRequest);
        professionalToCreate.setUser(userCreated);
        professionalRepository.save(professionalToCreate);
    }
    @Override
    public Page<ClientListItemResponse> findAll(String status, Pageable pageable) {
        if(status != null) {
            return professionalRepository.findAllByIsActive(status.equals("active"), pageable).map(professionalMapper::toProfessionalListItemResponse);
        } else {
            return professionalRepository.findAll(pageable).map(professionalMapper::toProfessionalListItemResponse);
        }
    }

    @Override
    public void updateToDeactivated(UUID id) {
        Client professionalToUpdate = getById(id);
        professionalToUpdate.setIsActive(false);
        professionalRepository.save(professionalToUpdate);
    }

    @Override
    public void updateToActivated(UUID id) {
        Client professionalToUpdate = getById(id);
        professionalToUpdate.setIsActive(true);

        if(!professionalToUpdate.getIsActive())
            professionalToUpdate.setAdmission(professionalToUpdate.getUpdatedAt().toLocalDate());

        professionalRepository.save(professionalToUpdate);
    }

    @Override
    public void update(ClientUpdateRequest professionalUpdateRequest) {
        Client foundProfessional = getById(professionalUpdateRequest.getId());

        professionalValidator.validateForUpdate(foundProfessional, professionalUpdateRequest);

        User userUpdated = userService.update(professionalUpdateRequest.getUserUpdateRequest());

        Client professionalToUpdate = professionalMapper.toModel(professionalUpdateRequest);
        professionalToUpdate.setUser(userUpdated);
        professionalToUpdate.setCreatedAt(foundProfessional.getCreatedAt());
        professionalToUpdate.setIsActive(foundProfessional.getIsActive());
        professionalToUpdate.getAddress().setId(foundProfessional.getAddress().getId());
        professionalToUpdate.getAddress().setCreatedAt(foundProfessional.getCreatedAt());

        professionalRepository.save(professionalToUpdate);
    }

    @Override
    public Client getById(UUID id) {
        return professionalRepository.findById(id).orElseThrow(() -> new ProfessionalNotFoundException(id));
    }
    @Override
    public ClientDetailsResponse getProfessionalById(UUID id) {
        Client foundProfessional= getById(id);
        return professionalMapper.toProfessionalDetailsResponse(foundProfessional);
    }

    @Override
    public Client getProfessionalByUser(User user){
        return professionalRepository.findByUser(user);
    }

    @Override
    public List<ClientListItemResponse> findAllActiveAndDoctors() {
        return professionalRepository.findAllByIsActiveTrueAndPosition(Position.DOCTOR).stream().map(professionalMapper::toProfessionalListItemResponse).collect(Collectors.toList());
    }

    @Override
    public Page<ClientListItemResponse> findAllDoctors( Pageable pageable) {
        return professionalRepository.findAllByPosition(Position.DOCTOR, pageable).map(professionalMapper::toProfessionalListItemResponse);
    }
}
