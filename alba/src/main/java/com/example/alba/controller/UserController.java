package com.example.alba.controller;

import com.example.alba.RecordNotFoundException;
import com.example.alba.entity.User;
import com.example.alba.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/userAdministration")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @RequestMapping
    public String getAllUsers(Model model) {

        List<User> list = userServiceImpl.getAllUsers();
        model.addAttribute("users", list);
        return "userAdministration";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editUserById(Model model, @PathVariable("id") Optional<Integer> id)
            throws RecordNotFoundException {

        if (id.isPresent()) {
            User entity = userServiceImpl.getUserById(id.get());
            model.addAttribute("user", entity);

        } else {
            model.addAttribute("user", new User());
        }
        return "add-edit-user";
    }

    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateUser(User user) {

        userServiceImpl.createOrUpdateUser(user);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(Model model, @PathVariable("id") Integer id) throws RecordNotFoundException {

        userServiceImpl.deleteUser(id);
        return "redirect:/";
    }


}

