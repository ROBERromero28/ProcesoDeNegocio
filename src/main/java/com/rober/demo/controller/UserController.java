package com.rober.demo.controller;

import com.rober.demo.entity.User;
import com.rober.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value="api/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> GetUser(@PathVariable Long id){
        Optional<User> foundUser=userRepository.findById(id);

        if(foundUser.isPresent()){
            return ResponseEntity.ok(foundUser.get());
        }
        Map<String,String> errorResponse = new HashMap<>();
        errorResponse.put("error","NOT FOUND");
        errorResponse.put("message","User NOT FOUND");
        errorResponse.put("status", HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity(errorResponse,HttpStatus.NOT_FOUND );
    }

    @RequestMapping(value = "api/users",method = RequestMethod.POST)
    public User createUser(@RequestBody User user){

        return userRepository.save(user);

    }
    @RequestMapping(value="api/users",method=RequestMethod.GET)
    public List<User> listUsers(){
        return userRepository.findAll();
    }
}
