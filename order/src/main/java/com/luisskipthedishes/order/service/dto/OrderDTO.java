package com.luisskipthedishes.order.service.dto;

import com.luisskipthedishes.order.model.User;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDTO {

    private Long id;
    private User user;
    private BigDecimal value;
    private Date createdAt;
    private Date updatedAt;

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


    public BigDecimal getValue() {
        return value;
    }

    public OrderDTO setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public OrderDTO setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public OrderDTO setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
