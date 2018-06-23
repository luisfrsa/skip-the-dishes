package com.luisskipthedishes.restaurant.resource;


import com.luisskipthedishes.restaurant.exception.ApplicationResourceException;
import com.luisskipthedishes.restaurant.resource.util.HeaderUtil;
import com.luisskipthedishes.restaurant.resource.util.ResponseUtil;
import com.luisskipthedishes.restaurant.service.RestaurantService;
import com.luisskipthedishes.restaurant.service.dto.RestaurantDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RestController
@RequestMapping("/api")
public class RestaurantResource {
    private static final String ENTITY_NAME = "restaurant";

    public static final String REST_REQUEST_TO_GET_ALL = "REST request to get all " + ENTITY_NAME;
    public static final String REST_REQUEST_TO_GET_ONE = "REST request to get one " + ENTITY_NAME + " by id %d";
    public static final String REST_REQUEST_FOR_SAVE = "REST request for save " + ENTITY_NAME + " %s";
    public static final String REST_REQUEST_FOR_UPDATE = "REST request for save " + ENTITY_NAME + " %s";
    public static final String CANNOT_CREATE_WITH_ID = "A new " + ENTITY_NAME + " cannot already have an ID %d";
    private final Logger log = LoggerFactory.getLogger(RestaurantResource.class);


    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantDTO> findOne(@PathVariable Long id) {
        log.info(format(REST_REQUEST_TO_GET_ONE, id));
        RestaurantDTO RestaurantDTO = restaurantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(RestaurantDTO));
    }


    @GetMapping("/restaurant")
    public ResponseEntity<List<RestaurantDTO>> findAll() {
        log.info(REST_REQUEST_TO_GET_ALL);
        List<RestaurantDTO> listRestaurantDTO = restaurantService.findAll();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(listRestaurantDTO));
    }


    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO RestaurantDTO) throws URISyntaxException {
        String strLog = format(REST_REQUEST_FOR_SAVE, RestaurantDTO.getId());
        log.info(strLog);
        if (RestaurantDTO.getId() != null) {
            throw new ApplicationResourceException(format(CANNOT_CREATE_WITH_ID, RestaurantDTO.getId()), HttpStatus.BAD_REQUEST);
        }
        RestaurantDTO result = restaurantService.save(RestaurantDTO);
        return ResponseEntity
                .created(new URI("/api/restaurant/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/restaurant")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@Valid @RequestBody RestaurantDTO RestaurantDTO) {
        String strLog = format(REST_REQUEST_FOR_UPDATE, RestaurantDTO.getId());
        log.info(strLog);
        RestaurantDTO result = restaurantService.save(RestaurantDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
}
