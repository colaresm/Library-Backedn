package com.library.libraryWDA.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "passwords_reset")
public class PasswordResetToken extends BaseEntity {

    private LocalDateTime dateExpiration;

    private Long code;

    @Email
    private String email;

    private Boolean confirmed;

    private Boolean changed;

}
