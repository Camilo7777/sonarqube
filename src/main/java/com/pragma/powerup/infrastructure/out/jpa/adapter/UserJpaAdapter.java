package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper entityMapper;
    @Override
    public UserModel saveUser(UserModel user) {
        UserEntity userEntity = userRepository.save(entityMapper.toEntity(user));
        return entityMapper.toUserModel(userEntity);
    }
    @Override
    public List<UserModel> getAllUsers() {
        List<UserEntity> entityList = userRepository.findAll();
        return entityMapper.toUserModelList(entityList);
    }
    @Override
    public UserModel findByID(Long id) {
        return entityMapper.toUserModel(userRepository.findById(id).get());
    }

    @Override
    public UserModel findOneByEmail(String email) {
        UserEntity userEntity =userRepository.findOneByEmail(email);
        return entityMapper.toUserModel(userEntity);
    }
    @Override
    public UserModel findOneByPassword(String password) {
        UserEntity userEntity =userRepository.findOneByPassword(password);
        return entityMapper.toUserModel(userEntity);
    }
}
