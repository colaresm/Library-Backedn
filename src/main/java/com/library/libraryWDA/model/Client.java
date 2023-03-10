package com.library.libraryWDA.model;

import com.library.libraryWDA.model.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Clients")
public class Client extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstPhone;

    private String secondPhone;

    @Column(nullable = false, unique = true)
    private String rg;

    @Column(nullable = false)
    private LocalDate admission;

    @Column( )
    private String crm;

    @Column(columnDefinition = "boolean default true", insertable = false)
    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private Position position;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_picture_id")
    private Document profilePicture;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "professional")
    private List<Schedule> Schedules= new ArrayList<>();
 
    
     
}