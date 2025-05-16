package com.example.mapper;

import com.example.dto.UserDtoInput;
import com.example.dto.UserDtoOutput;
import com.example.model.User;

import java.util.HashSet;

public class UserMapper {
    public static UserDtoOutput toUserDto(final User user) {
        final UserDtoOutput userDtoOutput = new UserDtoOutput();

        userDtoOutput.setId(user.getId());
        userDtoOutput.setName(user.getName());
        userDtoOutput.setEmail(user.getEmail());

        return userDtoOutput;
    }

    public static User toUser(final UserDtoInput userDtoInput) {
        final User user = new User();

        user.setName(userDtoInput.getName());
        user.setEmail(userDtoInput.getEmail());
        user.setSubscriptions(new HashSet<>());

        return user;
    }
}
