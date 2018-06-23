package com.luisskipthedishes.order.resource;


import com.luisskipthedishes.order.exception.ApplicationResourceException;
import com.luisskipthedishes.order.resource.util.ResponseUtil;
import com.luisskipthedishes.order.service.CourierService;
import com.luisskipthedishes.order.service.dto.CourierDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RestController
@RequestMapping("/api")
public class CourierResource {
    private static final String ENTITY_NAME = "courier";

    public static final String REST_REQUEST_TO_GET_ALL = "REST request to get all " + ENTITY_NAME;
    public static final String REST_REQUEST_TO_GET_ONE = "REST request to get one " + ENTITY_NAME + " by id %d";
    public static final String REST_REQUEST_FOR_SAVE = "REST request for save " + ENTITY_NAME + " %s";
    public static final String REST_REQUEST_FOR_UPDATE = "REST request for save " + ENTITY_NAME + " %s";
    public static final String CANNOT_CREATE_WITH_ID = "A new " + ENTITY_NAME + " cannot already have an ID %d";
    public static final String NEW_COURIER = "Create new courier request from courier %d, restaurant %d with value %s";
    public static final String FIND_NEW_ORDER_FOR_COURIER = "Find new order for Courier";
    private final Logger log = LoggerFactory.getLogger(CourierResource.class);


    @Autowired
    private CourierService courierService;


    @GetMapping("/courier/{id}")
    public ResponseEntity<CourierDTO> findOne(@PathVariable Long id) {
        log.info(format(REST_REQUEST_TO_GET_ONE, id));
        CourierDTO CourierDTO = courierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(CourierDTO));
    }


    @GetMapping("/courier")
    public ResponseEntity<List<CourierDTO>> findAll() {
        log.info(REST_REQUEST_TO_GET_ALL);
        List<CourierDTO> listCourierDTO = courierService.findAll();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(listCourierDTO));
    }


    @PostMapping("/courier")
    public ResponseEntity<CourierDTO> createCourier(@Valid @RequestBody CourierDTO CourierDTO) throws URISyntaxException {
        String strLog = format(REST_REQUEST_FOR_SAVE, CourierDTO.getId());
        log.info(strLog);
        if (CourierDTO.getId() != null) {
            throw new ApplicationResourceException(format(CANNOT_CREATE_WITH_ID, CourierDTO.getId()), HttpStatus.BAD_REQUEST);
        }
        CourierDTO result = courierService.save(CourierDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/courier")
    public ResponseEntity<CourierDTO> updateCourier(@Valid @RequestBody CourierDTO CourierDTO) {
        String strLog = format(REST_REQUEST_FOR_UPDATE, CourierDTO.getId());
        log.info(strLog);
        CourierDTO result = courierService.save(CourierDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/courier/assignOrder/{id}")
    public ResponseEntity<CourierDTO> assignOrder(@PathVariable Long id) {
        log.info(format(FIND_NEW_ORDER_FOR_COURIER));
        CourierDTO courierDTO = courierService.assignUndeliveriedOrder(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courierDTO));
    }


}
