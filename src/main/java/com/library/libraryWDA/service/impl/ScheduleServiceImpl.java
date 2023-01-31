package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.dto.schedule.ScheduleCalendarListItemResponse;
import com.library.libraryWDA.dto.schedule.ScheduleCreateRequest;
import com.library.libraryWDA.dto.schedule.ScheduleListItemResponse;
import com.library.libraryWDA.dto.schedule.ScheduleUpdateRequest;
import com.library.libraryWDA.exception.schedule.ScheduleNotFoundException;
import com.library.libraryWDA.mapper.ScheduleMapper;
import com.library.libraryWDA.model.Patient;
import com.library.libraryWDA.model.Client;
import com.library.libraryWDA.model.Schedule;
import com.library.libraryWDA.model.enums.SchedulesStatus;
import com.library.libraryWDA.repository.ScheduleRepository;
import com.library.libraryWDA.service.PatientService;
import com.library.libraryWDA.service.ClientService;
import com.library.libraryWDA.service.ScheduleService;
import com.library.libraryWDA.validation.ScheduleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
 
  
@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper scheduleMapper;

    private final ScheduleRepository scheduleRepository;

    private final ScheduleValidator scheduleValidator;

    private final PatientService patientService;

    private final ClientService professionalService;

    @Autowired
    public ScheduleServiceImpl(ScheduleMapper scheduleMapper, ScheduleRepository scheduleRepository,
                               ScheduleValidator scheduleValidator, PatientService patientService, ClientService professionalService) {
        this.scheduleMapper = scheduleMapper;
        this.scheduleRepository = scheduleRepository;
        this.scheduleValidator = scheduleValidator;
        this.patientService = patientService;
        this.professionalService = professionalService;
    }

    @Override
    public void create(ScheduleCreateRequest scheduleCreateRequest) {
        Schedule scheduleToCreate = scheduleMapper.toModel(scheduleCreateRequest);

        Patient patientFound = patientService.getById(scheduleCreateRequest.getPatientId());
        Client professionalFound = professionalService.getById(scheduleCreateRequest.getProfessionalId());

        scheduleToCreate.setPatient(patientFound);
        scheduleToCreate.setProfessional(professionalFound);

        scheduleValidator.validateForCreation(scheduleToCreate);
        scheduleRepository.save(scheduleToCreate);
    }

    public void update(ScheduleUpdateRequest scheduleUpdateRequest) {


        Schedule scheduleFound = getById(scheduleUpdateRequest.getId());

        Patient patientFound = patientService.getById(scheduleUpdateRequest.getPatientId());
        Client professionalFound = professionalService.getById(scheduleUpdateRequest.getProfessionalId());

        scheduleFound.setDate(scheduleUpdateRequest.getDate());
        scheduleFound.setDuration(scheduleUpdateRequest.getDuration());
        scheduleFound.setTime(scheduleUpdateRequest.getTime());
        scheduleFound.setPatient(patientFound);
        scheduleFound.setProfessional(professionalFound);
        scheduleFound.setStatus(scheduleUpdateRequest.getStatus());
        scheduleValidator.validateForUpdate(scheduleFound);
        scheduleRepository.save(scheduleFound);
    }
    public Schedule getById(UUID id){
        return scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException(id));
    }

    @Override
    public Page<ScheduleListItemResponse> findAll(Pageable pageable) {
        return scheduleRepository.findAll(pageable).map(scheduleMapper::toScheduleListItemResponse);
    }

    @Override
    public List<ScheduleUpdateRequest> findAllByDateBetweenInitialDateAndFinalDate(LocalDate initialDate, LocalDate finalDate) {
        return scheduleRepository.findAllByDateBetween(initialDate, finalDate)
                .stream()
                .map(scheduleMapper::toScheduleUpdateRequest)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleUpdateRequest> findAllByProfessionalIdAndTimeAndStatus(UUID professionalId, LocalTime time, SchedulesStatus schedulesStatus) {
        return scheduleRepository.findAllByProfessionalIdAndTimeAndStatus(professionalId, time, schedulesStatus)
                .stream()
                .map(scheduleMapper::toScheduleUpdateRequest)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleUpdateRequest> findAllByProfessionalIdAndStatus(UUID professionalId, SchedulesStatus schedulesStatus) {
        return scheduleRepository.findAllByProfessionalIdAndStatus(professionalId, schedulesStatus)
                .stream()
                .map(scheduleMapper::toScheduleUpdateRequest)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleUpdateRequest findConfirmedAndTodayByPatientId(UUID patientId) {
        Schedule scheduleFound = scheduleRepository.findScheduleByStatusAndDateAndPatientId(SchedulesStatus.CONFIRMED,
                                                                                            LocalDate.now(),
                                                                                            patientId)
                .orElseThrow(() -> new ScheduleNotFoundException("Agendamento para o dia de hoje não encontrado"));

        return  scheduleMapper.toScheduleUpdateRequest(scheduleFound);
    }

    @Override
    public List<ScheduleCalendarListItemResponse> findAllByProfessionalId(UUID professionalId) {
        professionalService.getById(professionalId);

        return scheduleRepository.findAllByProfessionalId(professionalId)
                .stream()
                .map(scheduleMapper::toScheduleCalendarList)
                .collect(Collectors.toList());
    }
}
