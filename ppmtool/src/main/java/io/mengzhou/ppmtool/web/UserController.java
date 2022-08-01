package io.mengzhou.ppmtool.web;


import io.mengzhou.ppmtool.domain.User;
import io.mengzhou.ppmtool.payload.JWTLoginSucessReponse;
import io.mengzhou.ppmtool.payload.LoginRequest;
import io.mengzhou.ppmtool.security.JwtTokenProvider;
import io.mengzhou.ppmtool.services.MapErrorValidationService;
import io.mengzhou.ppmtool.services.UserService;
import io.mengzhou.ppmtool.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static io.mengzhou.ppmtool.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private MapErrorValidationService mapErrorValidationService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> register(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapErrorValidationService.mapErrorValidationService(result);
        if(errorMap != null) return errorMap;
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtTokenProvider.generateToken(authenticate);
        return ResponseEntity.ok(new JWTLoginSucessReponse(TOKEN_PREFIX + token, true));
    }

        @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return mapErrorValidationService.mapErrorValidationService(result);
        }
        user.setConfirmPassword("");
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }
}
