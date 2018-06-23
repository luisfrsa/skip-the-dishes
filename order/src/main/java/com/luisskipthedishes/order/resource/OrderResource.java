package com.luisskipthedishes.order.resource;


import com.luisskipthedishes.order.exception.ApplicationResourceException;
import com.luisskipthedishes.order.resource.util.ResponseUtil;
import com.luisskipthedishes.order.service.OrderService;
import com.luisskipthedishes.order.service.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RestController
@RequestMapping("/api")
public class OrderResource {
    private static final String ENTITY_NAME = "order";

    public static final String REST_REQUEST_TO_GET_ALL = "REST request to get all " + ENTITY_NAME;
    public static final String REST_REQUEST_TO_GET_ONE = "REST request to get one " + ENTITY_NAME + " by id %d";
    public static final String REST_REQUEST_FOR_SAVE = "REST request for save " + ENTITY_NAME + " %s";
    public static final String REST_REQUEST_FOR_UPDATE = "REST request for save " + ENTITY_NAME + " %s";
    public static final String CANNOT_CREATE_WITH_ID = "A new " + ENTITY_NAME + " cannot already have an ID %d";
    public static final String NEW_ORDER = "Create new order request from user %d, restaurant %d with value %s";
    private final Logger log = LoggerFactory.getLogger(OrderResource.class);


    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> findOne(@PathVariable Long id) {
        log.info(format(REST_REQUEST_TO_GET_ONE, id));
        OrderDTO OrderDTO = orderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(OrderDTO));
    }


    @GetMapping("/order")
    public ResponseEntity<List<OrderDTO>> findAll() {
        log.info(REST_REQUEST_TO_GET_ALL);
        List<OrderDTO> listOrderDTO = orderService.findAll();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(listOrderDTO));
    }


    @PostMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO OrderDTO) throws URISyntaxException {
        String strLog = format(REST_REQUEST_FOR_SAVE, OrderDTO.getId());
        log.info(strLog);
        if (OrderDTO.getId() != null) {
            throw new ApplicationResourceException(format(CANNOT_CREATE_WITH_ID, OrderDTO.getId()), HttpStatus.BAD_REQUEST);
        }
        OrderDTO result = orderService.save(OrderDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/order")
    public ResponseEntity<OrderDTO> updateOrder(@Valid @RequestBody OrderDTO OrderDTO) {
        String strLog = format(REST_REQUEST_FOR_UPDATE, OrderDTO.getId());
        log.info(strLog);
        OrderDTO result = orderService.save(OrderDTO);
        return ResponseEntity.ok().body(result);
    }


    @PostMapping("/order/{idUser}/{idRestaurant}/{value}")
    public ResponseEntity<OrderDTO> createNewOrder(@PathVariable Long idUser, @PathVariable Long idRestaurant, @PathVariable BigDecimal value) {
        String strLog = format(NEW_ORDER, idUser, idRestaurant, value);
        log.info(strLog);
        OrderDTO result = orderService.createNewOrder(idUser, idRestaurant, value);
        return ResponseEntity.ok().body(result);
    }

}
