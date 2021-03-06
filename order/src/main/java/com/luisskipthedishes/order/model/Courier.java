package com.luisskipthedishes.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity(name = "courier")
@Table(name = "courier")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Courier implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "courier", targetEntity = Order.class, cascade = CascadeType.ALL)
    @JsonBackReference
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

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
