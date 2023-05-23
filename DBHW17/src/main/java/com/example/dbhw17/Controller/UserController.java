package com.example.dbhw17.Controller;

import com.example.dbhw17.Model.User;
import com.example.dbhw17.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


    //get
    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        List<User> userList=userService.getAllUsers();
        return ResponseEntity.status(200).body(userList);
    }


    //add
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid@RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("user has ben added!");
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid@RequestBody User user, Errors errors ,@PathVariable Integer id){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated= userService.updateUser(user,id);
        if(isUpdated){
            return ResponseEntity.status(200).body("user has been updated!");
        }
        return ResponseEntity.status(400).body("user not found");

    }

    //delete

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id)
    {
        boolean isDelete= userService.deleteUser(id);
        if(isDelete)
        {
            return ResponseEntity.status(200).body("user has been deleted!");

        }
        return ResponseEntity.status(400).body("user not found");

    }


//    Get user by email
    @GetMapping("/get-email/{email}")
    public ResponseEntity findUserByEmail(@PathVariable String email){
        User user=userService.findUserByEmail(email);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/get-email-jpql/{email}")
    public ResponseEntity JPQLemail(@PathVariable String email){
        User user=userService.findUserByEmail(email);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/get-role/{role}")
    public ResponseEntity findUserByRole(@PathVariable String role){
        List<User> users=userService.findUserByRole(role);
        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("/get-user/{username}/{password}")
    public ResponseEntity findUserByUsernameAndPassword(@PathVariable String username,@PathVariable String password){
        User user=userService.findUserByUsernameAndPassword(username,password);
        return ResponseEntity.status(200).body(user);
    }
    @GetMapping("/get-by-age/{age}")
    public ResponseEntity findUserByAgeGreaterThanEqual(@PathVariable Integer age){
        List<User>users=userService.findUserByAgeGreaterThanEqual(age);
        return ResponseEntity.status(200).body(users);
    }


}
