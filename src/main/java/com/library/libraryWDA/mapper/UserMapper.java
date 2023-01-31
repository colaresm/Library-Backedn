package com.library.libraryWDA.mapper;

import com.library.libraryWDA.dto.user.UserCreateRequest;
import com.library.libraryWDA.dto.user.UserUpdateRequest;
import com.library.libraryWDA.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(UserCreateRequest userCreateRequest);

    User toModel(UserUpdateRequest userUpdateRequest);
}
