package com.luisskipthedishes.order.service.dto;


import com.luisskipthedishes.order.model.Order;

import java.util.List;

public class RestaurantDTO {

    private Long id;
    private String name;
    private String address;
    private List<Order> orders;
    public Long getId() {
        return id;
    }

    public RestaurantDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RestaurantDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RestaurantDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public RestaurantDTO setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

}
