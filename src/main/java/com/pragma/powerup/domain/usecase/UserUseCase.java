package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.DocumentNumberIncorrectException;
import com.pragma.powerup.domain.exception.EmailAlreadyExistsException;
import com.pragma.powerup.domain.exception.EmailIncorrectException;
import com.pragma.powerup.domain.exception.PnoneIncorrectException;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private static final int DIGITS = 13;

    public UserUseCase(IUserPersistencePort userPersistencePort) {

        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public void saveUser(UserModel user) {
        UserModel userModel = userPersistencePort.findOneByEmail((user.getEmail()));
        if (userModel != null){
            throw new EmailAlreadyExistsException();
        }
        if (!phoneVerify(user.getPhone())) {
            throw new PnoneIncorrectException();
        }

        if (!documentNumberVerify(user.getDocumentNumber())) {
            throw new DocumentNumberIncorrectException();
        }

        if (!emailVerify(user.getEmail())) {
            throw new EmailIncorrectException();
        }

        userPersistencePort.saveUser(user);
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> modelList = userPersistencePort.getAllUsers();
        if (modelList.isEmpty()) {
            throw new NoDataFoundException("there are no users");
        }

        return modelList;
    }

    @Override
    public  UserModel findByID(Long id) {
        return userPersistencePort.findByID(id);
    }

    @Override
    public UserModel findOneByEmail(String email) {
        return userPersistencePort.findOneByEmail(email);
    }

    private boolean phoneVerify(String phone) {
        return phone.replace("+", "").length() < DIGITS;
    }

    private boolean documentNumberVerify(String documentNumber) {
        return documentNumber.chars().allMatch(Character::isDigit);
    }

    private boolean emailVerify(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher mather = pattern.matcher(email);
         return mather.matches();
    }

}
