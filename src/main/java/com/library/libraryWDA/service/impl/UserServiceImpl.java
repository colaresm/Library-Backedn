package com.library.libraryWDA.service.impl;

import com.library.libraryWDA.dto.user.UserCreateRequest;
import com.library.libraryWDA.dto.user.UserUpdateRequest;
import com.library.libraryWDA.exception.user.UserNotFoundException;
import com.library.libraryWDA.mapper.UserMapper;
import com.library.libraryWDA.model.Role;
import com.library.libraryWDA.model.User;
import com.library.libraryWDA.model.enums.Position;
import com.library.libraryWDA.repository.RoleRepository;
import com.library.libraryWDA.repository.UserRepository;
import com.library.libraryWDA.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User create(UserCreateRequest userCreateRequest, Position professionalPosition) {
        User userToCreate = userMapper.toModel(userCreateRequest);

        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
        User userCreated = userRepository.save(userToCreate);

        switch(professionalPosition) {
            case ADMIN: addRoleToUser(userToCreate.getUsername(), "ROLE_ADMIN");
                break;

            case PEOPLE: addRoleToUser(userToCreate.getUsername(), "ROLE_PEOPLE");
                break;

            default: addRoleToUser(userToCreate.getUsername(), "ROLE_DOCTOR");
                break;
        }

        return userCreated;
    }

    @Override
    public User update(UserUpdateRequest userUpdateRequest) {
        User foundUser = getById(userUpdateRequest.getId());

        User userToUpdate = userMapper.toModel(userUpdateRequest);

        userToUpdate.setCreatedAt(foundUser.getCreatedAt());
        userToUpdate.setRoles(foundUser.getRoles());
        userToUpdate.setPassword(passwordEncoder.encode(userToUpdate.getPassword()));
        return userRepository.save(userToUpdate);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = getUser(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    @Override
    public User getById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void updatePassword(User user, String password){
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }


}


