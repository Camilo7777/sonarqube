package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
class UserJpaAdapterTest {

    @Mock
    private  IUserRepository userRepositoryMock;
    @Mock
    private  IUserEntityMapper entityMapperMock;

    @InjectMocks
    UserJpaAdapter userJpaAdapterMock;


    @Test
    void saveUser() {
        UserModel userModelMock = new UserModel(1L,"1234","Pepe"
                ,"veraz","12345","notiene@gmail.com"
                ,"123456",2L);

        UserEntity userEntityMock = new UserEntity(1L,"1234",
                "pepe","veraz","234567","sitiene@gmail.com","34567",2L);


        Mockito.when(entityMapperMock.toEntity(any()))
                .thenReturn(userEntityMock);

        Mockito.when(userRepositoryMock.save(any()))
                .thenReturn(userEntityMock);

        Mockito.when(entityMapperMock.toUserModel(any()))
                .thenReturn(userModelMock);

        UserModel userModel = userJpaAdapterMock.saveUser(userModelMock);


        assertEquals(1L,userModel.getId());

    }

    @Test
    void getAllUsers() {

        Mockito.when(userRepositoryMock.findAll()).thenReturn(List.of());

        Mockito.when(entityMapperMock.toUserModelList(any()))
                .thenReturn(List.of(
                        new UserModel(1L,"1234","Pepe"
                                ,"veraz","12345","notiene@gmail.com"
                                ,"123456",2L)
                ));

        var result = userJpaAdapterMock.getAllUsers();

        Assertions.assertEquals(1L,result.get(0).getId());
    }

    @Test
    void findByID() {

        UserEntity userEntityMock = new UserEntity(1L,"1234",
                "pepe","veraz","234567","sitiene@gmail.com","34567",2L);

        UserModel userModelMock = new UserModel(1L,"1234","Pepe"
                ,"veraz","12345","notiene@gmail.com"
                ,"123456",2L);

        Mockito.when(userRepositoryMock.findById(anyLong()))
                .thenReturn(java.util.Optional.of(userEntityMock));

        Mockito.when(entityMapperMock.toUserModel(any()))
                .thenReturn(userModelMock);

        UserModel model = userJpaAdapterMock.findByID(1L);

        assertEquals(1L,model.getId());


    }


    @Test
    void findOneByEmail() {
        UserModel userModelMock = new UserModel(1L,"1234","Pepe"
                ,"veraz","12345","notiene@gmail.com"
                ,"123456",2L);

        UserEntity userEntityMock = new UserEntity(1L,"1234",
                "pepe","veraz","234567","sitiene@gmail.com","34567",2L);

      Mockito.when(userRepositoryMock.findOneByEmail(any())).thenReturn(userEntityMock);
      Mockito.when(entityMapperMock.toUserModel(any()))
              .thenReturn(userModelMock);

      UserModel userModel = userJpaAdapterMock.findOneByEmail(any());

      assertEquals("veraz",userModel.getLastName());

    }










}