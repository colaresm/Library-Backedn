package com.library.libraryWDA.dto.clinicalHistoryPatient;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalHistoryPatientCreateRequest {

  private UUID patientId;

  @NotBlank(message = "O título do título não pode estar em branco")
  @Size(min = 3, max = 50, message = "O tamanho do texto da informação adicional deve ser entre {min} e {max}")
  private String title;

  @NotBlank(message = "O texto do histórico não pode estar em branco")
  @Size(min = 3, max = 50, message = "O tamanho do texto da informação adicional deve ser entre {min} e {max}")
  private String content; 
  
}
