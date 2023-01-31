package com.library.libraryWDA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateRequest {

    @NotBlank(message = "O campo nome não deve estar em branco")
    private String name;
}
