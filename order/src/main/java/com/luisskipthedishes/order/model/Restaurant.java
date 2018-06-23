package com.luisskipthedishes.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity(name = "restaurant")
@Table(name = "restaurant")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String address;

    @OneToMany(mappedBy = "restaurant", targetEntity = Order.class, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orders;

    public Restaurant() {
    }

    public Long getId() {
        return id;
    }

    public Restaurant setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Restaurant setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Restaurant setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Restaurant setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}
