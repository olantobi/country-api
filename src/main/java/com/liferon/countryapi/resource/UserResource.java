package com.liferon.countryapi.resource;

import com.liferon.countryapi.model.LoginModel;
import com.liferon.countryapi.model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserResource {

    public ResponseEntity<?> signUp(@Valid @RequestBody UserModel userModel, BindingResult result) {


        return ResponseEntity.ok("");
    }

    public ResponseEntity<?> login(@Valid @RequestBody LoginModel loginModel, BindingResult result) {

        return ResponseEntity.ok("");
    }
}
