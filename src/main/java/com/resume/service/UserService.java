package com.resume.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resume.model.User;
import com.resume.repositories.RoleRepository;
import com.resume.repositories.UserRepository;

import java.util.Arrays;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Long countByEmail(String email) {
        return userRepository.countByEmail(email);
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public void saveUser(User user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("Job Seeker")));
        user.setEnabled(true);
        userRepository.save(user);
    }
    public void saveAdmin(User user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("Recruiter")));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
