package com.luisskipthedishes.order.service.mapper;

import com.luisskipthedishes.order.model.Courier;
import com.luisskipthedishes.order.service.dto.CourierDTO;
import com.luisskipthedishes.order.service.dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CourierMapper extends EntityMapper<CourierDTO, Courier> {

}
