package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findOneByEmail(String email);
    UserEntity findOneByPassword(String password);

}
