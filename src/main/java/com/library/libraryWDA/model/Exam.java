package com.library.libraryWDA.model;
import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "exams")
public class Exam extends BaseEntity{

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;
   
}
