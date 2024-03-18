package com.faraz.blogappapis.services;

import com.faraz.blogappapis.entities.User;
import com.faraz.blogappapis.payloads.UserDTO;

import java.util.List;

public interface UserService {


    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user , Integer userId);
    UserDTO getUserById(Integer userId);
    List<UserDTO> getAllUsers();
    void deleteUser(Integer userId);
}
