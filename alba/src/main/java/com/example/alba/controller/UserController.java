package com.example.alba.controller;

import com.example.alba.RecordNotFoundException;
import com.example.alba.entity.User;
import com.example.alba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping
    public String getAllUsers(Model model) {

        List<User> list = userService.getAllUsers();
        model.addAttribute("users", list);
        return "users";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editUserById(Model model, @PathVariable("id") Optional<Integer> id)
            throws RecordNotFoundException {

        if (id.isPresent()) {
            User entity = userService.getUserById(id.get());
            model.addAttribute("user", entity);

        } else {
            model.addAttribute("user", new User());
        }
        return "add-edit-user";
    }

    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateUser(User user) {

        userService.createOrUpdateUser(user);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(Model model, @PathVariable("id") Integer id) throws RecordNotFoundException {

        userService.deleteUser(id);
        return "redirect:/";
    }


}

