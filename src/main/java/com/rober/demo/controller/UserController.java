package com.rober.demo.controller;

import com.rober.demo.entity.User;
import com.rober.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<User> createUser(@RequestBody User user){
        Map<String, String> response = new LinkedHashMap<>();
        try{
            userRepository.save(user);
            response.put("Success","user registered");
            response.put("message","user registered success");
            response.put("status",HttpStatus.OK.toString());
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception e){
            response.put("error","Error");
            response.put("message","No se registro a ninguno");
            response.put("status",HttpStatus.INTERNAL_SERVER_ERROR.toString());
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @RequestMapping(value="api/users",method=RequestMethod.GET)
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value="api/users/{id}",method = RequestMethod.PUT)
    public ResponseEntity<User> editUser(@RequestBody User newUser , @PathVariable Long id){
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
            return new ResponseEntity(response,HttpStatus.OK);

        }catch (Exception e){
            response.put("error","Not found");
            response.put("message","User not found!");
            response.put("status",HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }


    }
    @RequestMapping(value="api/users/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        try{
            User user = userRepository.findById(id).get();

            userRepository.delete(user);
            response.put("Success","user delete");
            response.put("message","user delete success");
            response.put("status",HttpStatus.OK.toString());
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception e){
            response.put("error","Not found");
            response.put("message","User not found!");
            response.put("status",HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }




    }
}
