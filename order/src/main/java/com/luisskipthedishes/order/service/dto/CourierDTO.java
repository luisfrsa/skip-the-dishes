package com.luisskipthedishes.order.service.dto;

import com.luisskipthedishes.order.model.Order;

import java.util.List;

public class CourierDTO {

    private Long id;
    private String name;
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public CourierDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourierDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public CourierDTO setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
