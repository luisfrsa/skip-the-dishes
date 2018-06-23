package com.luisskipthedishes.restaurant.model;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "userorder")
@Table(name = "userorder")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private User user;

    @NotBlank
    private Restaurant restaurant;

    @NotNull
    @Column(name = "value", precision = 10, scale = 2, nullable = false)
    private BigDecimal value;


    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;


    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Order() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Order setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Order setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Order setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Order setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
