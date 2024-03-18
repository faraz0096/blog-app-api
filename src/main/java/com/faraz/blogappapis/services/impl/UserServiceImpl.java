package com.faraz.blogappapis.services.impl;

import com.faraz.blogappapis.entities.User;
import com.faraz.blogappapis.exceptions.ResourceNotFoundException;
import com.faraz.blogappapis.payloads.UserDTO;
import com.faraz.blogappapis.repository.UserRepo;
import com.faraz.blogappapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user  = this.dtoToUser(userDTO);
        User savedUsers = this.userRepo.save(user);
        return this.userToDTO(savedUsers);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id" , userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUser = this.userRepo.save(user);
        UserDTO userDTO = this.userToDTO(updateUser);
        return userDTO;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" , "Id" , userId));

        return this.userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
       List<User> users =  this.userRepo.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

      List<UserDTO> userDTOS =  users.stream().map(user-> this.userToDTO(user)).collect(Collectors.toList());

      return userDTOS;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user
         = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" , "Id" , userId));
        this.userRepo.delete(user);
    }

    //Convert DTOUser to User
    private User dtoToUser(UserDTO userDTO){

        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    //Convert User to USERDTO
    private UserDTO userToDTO(User user)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAbout(user.getAbout());

        return userDTO;
    }
}
