package com.liferon.countryapi.service.impl;

import com.liferon.countryapi.domain.User;
import com.liferon.countryapi.model.UserModel;
import com.liferon.countryapi.repository.UserRepository;
import com.liferon.countryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    @Override
    public boolean register(UserModel userModel) throws ParseException {
        User user = new User(userModel);
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        user.setDateOfBirth(simpleDateFormat.parse(userModel.getDateOfBirth()));

        User userResponse = userRepository.save(user);
        if(userResponse != null)
            return true;
        else
            return false;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
