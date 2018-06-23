package com.luisskipthedishes.order.service;

import com.luisskipthedishes.order.exception.ApplicationServiceException;
import com.luisskipthedishes.order.model.Order;
import com.luisskipthedishes.order.repository.OrderRepository;
import com.luisskipthedishes.order.service.dto.OrderDTO;
import com.luisskipthedishes.order.service.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.format;


@Service
public class OrderService {

    private static final String ENTITY_NAME = "order";

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private static final String REQUEST_SAVE = "Request for save " + ENTITY_NAME + " with id";
    private static final String REQUEST_UPDATE = "Request for update " + ENTITY_NAME + " with id %s";
    private static final String PERSIST_REQUESTED_WITH_NULL_OBJECT = "Persist requested with null " + ENTITY_NAME;
    private static final String CANT_DELETE_NOT_FOUND = "Could not delete the " + ENTITY_NAME + ". Reason: " + ENTITY_NAME + " not found ";

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    public OrderDTO findOne(Long id) {
        Order order = findOneEntity(id);
        return orderMapper.toDto(order);
    }

    @Transactional(readOnly = true)
    public Order findOneEntity(Long id) {
        return orderRepository.getOne(id);
    }

    public List<OrderDTO> findAll() {
        return findAllEntity()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

    @Transactional(readOnly = true)
    public List<Order> findAllEntity() {
        return orderRepository.findAll();

    }

    public OrderDTO persist(OrderDTO orderDTO) {
        if (Objects.isNull(orderDTO)) {
            throw new ApplicationServiceException(PERSIST_REQUESTED_WITH_NULL_OBJECT, HttpStatus.PRECONDITION_FAILED);
        }
        if (orderDTO.getId() > 0) {
            return save(orderDTO);
        }
        return update(orderDTO);
    }

    @Transactional
    public OrderDTO save(OrderDTO orderDTO) {
        String stringDebug = format(REQUEST_SAVE);
        log.info(stringDebug);
        Order order = orderMapper.toEntity(orderDTO);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Transactional
    public OrderDTO update(OrderDTO orderDTO) {
        String stringDebug = format(REQUEST_UPDATE, orderDTO.getId());
        log.info(stringDebug);
        Order order = orderMapper.toEntity(orderDTO);
        return orderMapper.toDto(orderRepository.save(order));
    }

    public void delete(Long id) {
        Order order = orderRepository.getOne(id);
        if (Objects.isNull(order)) {
            throw new ApplicationServiceException(CANT_DELETE_NOT_FOUND, HttpStatus.PRECONDITION_FAILED);
        }
        orderRepository.deleteById(order.getId());
    }

    public void delete(OrderDTO orderDTO) {

        orderRepository.deleteById(orderDTO.getId());
    }

//    public OrderDTO createNewOrder(Long idUser, Long idRestaurant, BigDecimal value) {
//
//        Restaurant restaurant = restaurantServiceProxy.getRestaurant(idRestaurant);
//        User user = userService.findOneEntity(idUser);
//
//        System.out.println("Restaurant " + restaurant.getName());
//
//        Order order = new Order()
//                .setRestaurant(restaurant)
//                .setUser(user)
//                .setValue(value);
//        return orderMapper.toDto(orderRepository.save(order));
//    }

}