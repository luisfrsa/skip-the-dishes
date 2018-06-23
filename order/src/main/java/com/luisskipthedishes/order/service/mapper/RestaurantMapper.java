package com.luisskipthedishes.order.service.mapper;

import com.luisskipthedishes.order.model.Restaurant;
import com.luisskipthedishes.order.service.dto.RestaurantDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface RestaurantMapper extends EntityMapper<RestaurantDTO, Restaurant> {

}
