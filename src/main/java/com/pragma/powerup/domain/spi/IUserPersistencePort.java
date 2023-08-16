package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.UserModel;


import java.util.List;

public interface IUserPersistencePort {
    UserModel saveUser(UserModel user);
    UserModel findByID(Long id);
    UserModel findOneByEmail(String email);
    UserModel findOneByPassword(String password);
    List<UserModel> getAllUsers();
}
