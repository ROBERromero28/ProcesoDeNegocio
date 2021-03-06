package com.rober.demo.controller;

import com.rober.demo.entity.User;
import com.rober.demo.repository.UserRepository;
import com.rober.demo.util.JWTUtil;
import com.rober.demo.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    private User user;
    private Message message;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value="api/auth/login",method= RequestMethod.POST)
    public ResponseEntity login(@RequestBody User userReq){
        try{
            message=new Message();
            user= userRepository.findByEmail(userReq.getEmail());
            if(passwordEncoder.matches(userReq.getPassword(),user.getPassword())){
                String token = jwtUtil.create(String.valueOf(user.getId()), user.getEmail());
                return message.viewMessage(HttpStatus.OK, "Login", token);

            }

            return message.viewMessage(HttpStatus.UNAUTHORIZED,"UNAUTHORIZED","invalid username or password");
        }catch (Exception e){

            return message.viewMessage(HttpStatus.INTERNAL_SERVER_ERROR,"error","An error occurred while login the user!"+e.getLocalizedMessage());
        }

    }
}
