package com.library.libraryWDA.model;

import lombok.Data;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.library.libraryWDA.model.enums.Weekday;

import org.hibernate.annotations.Type;


@Data
@Entity
@Table(name = "ProfessionalAvailabilitys")
public class ProfessionalAvailability extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professional_id")
    private Client professional;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Weekday weekday;

    @NotNull  
    private LocalTime time;
    
    @NotNull
    private Duration duration;
}
