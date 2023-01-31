package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.dto.schedule.ScheduleUpdateRequest;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateCreateRequest;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateListItemResponse;
import com.library.libraryWDA.dto.unavaibilityDate.UnavailabilityDateUpdateRequest;
import com.library.libraryWDA.exception.unavailabilityDate.UnavailabilityDateNotFoundException;
import com.library.libraryWDA.exception.unavailabilityDate.UnavailabilityDateNotValidException;
import com.library.libraryWDA.mapper.UnavailabilityDateMapper;
import com.library.libraryWDA.model.UnavailabilityDate;
import com.library.libraryWDA.model.enums.SchedulesStatus;
import com.library.libraryWDA.repository.UnavailabilityDateRepository;
import com.library.libraryWDA.service.ScheduleService;
import com.library.libraryWDA.service.UnavailabilityDateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UnavailabilityDateServiceImpl implements UnavailabilityDateService {

    private final UnavailabilityDateRepository unavailabilityDateRepository;

    private final UnavailabilityDateMapper unavailabilityDateMapper;

    private final ScheduleService scheduleService;

    public UnavailabilityDateServiceImpl(UnavailabilityDateRepository unavailabilityDateRepository,
                                         UnavailabilityDateMapper unavailabilityDateMapper, ScheduleService scheduleService) {
        this.unavailabilityDateRepository = unavailabilityDateRepository;
        this.unavailabilityDateMapper = unavailabilityDateMapper;
        this.scheduleService = scheduleService;
    }

    public void create(UnavailabilityDateCreateRequest unavailabilityDateCreateRequest){

        if(unavailabilityDateCreateRequest.getInitialDate().isAfter(unavailabilityDateCreateRequest.getFinalDate())) {
            throw new UnavailabilityDateNotValidException("Data inicial não pode ser maior que data final.");
        }
        UnavailabilityDate unavailabilityDateToCreate = unavailabilityDateMapper.toModel(unavailabilityDateCreateRequest);

        List<ScheduleUpdateRequest> schedulesFound = scheduleService.findAllByDateBetweenInitialDateAndFinalDate(unavailabilityDateToCreate.getInitialDate(),
                                                                                                    unavailabilityDateToCreate.getFinalDate());

        for (ScheduleUpdateRequest schedule : schedulesFound) {
            if(schedule.getStatus().equals(SchedulesStatus.CONFIRMED)) {
                schedule.setStatus(SchedulesStatus.CANCELED_BY_CLINIC);
                scheduleService.update(schedule);
            }
        }

        unavailabilityDateRepository.save(unavailabilityDateToCreate);
    }

    public List<UnavailabilityDateListItemResponse> findAll(){
        return unavailabilityDateRepository.findAll().stream().map(unavailabilityDateMapper::toUnavaibilityDateListItemResponse).collect(Collectors.toList());
    }

    public void update(UnavailabilityDateUpdateRequest unavailabilityDateUpdateRequest){
        UnavailabilityDate foundUnavailabilityDate = getById(unavailabilityDateUpdateRequest.getId());
        UnavailabilityDate unavailabilityDateToUpdate = unavailabilityDateMapper.toModel(unavailabilityDateUpdateRequest);
        unavailabilityDateToUpdate.setCreatedAt(foundUnavailabilityDate.getCreatedAt());

        updateStatusSchedulesByUpdateUnavailabilityDate(unavailabilityDateToUpdate, foundUnavailabilityDate);

        unavailabilityDateRepository.save(unavailabilityDateToUpdate);
    }

    public void deleteById(UUID id){
        UnavailabilityDate foundUnavailableDate = getById(id);

        List<ScheduleUpdateRequest> foundSchedulesForConfirmed = scheduleService.findAllByDateBetweenInitialDateAndFinalDate(foundUnavailableDate.getInitialDate(),
                foundUnavailableDate.getFinalDate());

        for (ScheduleUpdateRequest schedule : foundSchedulesForConfirmed) {
            if(schedule.getStatus().equals(SchedulesStatus.CANCELED_BY_CLINIC)) {
                schedule.setStatus(SchedulesStatus.CONFIRMED);
                scheduleService.update(schedule);
            }
        }

        unavailabilityDateRepository.deleteById(id);
    }

    @Override
    public UnavailabilityDate getById(UUID id) {
        return unavailabilityDateRepository.findById(id).orElseThrow(() -> new UnavailabilityDateNotFoundException(id));
    }

    private void updateStatusSchedulesByUpdateUnavailabilityDate(UnavailabilityDate currentUnavailabilityDate,
                                                                 UnavailabilityDate oldUnavailabilityDate) {
        List<ScheduleUpdateRequest> foundSchedulesForConfirmed = new ArrayList<>();

        if(oldUnavailabilityDate.getInitialDate().isBefore(currentUnavailabilityDate.getInitialDate())) {
            findAllCanceledSchedulesToConfirmedByInitialDate(currentUnavailabilityDate, oldUnavailabilityDate, foundSchedulesForConfirmed);
        } else if (currentUnavailabilityDate.getFinalDate().isBefore(oldUnavailabilityDate.getFinalDate())) {
            findAllCanceledSchedulesToConfirmedByFinalDate(currentUnavailabilityDate, oldUnavailabilityDate, foundSchedulesForConfirmed);
        } else if (!oldUnavailabilityDate.getInitialDate().isEqual(currentUnavailabilityDate.getInitialDate())
                || !oldUnavailabilityDate.getFinalDate().isEqual(currentUnavailabilityDate.getFinalDate())) {
            setAllConfirmedSchedulesToCanceled(currentUnavailabilityDate);
        }

        for (ScheduleUpdateRequest schedule : foundSchedulesForConfirmed) {
            if(schedule.getStatus().equals(SchedulesStatus.CANCELED_BY_CLINIC)) {
                schedule.setStatus(SchedulesStatus.CONFIRMED);
                scheduleService.update(schedule);
            }
        }
    }

    private void findAllCanceledSchedulesToConfirmedByInitialDate(UnavailabilityDate currentUnavailabilityDate, UnavailabilityDate oldUnavailabilityDate,
                                                    List<ScheduleUpdateRequest> foundSchedulesForConfirmed) {
        foundSchedulesForConfirmed.addAll(scheduleService.findAllByDateBetweenInitialDateAndFinalDate(oldUnavailabilityDate.getInitialDate(),
                currentUnavailabilityDate.getInitialDate().minusDays(1)));
    }

    private void findAllCanceledSchedulesToConfirmedByFinalDate(UnavailabilityDate currentUnavailabilityDate, UnavailabilityDate oldUnavailabilityDate,
                                                               List<ScheduleUpdateRequest> foundSchedulesForConfirmed) {
        foundSchedulesForConfirmed.addAll(scheduleService.findAllByDateBetweenInitialDateAndFinalDate(currentUnavailabilityDate.getFinalDate().plusDays(1),
                oldUnavailabilityDate.getFinalDate()));
    }

    private void setAllConfirmedSchedulesToCanceled(UnavailabilityDate unavailabilityDate) {
        List<ScheduleUpdateRequest> foundSchedulesForCanceledByClinic = scheduleService.findAllByDateBetweenInitialDateAndFinalDate(unavailabilityDate.getInitialDate(),
                unavailabilityDate.getFinalDate());

        for (ScheduleUpdateRequest schedule : foundSchedulesForCanceledByClinic) {
            if(schedule.getStatus().equals(SchedulesStatus.CONFIRMED)) {
                schedule.setStatus(SchedulesStatus.CANCELED_BY_CLINIC);
                scheduleService.update(schedule);
            }
        }
    }

}
