package com.luisskipthedishes.order.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "userorder")
@Table(name = "userorder")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Courier courier;

    private boolean finished;

    @NotNull
    @Column(name = "value", precision = 10, scale = 2, nullable = false)
    private BigDecimal value;


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

    public BigDecimal getValue() {
        return value;
    }

    public Order setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public Courier getCourier() {
        return courier;
    }

    public Order setCourier(Courier courier) {
        this.courier = courier;
        return this;
    }

    public boolean isFinished() {
        return finished;
    }

    public Order setFinished(boolean finished) {
        this.finished = finished;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", restaurant=" + restaurant.getId() +
                ", courier=" + courier.getId() +
                ", finished=" + finished +
                ", value=" + value +
                '}';
    }
}
