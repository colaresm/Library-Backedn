package com.library.libraryWDA.config;


import com.library.libraryWDA.dto.address.AddressRequest;
import com.library.libraryWDA.dto.client.ClientCreateRequest;
import com.library.libraryWDA.dto.user.UserCreateRequest;
import com.library.libraryWDA.model.Role;
import com.library.libraryWDA.model.enums.Position;
import com.library.libraryWDA.repository.ClientRepository;
import com.library.libraryWDA.repository.RoleRepository;
import com.library.libraryWDA.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Configuration
public class InitialAuthenticationConfig {

    @Autowired
    ClientService professionalService;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ClientRepository professionalRepository;

    @Value("${default.user.email}") String defaultUserEmail;
    @Value("${default.user.password}") String defaultUserPassword;

    @PostConstruct
    private void initializeRoles(){
        if(roleRepository.count() == 0) {
            roleRepository.save(new Role("ROLE_ADMIN"));
            roleRepository.save(new Role("ROLE_ATTENDANT"));
            roleRepository.save(new Role("ROLE_DOCTOR"));

            initializeUser();
        }
    }

    private void initializeUser(){
        if(professionalRepository.count() == 0) {
            UserCreateRequest user = new UserCreateRequest(defaultUserEmail, defaultUserPassword);
            AddressRequest address = new AddressRequest("Av. Dom Luís, 209", "60160-230", "Ceará", "Meireles", "Fortaleza", "Próximo a paraça Portugal");
            ClientCreateRequest admin = new ClientCreateRequest();
            admin.setUserCreateRequest(user);
            admin.setAdmission(LocalDate.parse("2020-08-23"));
            admin.setBirthDate(LocalDate.parse("2000-10-20"));
            admin.setCrm("555533/CE");
            admin.setPosition(Position.valueOf("ADMIN"));
            admin.setCpf("824.994.050-40");
            admin.setEmail("canonecgtest@gmail.com");
            admin.setFirstPhone("86997685455");
            admin.setName("library Admin");
            admin.setRg("983892839398");
            admin.setSecondPhone("85998009898");
            admin.setAddressRequest(address);
            professionalService.create(admin);
        }
    }


}
