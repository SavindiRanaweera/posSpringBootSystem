package com.ijse.springpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ijse.springpos.entity.User;
import com.ijse.springpos.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
     @Autowired
     private UserRepository userRepository;

     @Autowired
     private PasswordEncoder passwordEncoder;

     @Override
     public List<User> getAllUsers(){
        return userRepository.findAll();
     }

     @Override
     public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
     }

     @Override
     public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
         return userRepository.save(user);
     }
 
     @Override
     public User updateUser(Long id, User user) {
         User existUser = userRepository.findById(id).orElse(null);
         
         if(existUser != null) {
             existUser.setUsername(user.getUsername());
             existUser.setEmail(user.getEmail());
             existUser.setPassword(user.getPassword());
             return userRepository.save(existUser);
         } else {
             return null;
         }
     }
 
     @Override
     public void deleteUser(Long id) {
         userRepository.deleteById(id);
     }
}
