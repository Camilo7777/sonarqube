package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class UserHandler implements IUserHandler {
    private final IUserServicePort userServicePort;

    private final IUserRequestMapper userRequestMapper;

    private final IUserResponseMapper userResponseMapper;

    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
      userServicePort.saveUser(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userResponseMapper.toResponseList(userServicePort.getAllUsers());
    }

    @Override
    public UserResponseDto findByID(Long id) {
        return userResponseMapper.toUserResponseDto(userServicePort.findByID(id));
    }

    @Override
    public UserResponseDto findOneByEmail(String email) {
        return userResponseMapper.toUserResponseDto(userServicePort.findOneByEmail(email));
    }
}
