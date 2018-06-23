package com.luisskipthedishes.restaurant.service.dto;

import com.luisskipthedishes.restaurant.model.Order;

import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String address;
    private List<Order> orders;

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

    public List<Order> getOrders() {
        return orders;
    }

    public UserDTO setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
