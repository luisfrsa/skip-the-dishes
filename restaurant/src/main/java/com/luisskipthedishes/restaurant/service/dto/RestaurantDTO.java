package com.luisskipthedishes.restaurant.service.dto;



import com.luisskipthedishes.restaurant.model.Restaurant;
import com.luisskipthedishes.restaurant.model.User;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

public class RestaurantDTO {

    private Long id;
    private String name;
    private String address;

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
}
