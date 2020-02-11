package com.example.alba.service;

import com.example.alba.RecordNotFoundException;
import com.example.alba.entity.Order;
import com.example.alba.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders() {

        List<Order> resultOrders = (List<Order>) orderRepository.findAll();

        if (resultOrders.size() > 0) {
            return resultOrders;
        } else {
            return new ArrayList<>();
        }
    }

    public Order getOrderById(Integer id) throws RecordNotFoundException {

        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            return order.get();

        } else throw new RecordNotFoundException("No orders found");
    }

    public Order createOrUpdateOrder(Order order) {

        if (order.getOrderId() == null) {
            order = orderRepository.save(order);
            return order;

        } else {
            Optional<Order> optionalOrder = orderRepository.findById(order.getOrderId());

            if (optionalOrder.isPresent()) {
                Order newEntity = optionalOrder.get();
                newEntity.setOrderDescription(order.getOrderDescription());
                newEntity.setOrderDate(order.getOrderDate());
                newEntity = orderRepository.save(newEntity);
                return newEntity;
            } else {
                order = orderRepository.save(order);
                return order;
            }
        }
    }

    public void deleteOrder(Integer id) throws RecordNotFoundException {

        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No order found");
        }
    }
}
