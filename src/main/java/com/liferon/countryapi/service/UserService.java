package com.liferon.countryapi.service;

import com.liferon.countryapi.model.LoginModel;
import com.liferon.countryapi.model.UserModel;

public interface UserService {
    String login(LoginModel loginModel);
    boolean register(UserModel userModel);
}
