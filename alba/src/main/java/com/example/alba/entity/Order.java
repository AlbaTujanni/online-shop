package com.example.alba.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "online_shop")
public class Order {

    private int orderId;
    private String orderDescription;
    private Date orderDate;

    @Id
    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
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

    @Basic
    @Column(name = "order_date")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "product_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

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

    public Order() {
    }

    public Order(String orderDescription, Date orderDate){
        this.orderDate=orderDate;
        this.orderDescription=orderDescription;
    }

    @Override
    public int hashCode() {

        int result = orderId;
        result = 31 * result + (orderDescription != null ? orderDescription.hashCode() : 0);
        return result;
    }
}
