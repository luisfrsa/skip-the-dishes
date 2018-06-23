package com.luisskipthedishes.restaurant.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity(name = "courier")
@Table(name = "courier")
public class Courier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public Courier setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Courier setName(String name) {
        this.name = name;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Courier setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
