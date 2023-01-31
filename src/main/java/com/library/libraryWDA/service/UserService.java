package com.library.libraryWDA.service;

import com.library.libraryWDA.dto.user.UserCreateRequest;
import com.library.libraryWDA.dto.user.UserUpdateRequest;
import com.library.libraryWDA.model.User;
import com.library.libraryWDA.model.enums.Position;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User create(UserCreateRequest userCreateRequest, Position professionalPosition);
    void addRoleToUser(String username, String roleName);
    User update(UserUpdateRequest userUpdateRequest);
    User getById(UUID id);
    User getUser(String username);
    List<User> findAll();
    void updatePassword(User user, String password);
}
