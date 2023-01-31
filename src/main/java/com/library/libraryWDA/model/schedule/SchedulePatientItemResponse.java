package com.library.libraryWDA.model.schedule;
 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.library.libraryWDA.model.BaseEntity;

import lombok.Data;


@Data
@Entity
@Table(name= "patients")
public class SchedulePatientItemResponse extends BaseEntity{
    @Column(nullable = false)
    private String name;
}
