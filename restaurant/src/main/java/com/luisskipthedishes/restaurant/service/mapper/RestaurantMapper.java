package com.luisskipthedishes.restaurant.service.mapper;

import com.luisskipthedishes.restaurant.model.Restaurant;
import com.luisskipthedishes.restaurant.service.dto.RestaurantDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface RestaurantMapper extends EntityMapper<RestaurantDTO, Restaurant> {

}
