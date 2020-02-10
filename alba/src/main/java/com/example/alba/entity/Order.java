package com.example.alba.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "online_shop")
public class Order {

    private int orderId;
    private String orderDescription;

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "order_description")
    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order that = (Order) o;

        if (orderId != that.orderId) return false;

        if (!Objects.equals(orderDescription, that.orderDescription))
            return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = orderId;
        result = 31 * result + (orderDescription != null ? orderDescription.hashCode() : 0);
        return result;
    }
}
