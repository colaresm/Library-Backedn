package com.library.libraryWDA.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Getter
@Setter
@Table(name = "queries")
public class Query extends BaseEntity{

    @Column
    private String notes;

    @Column
    private String prescription;
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id" )
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professional_id")
    private Client professional;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "anamnese_id")
    private Anamnese anamnese;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> attachments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Exam> exams= new ArrayList<>();
}
