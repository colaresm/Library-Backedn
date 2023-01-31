package com.library.libraryWDA.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private String address;

    private String cep;

    private String state;

    private String neighborhood;

    private String city;

    private String complement;
}
