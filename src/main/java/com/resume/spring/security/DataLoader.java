/*package com.resume.spring.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.resume.model.Role;
import com.resume.model.User;
import com.resume.repositories.RoleRepository;
import com.resume.repositories.UserRepository;



@Component
public class DataLoader implements CommandLineRunner{


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data . . .");

        roleRepository.save(new Role("JobSeeker"));
        roleRepository.save(new Role("Recruiter"));

        Role recruiterRole = roleRepository.findByRole("Recruiter");
        Role jobSeekerRole = roleRepository.findByRole("JobSeeker");

        User user = new User("bob@bob.com","bob","Bob","Bobberson", true, "bob");
        user.setRoles(Arrays.asList(jobSeekerRole));
        userRepository.save(user);

        user = new User("jim@jim.com","jim","Jim","Jimmerson", true, "jim");
        user.setRoles(Arrays.asList(jobSeekerRole));
        userRepository.save(user);

        user = new User("admin@secure.com","password","Admin","User", true, "admin");
        user.setRoles(Arrays.asList(recruiterRole));
        userRepository.save(user);

        user = new User("sam@every.com","password","Sam","Everyman", true, "everyman");
        user.setRoles(Arrays.asList(jobSeekerRole, recruiterRole));
        userRepository.save(user);

    }
}
*/