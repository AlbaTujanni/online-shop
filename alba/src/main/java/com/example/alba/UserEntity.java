//package com.example.alba;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "user", schema = "online_shop", catalog = "")
//public class UserEntity {
//    private int userId;
//    private String name;
//    private String email;
//
//    @Id
//    @Column(name = "user_id")
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
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
//    @Column(name = "email")
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        UserEntity that = (UserEntity) o;
//
//        if (userId != that.userId) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//        if (email != null ? !email.equals(that.email) : that.email != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = userId;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (email != null ? email.hashCode() : 0);
//        return result;
//    }
//}
