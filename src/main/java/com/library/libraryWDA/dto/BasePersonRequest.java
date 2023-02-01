package com.library.libraryWDA.dto;

import com.library.libraryWDA.dto.address.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BasePersonRequest {

    @NotBlank(message = "O campo nome não deve estar em branco")
    @Size(max = 60, message = "O tamanho do campo nome deve ser entre {min} e {max}")
    private String name;

    @CPF
    private String cpf;

    @Email
    @Size(max = 60, message = "O tamanho do campo e-mail deve ser entre {min} e {max}")
    private String email;


    private MultipartFile profilePicture;

}
