package com.luisskipthedishes.order.service.mapper;

import com.luisskipthedishes.order.model.Order;
import com.luisskipthedishes.order.model.User;
import com.luisskipthedishes.order.service.dto.OrderDTO;
import com.luisskipthedishes.order.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User> {

}
