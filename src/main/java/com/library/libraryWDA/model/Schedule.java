package com.library.libraryWDA.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.library.libraryWDA.model.enums.SchedulesStatus;

import lombok.Data;
 

@Data
@Entity
@Table(name = "schedules")
public class Schedule  extends BaseEntity{
 
    @Column(nullable = false)
    private LocalDate date; 

    @Column(nullable = false)
    private LocalTime time;  

    @Column(nullable = false)
    private Duration duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id" )
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professional_id")
    private Client professional;

    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SchedulesStatus status;

}
