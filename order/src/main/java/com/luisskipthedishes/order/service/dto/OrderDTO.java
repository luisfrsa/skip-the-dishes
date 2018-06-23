package com.luisskipthedishes.order.service.dto;

import com.luisskipthedishes.order.model.Courier;
import com.luisskipthedishes.order.model.Restaurant;
import com.luisskipthedishes.order.model.User;

import java.math.BigDecimal;

public class OrderDTO {

    private Long id;

    private BigDecimal value;
    private Restaurant restaurant;
    private User user;
    private Courier courier;

    private boolean finished;

    public Long getId() {
        return id;
    }

    public OrderDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public OrderDTO setUser(User user) {
        this.user = user;
        return this;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public OrderDTO setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public BigDecimal getValue() {
        return value;
    }

    public OrderDTO setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public Courier getCourier() {
        return courier;
    }

    public OrderDTO setCourier(Courier courier) {
        this.courier = courier;
        return this;
    }

    public boolean isFinished() {
        return finished;
    }

    public OrderDTO setFinished(boolean finished) {
        this.finished = finished;
        return this;
    }
}
