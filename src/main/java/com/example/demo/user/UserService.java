package com.example.demo.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    public Map<String, Object> getUsers(){
        Map<String, Object> map = new HashMap<>();
        map.put("status",200);
        map.put("body",userRepository.findAll());
        map.put("message","Success");
        return map;
    }

    public Map<String, Object> getParticularUsers(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("status",200);
        map.put("body",userRepository.findById(id));
        map.put("message","Success");
        return map;
    }

    public Map<String, Object> deleteParticularUsers(String id){
        Map<String, Object> map = new HashMap<>();
        userRepository.findById(id).ifPresentOrElse((u)->{
            userRepository.deleteById(id);
            map.put("status", 200);
            map.put("body", "");
            map.put("message", "successfully deleted");
        }, () -> {
            map.put("status", 401);
            map.put("body", "");
            map.put("message", "User doesnot exists");
        });

        return map;
    }


    public Map<String, Object> createParticular(User data){
        Map<String, Object> map = new HashMap<>();
        String  originalPassword = data.getPassword();
        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
        data.setPassword(generatedSecuredPasswordHash);

//        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
//        System.out.println(matched);
        userRepository.findUserByEmail(data.getEmail())
                .ifPresentOrElse(u -> {
                    System.out.println("Email already exists");
                    map.put("status", 401);
                    map.put("message", "Email Already Exists");


                }, () -> {
                    userRepository.insert(data);
                    map.put("status", 200);
                    map.put("message", "User Inserted");

                    System.out.println("User Inserted");
                });
        return map;
    }

    public Map<String, Object> login(String email, String password){
        Map<String, Object> map = new HashMap<>();
        String  originalPassword = password;


        userRepository.findUserByEmail(email)
                .ifPresentOrElse(u -> {
                    User user = userRepository.getUserByEmail(email);

                    boolean matched = BCrypt.checkpw(originalPassword, user.getPassword());
                    if(matched){
                        map.put("status", 200);
                        map.put("message", "Login Successful");
                        map.put("data", user);
                    }else{
                        map.put("status", 401);
                        map.put("message", "Email and password didnot matched");
                        
                    }


                }, () -> {
//                    userRepository.insert(data);
                    map.put("status", 200);
                    map.put("message", "User Not found. Please register first");

                    System.out.println("User Not found. Please register first");
                });
        return map;
    }

    public Map<String, Object> updateParticularUser(User data){
        Map<String, Object> map = new HashMap<>();
        userRepository.findById(data.getId())
                .ifPresentOrElse(u -> {
                    userRepository.save(data);
                    System.out.println("Updated Successfully");
                    map.put("status", 200);
                    map.put("message", "Updated Successfully");
                }, () -> {


                    map.put("status", 401);
                    map.put("message", "User doesnot exists");

                    System.out.println("User doesnot exists");
                });;
        return map;
    }
 }
