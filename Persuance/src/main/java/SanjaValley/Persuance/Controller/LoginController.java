package SanjaValley.Persuance.Controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import SanjaValley.Persuance.Entity.UserModel;
import SanjaValley.Persuance.Model.LoginRequest;
import SanjaValley.Persuance.security.JWTUtils;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager auth;

    @PostMapping("/login")
    @CrossOrigin(exposedHeaders = "Token")
    public ResponseEntity<?> login(@RequestBody LoginRequest login, HttpServletResponse response) throws JsonProcessingException {
        Authentication credentials = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        SecurityContextHolder.getContext().setAuthentication(credentials);

        UserModel usuario = (UserModel) auth.authenticate(credentials).getPrincipal();
        response.setHeader("Token", JWTUtils.generateToken(usuario));
        return new ResponseEntity<UserModel>(usuario, HttpStatus.OK);
    }
}