package com.library.libraryWDA.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotBlank(message = "O campo endereço não deve estar em branco")
    @Size(min = 3, max = 40, message = "O tamanho do campo endereço deve ser entre {min} e {max}")
    private String address;

    @NotBlank(message = "O campo CEP não deve estar em branco")
    @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$", message = "O campo CEP aceita somente números e nesse formato: 00000-000")
    private String cep;

    @NotBlank(message = "O campo estado não deve estar em branco")
    @Pattern(regexp = "^[\\pL\\pM\\p{Zs}.-]{3,}$", message = "O campo estado não aceita números ou caracteres especiais")
    private String state;

    @NotBlank(message = "O campo bairro não deve estar em branco")
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ 0-9]+$", message = "O campo bairro não aceita caracteres especiais")
    private String neighborhood;

    @NotBlank(message = "O campo cidade não deve estar em branco")
    @Pattern(regexp = "^[\\pL\\pM\\p{Zs}.-]{3,}$", message = "O campo cidade não aceita números ou caracteres especiais")
    private String city;

    @Pattern(regexp="(^$|.{3,40})", message = "O tamanho do campo complemento deve ser entre 3 e 40")
    private String complement;
}