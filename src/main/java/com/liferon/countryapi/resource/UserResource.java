package com.liferon.countryapi.resource;

import com.liferon.countryapi.model.LoginModel;
import com.liferon.countryapi.model.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserResource {

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserModel userModel, BindingResult result) {


        return ResponseEntity.ok("");
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginModel loginModel, BindingResult result, HttpServletRequest request) {
//            request.setAttribute(
//                    View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
//
//        return ResponseEntity.ok("");
//    }
}
