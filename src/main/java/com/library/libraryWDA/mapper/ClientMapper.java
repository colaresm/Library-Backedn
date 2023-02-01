package com.library.libraryWDA.mapper;

import com.library.libraryWDA.dto.client.ClientCreateRequest;
import com.library.libraryWDA.dto.client.ClientDetailsResponse;
import com.library.libraryWDA.dto.client.ClientListItemResponse;
import com.library.libraryWDA.dto.client.ClientUpdateRequest;
import com.library.libraryWDA.model.Document;
import com.library.libraryWDA.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface ClientMapper {


    @Mapping(target = "user", source = "userCreateRequest")
    @Mapping(target = "profilePicture", source = "profilePicture", qualifiedByName = "multipartFileToDocument")
    Client toModel(ClientCreateRequest professionalCreateRequest);


    @Mapping(target = "profilePicture", source = "profilePicture", qualifiedByName = "multipartFileToDocument")
    Client toModel(ClientUpdateRequest professionalUpdateRequest);

    ClientListItemResponse toProfessionalListItemResponse(Client professional);

     
    ClientDetailsResponse toProfessionalDetailsResponse(Client professional);

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
}
