package com.liferon.countryapi.resource;

import com.liferon.countryapi.exception.InternalServerErrorException;
import com.liferon.countryapi.exception.InvalidRequestParameterException;
import com.liferon.countryapi.model.UserModel;
import com.liferon.countryapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping("/signup")
    @ApiOperation(nickname = "/signup", value = "Sign up")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful signed up"),
            @ApiResponse(code = 400, message = "Invalid request parameters")
    })
    public ResponseEntity<?> signUp(@Valid @RequestBody UserModel userModel, BindingResult result) throws InvalidRequestParameterException, InternalServerErrorException {

        if (result.hasFieldErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(p -> p.getDefaultMessage()).collect(Collectors.joining("\n"));
            throw new InvalidRequestParameterException("Bad Request", errors);
        }

        Optional<?> userOptional = userService.findByUsername(userModel.getUsername());
        if (userOptional.isPresent()) {
            throw new InvalidRequestParameterException("Bad Request", "User with username "+ userModel.getUsername() +" already exists");
        }

        userOptional = userService.findByEmail(userModel.getEmail());
        if (userOptional.isPresent()) {
            throw new InvalidRequestParameterException("Bad Request", "User with email "+ userModel.getEmail() +" already exists");
        }
        boolean isRegistered = false;

        try {
            isRegistered = userService.register(userModel);
        } catch (ParseException ex) {
            throw new InvalidRequestParameterException("Bad Request", "Invalid date format for date of birth");
        }

        if (isRegistered)
            return ResponseEntity.ok("User created ");
        else
            throw new InternalServerErrorException("An error has occurred", "Unable to save user credentials");
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginModel loginModel, BindingResult result, HttpServletRequest request) {
//            request.setAttribute(
//                    View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
//
//        return ResponseEntity.ok("");
//    }
}
