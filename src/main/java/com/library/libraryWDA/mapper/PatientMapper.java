package com.library.libraryWDA.mapper;

import com.library.libraryWDA.dto.patient.PatientCreateRequest;
import com.library.libraryWDA.dto.patient.PatientListItemResponse;
import com.library.libraryWDA.dto.patient.PatientResponse;
import com.library.libraryWDA.dto.patient.PatientSimplifiedCreateRequest;
import com.library.libraryWDA.dto.patient.PatientReminderListItemResponse;
import com.library.libraryWDA.dto.patient.PatientUpdateRequest;
import com.library.libraryWDA.model.Document;
import com.library.libraryWDA.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "address", source = "addressRequest")
    @Mapping(target = "profilePicture", source = "profilePicture", qualifiedByName = "multipartFileToDocument")
    Patient toModel(PatientCreateRequest patientCreateRequest);
    
    @Mapping(target = "address", source = "addressRequest")
    Patient toModelSimplified(PatientSimplifiedCreateRequest patientSimplifiedCreateRequest);

    @Mapping(target = "patientAddressListItemResponse", source = "address")
    PatientListItemResponse toPatientListItemResponse(Patient patient);

    @Mapping(target = "address", source = "addressRequest")
    @Mapping(target = "profilePicture", source = "profilePicture", qualifiedByName = "multipartFileToDocument")
    Patient toModel(PatientUpdateRequest patientUpdateRequest);
    @Mapping(target = "addressResponse", source = "address")
    PatientResponse toPatientResponse(Patient patient);

    @Named("multipartFileToDocument")
    static Document multipartFileToDocument(MultipartFile multipartFile) throws IOException {
        if(multipartFile == null) {
            return null;
        }

        Document profilePicture = new Document();

        profilePicture.setName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        profilePicture.setContent(multipartFile.getBytes());
        profilePicture.setSize(multipartFile.getSize());
        profilePicture.setType(multipartFile.getContentType());

        return profilePicture;
    }

    PatientReminderListItemResponse toPatientReminderListItemResponse(Patient patient);
}