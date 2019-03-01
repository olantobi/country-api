package com.liferon.countryapi.service;

import com.liferon.countryapi.domain.User;
import com.liferon.countryapi.model.UserModel;

import java.text.ParseException;
import java.util.Optional;

public interface UserService {
    boolean register(UserModel userModel) throws ParseException;
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
