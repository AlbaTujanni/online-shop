package com.example.alba.controller;

import com.example.alba.RecordNotFoundException;
import com.example.alba.entity.Order;
import com.example.alba.service.OrderService;
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
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping
    public String getAllOrders(Model model) {
        List<Order> allOrders = orderService.getAllOrders();
        model.addAttribute("orders", allOrders);
        return "orders";
    }

    @RequestMapping(path = {"/edit", "/edit{id}"})
    public String editOrderById(Model model, @PathVariable("id") Optional<Integer> id)
            throws RecordNotFoundException {

        if (id.isPresent()) {
            Order order = orderService.getOrderById(id.get());
            model.addAttribute("order", order);

        } else {
            model.addAttribute("order", new Order());
        }
        return "add-edit-order";
    }

    @RequestMapping(path = "/createOrder", method = RequestMethod.POST)
    public String createOrUpdateOrder(Order order) {

        orderService.createOrUpdateOrder(order);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteOrder(Model model, @PathVariable("id") Integer id)
            throws RecordNotFoundException {

        orderService.deleteOrder(id);
        return "redirect:/";
    }

}
