//package com.example.alba;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "order", schema = "online_shop", catalog = "")
//public class OrderEntity {
//    private int orderId;
//    private String orderDescription;
//
//    @Id
//    @Column(name = "order_id")
//    public int getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }
//
//    @Basic
//    @Column(name = "order_description")
//    public String getOrderDescription() {
//        return orderDescription;
//    }
//
//    public void setOrderDescription(String orderDescription) {
//        this.orderDescription = orderDescription;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        OrderEntity that = (OrderEntity) o;
//
//        if (orderId != that.orderId) return false;
//        if (orderDescription != null ? !orderDescription.equals(that.orderDescription) : that.orderDescription != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = orderId;
//        result = 31 * result + (orderDescription != null ? orderDescription.hashCode() : 0);
//        return result;
//    }
//}
