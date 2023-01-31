package com.library.libraryWDA.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "anamnesis")
public class Anamnese extends BaseEntity{
    private String chiefComplaint;
    private String currentHistory;
    private String revisionSystems;
    private String pathologyHistory;
    private String familyHistory;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "life_habit_id")
    private LifeHabit lifeHabit;
 
}
