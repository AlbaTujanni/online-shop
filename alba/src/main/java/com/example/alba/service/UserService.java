package com.example.alba.service;

import com.example.alba.RecordNotFoundException;
import com.example.alba.entity.User;
import com.example.alba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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
}
