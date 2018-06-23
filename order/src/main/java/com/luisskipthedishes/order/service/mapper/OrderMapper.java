package com.luisskipthedishes.order.service.mapper;

import com.luisskipthedishes.order.model.Order;
import com.luisskipthedishes.order.service.dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {

}
