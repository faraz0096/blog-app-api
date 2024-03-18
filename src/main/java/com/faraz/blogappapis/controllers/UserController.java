package com.faraz.blogappapis.controllers;

import com.faraz.blogappapis.entities.User;
import com.faraz.blogappapis.exceptions.ResourceNotFoundException;
import com.faraz.blogappapis.payloads.APIResponse;
import com.faraz.blogappapis.payloads.UserDTO;
import com.faraz.blogappapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
    {

        UserDTO createUserDto =  this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDto , HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO , @PathVariable Integer userId)
    {

        UserDTO updateUser = this.userService.updateUser(userDTO , userId);
        return  new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Integer userId) {

            this.userService.deleteUser(userId);
            return new ResponseEntity<>(new APIResponse("User Deleted Successfully", true), HttpStatus.OK);

    }


    @GetMapping("/")
    public ResponseEntity<List<UserDTO>>getAllUsers()
    {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId)
    {
            return ResponseEntity.ok(userService.getUserById(userId));

    }


}
