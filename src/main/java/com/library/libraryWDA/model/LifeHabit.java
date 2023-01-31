package com.library.libraryWDA.model;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "lifeHabits")
public class LifeHabit extends BaseEntity{
    private String socialFactors;
    private String familyFactors;
}
