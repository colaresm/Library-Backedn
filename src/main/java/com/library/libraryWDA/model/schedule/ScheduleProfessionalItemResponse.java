package com.library.libraryWDA.model.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.library.libraryWDA.model.BaseEntity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Clients")
public class ScheduleProfessionalItemResponse extends BaseEntity {
    @Column(nullable = false)
    private String name;
}
