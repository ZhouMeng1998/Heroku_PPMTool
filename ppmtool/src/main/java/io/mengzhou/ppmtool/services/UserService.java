package io.mengzhou.ppmtool.services;

import io.mengzhou.ppmtool.domain.User;
import io.mengzhou.ppmtool.exception.UsernameRepeatException;
import io.mengzhou.ppmtool.repositories.UserRepository;
import io.mengzhou.ppmtool.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User createUser(User newUser) {
        try {
            String encryptedPwd = bCryptPasswordEncoder.encode(newUser.getPassword());
            newUser.setPassword(encryptedPwd);
            return userRepository.save(newUser);
        }catch (Exception e) {
            throw new UsernameRepeatException("'" + newUser.getUsername() + "' already exists");
        }

    }

}
