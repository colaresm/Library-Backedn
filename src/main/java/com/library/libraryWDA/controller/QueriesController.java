package com.library.libraryWDA.controller;

import com.library.libraryWDA.controller.docs.QueriesControllerDocs;
import com.library.libraryWDA.domain.PageResult;
import com.library.libraryWDA.dto.query.QueryCreateRequest;
import com.library.libraryWDA.dto.query.QueryListItemResponse;
import com.library.libraryWDA.dto.query.QueryUpdateRequest;
import com.library.libraryWDA.model.Document;
import com.library.libraryWDA.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/queries")
public class QueriesController implements QueriesControllerDocs{
    private final QueryService queryService;
    @Autowired
    public QueriesController(QueryService queryService) {
        this.queryService = queryService;
      
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @GetMapping("/{patientId}/page")
    public ResponseEntity<PageResult<QueryListItemResponse>> getPageByPatientId(@PathVariable UUID patientId,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageResult<QueryListItemResponse> pageResult = new PageResult(queryService.findAllByPatientId(patientId, pageable));
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@ModelAttribute @Valid QueryCreateRequest queryCreateRequest,
                       @RequestPart(value = "files", required = false) MultipartFile[] files,
                       HttpServletRequest request, HttpServletResponse response)throws IOException {

        List<Document> documents = new ArrayList<>();

        if(files != null) {
            for (MultipartFile attachment:files){
                Document document = new Document();
                document.setContent(attachment.getBytes());
                document.setSize(attachment.getSize() );
                document.setName(StringUtils.cleanPath(attachment.getOriginalFilename()));
                document.setType(attachment.getContentType());
                documents.add(document);
            }
        }

        queryCreateRequest.setAttachments(documents);

        queryService.create(queryCreateRequest, request, response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ATTENDANT', 'ROLE_DOCTOR')")
    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@ModelAttribute @Valid QueryUpdateRequest queryUpdateRequest,
                       @RequestPart(value = "files", required = false) MultipartFile[] files)throws IOException {
        
        List<Document> documents = new ArrayList<>();

        if(files != null) {
            for (MultipartFile attachment:files){
                Document document = new Document();
                document.setContent(attachment.getBytes());
                document.setSize(attachment.getSize() );
                document.setName(StringUtils.cleanPath(attachment.getOriginalFilename()));
                document.setType(attachment.getContentType());
                documents.add(document);
            }
        }
        
        queryUpdateRequest.setAttachments(documents);
        
        queryService.update(queryUpdateRequest);
    }
 
}
