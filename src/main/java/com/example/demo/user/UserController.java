package com.example.demo.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
//@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;


    @GetMapping("/api/v1/users")
    public Map<String, Object> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/api/v1/login")
    public Map<String, Object> loginUser(@RequestBody User data){
        return userService.login(data.getEmail(), data.getPassword());
    }

    @GetMapping("/api/v1/users/{id}")
    @ResponseBody
    public Object getParticularUsers(@PathVariable String id){
        return userService.getParticularUsers(id);
    }

    @PostMapping("/api/v1/users")
    @ResponseBody
    public Object createUser(@RequestBody User data){
        return userService.createParticular(data);
    }

    @PutMapping("/api/v1/users")
    @ResponseBody
    public Object updateUser(@RequestBody User data){
        return userService.updateParticularUser(data);
    }

    @DeleteMapping("/api/v1/users/{id}")
    @ResponseBody
    public Object deleteParticularUsers(@PathVariable String id){
        return userService.deleteParticularUsers(id);
    }
}
