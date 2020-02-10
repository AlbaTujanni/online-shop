//package com.example.alba;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "product", schema = "online_shop", catalog = "")
//public class ProductEntity {
//    private int productId;
//    private String name;
//    private Double price;
//
//    @Id
//    @Column(name = "product_id")
//    public int getProductId() {
//        return productId;
//    }
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }
//
//    @Basic
//    @Column(name = "name")
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Basic
//    @Column(name = "price")
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        ProductEntity that = (ProductEntity) o;
//
//        if (productId != that.productId) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//        if (price != null ? !price.equals(that.price) : that.price != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = productId;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (price != null ? price.hashCode() : 0);
//        return result;
//    }
//}
