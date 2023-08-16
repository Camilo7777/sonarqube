package com.pragma.powerup.security;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RegisterUserDetailsService implements UserDetailsService {


    @Autowired
    private IUserPersistencePort userPersistencePort;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userPersistencePort.findOneByEmail(username);
        if (userModel != null) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            Optional<RoleEntity> result = roleRepository.findById(userModel.getRoleId());
            result.ifPresent(roleEntity -> authorities.add(new SimpleGrantedAuthority(roleEntity.getName())));
            return new User(userModel.getEmail(), userModel.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("Username Not Found");
    }


}
