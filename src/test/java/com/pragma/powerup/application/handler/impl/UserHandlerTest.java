package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(SpringExtension.class)
class UserHandlerTest {
    @Mock
    private  IUserServicePort userServicePortMock;

    @Mock
    private  IUserRequestMapper userRequestMapperMock;

    @Mock
    private  IUserResponseMapper userResponseMapperMock;
   @Mock
    private PasswordEncoder passwordEncoderMock;

    @InjectMocks
    private UserHandler userHandlerMock;

    @Test
    void saveUser() {
        UserRequestDto userRequestDtoMock =  UserRequestDto.builder()
                .id(1L)
                .roleId(2L)
                .phone("234")
                .email("sitiene@gmail.com")
                .documentNumber("12345")
                .fistName("Pepe")
                .lastName("Orozco")
                .password("dfsghjd")
                .build();


Mockito.when(passwordEncoderMock.encode(any())).thenReturn("ecfqrwe318");
        Mockito.when(userRequestMapperMock.toUser(any()))
                        .thenReturn(new UserModel());

        Mockito.doNothing()
                .when(userServicePortMock).saveUser(any());


        userHandlerMock.saveUser(userRequestDtoMock);
        Mockito.verify(userServicePortMock,Mockito.times(1))
                .saveUser(any());

    }



    @Test
    void findByID() {

        UserResponseDto userResponseDto =  UserResponseDto.builder()
                .id(1L)
                .roleId(2L)
                .phone("234")
                .email("sitiene@gmail.com")
                .documentNumber("12345")
                .fistName("Pepe")
                .lastName("Orozco")
                .password("dfsghjd")
                .build();

        Mockito.when(userServicePortMock.findByID(anyLong()))
                .thenReturn(new UserModel());

        Mockito.when(userResponseMapperMock.toUserResponseDto(any()))
                .thenReturn(userResponseDto);

        UserResponseDto result = userHandlerMock.findByID(1L);

        Assertions.assertEquals(1L,result.getId());

    }


    @Test
    void getAllUsers() {
        Mockito.when(userServicePortMock.getAllUsers()).thenReturn(List.of());
        Mockito.when(userResponseMapperMock.toResponseList(any()))
                .thenReturn(List.of(UserResponseDto.builder()
                                .id(2L)
                        .build()));

        var result = userHandlerMock.getAllUsers();
        Assertions.assertEquals(2L,result.get(0).getId());

    }

    @Test
    void findOneByEmail() {
        Mockito.when(userServicePortMock.findOneByEmail(any()))
                .thenReturn(new UserModel());
        Mockito.when(userResponseMapperMock.toUserResponseDto(any()))
                .thenReturn(UserResponseDto.builder()
                        .id(2L)
                        .build());

        var result = userHandlerMock.findOneByEmail(any());
        Assertions.assertEquals(2L,result.getId());
    }

}