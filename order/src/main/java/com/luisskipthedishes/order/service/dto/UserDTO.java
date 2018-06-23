package com.luisskipthedishes.order.service.dto;

import com.luisskipthedishes.order.model.Order;

import java.util.LinkedList;

public class UserDTO {

    private Long id;
    private String name;
    private String address;
    private LinkedList<Order> orders;

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public LinkedList<Order> getOrders() {
        return orders;
    }

    public UserDTO setOrders(LinkedList<Order> orders) {
        this.orders = orders;
        return this;
    }
}
