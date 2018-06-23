package com.luisskipthedishes.order.service;

import com.luisskipthedishes.order.exception.ApplicationServiceException;
import com.luisskipthedishes.order.model.Courier;
import com.luisskipthedishes.order.model.Order;
import com.luisskipthedishes.order.repository.CourierRepository;
import com.luisskipthedishes.order.service.dto.CourierDTO;
import com.luisskipthedishes.order.service.mapper.CourierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;


@Service
public class CourierService {

    private static final String ENTITY_NAME = "courier";

    private static final Logger log = LoggerFactory.getLogger(CourierService.class);
    private static final String REQUEST_SAVE = "Request for save " + ENTITY_NAME + " with id";
    private static final String REQUEST_UPDATE = "Request for update " + ENTITY_NAME + " with id %s";
    private static final String PERSIST_REQUESTED_WITH_NULL_OBJECT = "Persist requested with null " + ENTITY_NAME;
    private static final String CANT_DELETE_NOT_FOUND = "Could not delete the " + ENTITY_NAME + ". Reason: " + ENTITY_NAME + " not found ";
    public static final String UNDELIVERIED_ORDER_NOT_FOUND = "Sorry, we dont have undeliveried orders anymore";
    public static final String ASSIGN_COURIER = "Request for assign an undeliveried order to a courier";

    @Autowired
    private CourierMapper courierMapper;

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private OrderService orderService;

    public CourierDTO findOne(Long id) {
        Courier courier = findOneEntity(id);
        return courierMapper.toDto(courier);
    }

    @Transactional(readOnly = true)
    public Courier findOneEntity(Long id) {
        return courierRepository.getOne(id);
    }

    public List<CourierDTO> findAll() {
        return findAllEntity()
                .stream()
                .map(courierMapper::toDto)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<Courier> findAllEntity() {
        return courierRepository.findAll();

    }

    public CourierDTO persist(CourierDTO courierDTO) {
        if (Objects.isNull(courierDTO)) {
            throw new ApplicationServiceException(PERSIST_REQUESTED_WITH_NULL_OBJECT, HttpStatus.PRECONDITION_FAILED);
        }
        if (courierDTO.getId() > 0) {
            return save(courierDTO);
        }
        return update(courierDTO);
    }

    @Transactional
    public CourierDTO save(CourierDTO courierDTO) {
        String stringDebug = format(REQUEST_SAVE);
        log.info(stringDebug);
        Courier courier = courierMapper.toEntity(courierDTO);
        return courierMapper.toDto(courierRepository.save(courier));
    }

    @Transactional
    public CourierDTO update(CourierDTO courierDTO) {
        String stringDebug = format(REQUEST_UPDATE, courierDTO.getId());
        log.info(stringDebug);
        Courier courier = courierMapper.toEntity(courierDTO);
        return courierMapper.toDto(courierRepository.save(courier));
    }

    public void delete(Long id) {
        Courier courier = courierRepository.getOne(id);
        if (Objects.isNull(courier)) {
            throw new ApplicationServiceException(CANT_DELETE_NOT_FOUND, HttpStatus.PRECONDITION_FAILED);
        }
        courierRepository.deleteById(courier.getId());
    }

    public void delete(CourierDTO courierDTO) {

        courierRepository.deleteById(courierDTO.getId());
    }

    public CourierDTO assignUndeliveriedOrder(Long id) {
        String stringDebug = format(ASSIGN_COURIER);
        Optional<Order> optOrder = orderService.getUndeliveriedOrder();
        if (!optOrder.isPresent()) {
            throw new ApplicationServiceException(UNDELIVERIED_ORDER_NOT_FOUND, HttpStatus.PRECONDITION_FAILED);
        }
        Courier courier = findOneEntity(id);
        Order order = optOrder.get();

        order.setFinished(true);
        order.setCourier(courier);

        courier.setOrders(addOrder(courier, order));

        Courier newCourier = courierRepository.save(courier);

        return courierMapper.toDto(newCourier);
    }

    public List<Order> addOrder(Courier courier, Order order) {
        List<Order> orders = courier.getOrders();
        orders.add(order);
        return orders;
    }
}
