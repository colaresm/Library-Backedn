package com.library.libraryWDA.dto.client;

import com.library.libraryWDA.dto.address.AddressRequest;
import com.library.libraryWDA.dto.user.UserListResponse;
import com.library.libraryWDA.model.Document;
import com.library.libraryWDA.model.enums.Position;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ClientDetailsResponse {

    private UUID id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate admission;

    private String crm;
  
    private String name;

    private String cpf;   

    private String email;
 
    private String firstPhone;

    private String secondPhone;

    private String rg;

    private Document profilePicture;

    private AddressRequest address;

    private UserListResponse user;

    private Position position;
}
