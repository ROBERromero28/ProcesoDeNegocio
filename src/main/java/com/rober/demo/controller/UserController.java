package com.rober.demo.controller;

import com.rober.demo.entity.User;
import com.rober.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.rober.demo.util.Message;

import java.util.*;

@RestController
public class UserController {


    @Autowired
    private UserRepository userRepository;
    private Message message = new Message();

    @RequestMapping(value="api/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional> GetUser(@PathVariable Long id){
        Optional<User> foundUser=userRepository.findById(id);

        if(foundUser.isPresent()){
            return message.viewMessage(HttpStatus.OK,"success","User found");

        }
        return message.viewMessage(HttpStatus.NOT_FOUND,"Not found","User not found");
    }

    @RequestMapping(value = "api/users",method = RequestMethod.POST)
    public ResponseEntity<Optional> createUser(@RequestBody User user){
        Map<String, String> response = new LinkedHashMap<>();
        try{
            userRepository.save(user);
            return message.viewMessage(HttpStatus.OK,"success","registered user success!");
        }catch (Exception e){
            return message.viewMessage(HttpStatus.INTERNAL_SERVER_ERROR,"error","An error occurred while registering the user!");
        }


    }
    @RequestMapping(value="api/users",method=RequestMethod.GET)
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value="api/users/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Optional> editUser(@RequestBody User newUser , @PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        try{
            User user = userRepository.findById(id).get();
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            response.put("Success","user edit");
            response.put("message","user edit success");
            response.put("status",HttpStatus.OK.toString());
            userRepository.save(user);
            return message.viewMessage(HttpStatus.OK,"success","user edit success!!");

        }catch (Exception e){
            return message.viewMessage(HttpStatus.NOT_FOUND,"error","User not found!");
        }


    }
    @RequestMapping(value="api/users/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Optional> deleteUser(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        try{
            User user = userRepository.findById(id).get();

            userRepository.delete(user);
            return message.viewMessage(HttpStatus.OK,"success","user delete success!!");
        }catch (Exception e){
            return message.viewMessage(HttpStatus.NOT_FOUND,"error","User not found!");
        }




    }
}
