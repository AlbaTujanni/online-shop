package com.example.alba.service;

import com.example.alba.RecordNotFoundException;
import com.example.alba.entity.Role;
import com.example.alba.entity.User;
import com.example.alba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {

        List<User> resultUsers = (List<User>) userRepository.findAll();

        if (resultUsers.size() > 0) {
            return resultUsers;
        } else {
            return new ArrayList<>();
        }
    }

    public User getUserById(Integer id) throws RecordNotFoundException {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else throw new RecordNotFoundException("No users found");
    }

    public UserDetails loadUserByName(String name) throws UsernameNotFoundException {

        User user = userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Name " + name + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getName(),
                getAuthorities(user));
    }

    public User createOrUpdateUser(User user) {

        if (user.getUserId() == null) {
            user = userRepository.save(user);
            return user;

        } else {
            Optional<User> optionalUser = userRepository.findById(user.getUserId());

            if (optionalUser.isPresent()) {
                User newEntity = optionalUser.get();
                newEntity.setName(user.getName());
                newEntity.setEmail(user.getEmail());

                newEntity = userRepository.save(newEntity);
                return newEntity;
            } else {
                user = userRepository.save(user);
                return user;
            }
        }
    }

    public void deleteUser(Integer id) throws RecordNotFoundException {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }


    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {

        String[] userRoles = user.getRoles().stream().map(Role::getName).toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }
}
