package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.dto.query.QueryCreateRequest;
import com.library.libraryWDA.dto.query.QueryListItemResponse;
import com.library.libraryWDA.dto.query.QueryUpdateRequest;
import com.library.libraryWDA.exception.query.QueryNotFoundException;
import com.library.libraryWDA.mapper.QueryMapper;
import com.library.libraryWDA.mapper.ScheduleMapper;
import com.library.libraryWDA.model.*;
import com.library.libraryWDA.model.enums.SchedulesStatus;
import com.library.libraryWDA.repository.QueryRepository;
import com.library.libraryWDA.service.*;
import com.library.libraryWDA.validation.QueryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class QueryServiceImpl implements QueryService  {
    private final QueryMapper queryMapper;

    private final QueryRepository queryRepository;

    private final QueryValidator queryValidator;

    private final PatientService patientService;

    private final ClientService professionalService;

    private final ScheduleService scheduleService;

    private final AuthenticationService authenticationService;

    private final ScheduleMapper scheduleMapper;

    private final ExamService examService;
    
    @Autowired
    public QueryServiceImpl(QueryMapper queryMapper, QueryRepository queryRepository, QueryValidator queryValidator,
                            PatientService patientService, ClientService professionalService,
                            ScheduleService scheduleService, AuthenticationService authenticationService,
                            ScheduleMapper scheduleMapper,ExamService examService) {
        this.queryMapper = queryMapper;
        this.queryRepository = queryRepository;
        this.queryValidator = queryValidator;
        this.patientService = patientService;
        this.professionalService = professionalService;
        this.scheduleService = scheduleService;
        this.authenticationService = authenticationService;
        this.scheduleMapper = scheduleMapper;
        this.examService=examService;
    }

    public void create(QueryCreateRequest queryCreateRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        queryValidator.validateForCreation(queryCreateRequest);

        Query queryToCreate =new Query();
        Anamnese anamneseToCreate= new  Anamnese();
        LifeHabit lifeHabitToCreate= new  LifeHabit();

        Map<String, String> userPayload = authenticationService.payload(request, response);

        Patient patientFound = patientService.getById(queryCreateRequest.getPatientId());
        Client professionalFound = professionalService.getById(UUID.fromString(userPayload.get("professionalId")));
        queryToCreate.setPatient(patientFound);
        queryToCreate.setProfessional(professionalFound);

        anamneseToCreate.setChiefComplaint(queryCreateRequest.getAnamnese().getChiefComplaint());
        anamneseToCreate.setCurrentHistory(queryCreateRequest.getAnamnese().getCurrentHistory());
        anamneseToCreate.setFamilyHistory(queryCreateRequest.getAnamnese().getFamilyHistory());
       
        lifeHabitToCreate.setFamilyFactors(queryCreateRequest.getAnamnese().getLifeHabit().getFamilyFactors());
        lifeHabitToCreate.setSocialFactors(queryCreateRequest.getAnamnese().getLifeHabit().getSocialFactors());

        anamneseToCreate.setLifeHabit(lifeHabitToCreate);

        anamneseToCreate.setPathologyHistory(queryCreateRequest.getAnamnese().getPathologyHistory());
        anamneseToCreate.setRevisionSystems(queryCreateRequest.getAnamnese().getRevisionSystems());

        queryToCreate.setAnamnese(anamneseToCreate);

        queryToCreate.setPrescription(queryCreateRequest.getPrescription());

        queryToCreate.setNotes(queryCreateRequest.getNotes());

        queryToCreate.setAttachments(queryCreateRequest.getAttachments());


        if(queryCreateRequest.getExams()!=null){

            List<Exam> exams = new ArrayList();

            for(UUID examId : queryCreateRequest.getExams()){
                Exam examFound = examService.getById(examId);
                exams.add(examFound);
            }
    
            queryToCreate.setExams(exams);
        
        }
      
        Schedule scheduleFound = scheduleService.getById(queryCreateRequest.getScheduleId());
        queryToCreate.setSchedule(scheduleFound);

        scheduleFound.setStatus(SchedulesStatus.COMPLETED);

        scheduleService.update(scheduleMapper.toScheduleUpdateRequest(scheduleFound));

        queryRepository.save(queryToCreate);
    }
   
    public Page<QueryListItemResponse> findAllByPatientId(UUID patientId, Pageable pageable) {
      return queryRepository.findAllByPatientId(patientId, pageable).map(queryMapper::toQueryListItemResponse);
    }
    
    public void update(QueryUpdateRequest queryUpdateRequest){
        Query foundQuery = getById(queryUpdateRequest.getId());

        Query queryToUpdate = new Query();
        Anamnese anamneseToUpdate= new  Anamnese();
        LifeHabit lifeHabitToUpdate= new  LifeHabit();

       

        Patient patientFound = patientService.getById(foundQuery.getPatient().getId());
        Client professionalFound = professionalService.getById(foundQuery.getProfessional().getId());
        Schedule scheduleFound = scheduleService.getById(foundQuery.getSchedule().getId());

        anamneseToUpdate.setChiefComplaint(queryUpdateRequest.getAnamnese().getChiefComplaint());
        anamneseToUpdate.setCurrentHistory(queryUpdateRequest.getAnamnese().getCurrentHistory());
        anamneseToUpdate.setFamilyHistory(queryUpdateRequest.getAnamnese().getFamilyHistory());
       
        lifeHabitToUpdate.setFamilyFactors(queryUpdateRequest.getAnamnese().getLifeHabit().getFamilyFactors());
        lifeHabitToUpdate.setSocialFactors(queryUpdateRequest.getAnamnese().getLifeHabit().getSocialFactors());

        anamneseToUpdate.setLifeHabit(lifeHabitToUpdate);

        anamneseToUpdate.setPathologyHistory(queryUpdateRequest.getAnamnese().getPathologyHistory());
        anamneseToUpdate.setRevisionSystems(queryUpdateRequest.getAnamnese().getRevisionSystems());

        queryToUpdate.setAnamnese(anamneseToUpdate);;

        queryToUpdate.setPrescription(queryUpdateRequest.getPrescription());

        queryToUpdate.setNotes(queryUpdateRequest.getNotes());

        queryToUpdate.setAttachments(queryUpdateRequest.getAttachments());


        if(queryUpdateRequest.getExams()!=null){

            List<Exam> exams = new ArrayList();

            for(UUID examId : queryUpdateRequest.getExams()){
                Exam examFound = examService.getById(examId);
                exams.add(examFound);
            }
            queryToUpdate.setExams(exams);
        }
        
        queryToUpdate.setId(queryUpdateRequest.getId());
        queryToUpdate.setCreatedAt(foundQuery.getCreatedAt());
        queryToUpdate.setPatient(patientFound);
        queryToUpdate.setProfessional(professionalFound);
        queryToUpdate.setSchedule(scheduleFound);
        queryRepository.save(queryToUpdate);
    }

    @Override
    public Query getById(UUID id){
        return queryRepository.findById(id).orElseThrow(() -> new QueryNotFoundException(id));
    }
}
