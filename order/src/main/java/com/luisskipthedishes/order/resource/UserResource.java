package com.luisskipthedishes.order.resource;


import com.luisskipthedishes.order.exception.ApplicationResourceException;
import com.luisskipthedishes.order.resource.util.ResponseUtil;
import com.luisskipthedishes.order.service.UserService;
import com.luisskipthedishes.order.service.dto.UserDTO;
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
public class UserResource {
    private static final String ENTITY_NAME = "user";

    public static final String REST_REQUEST_TO_GET_ALL = "REST request to get all " + ENTITY_NAME;
    public static final String REST_REQUEST_TO_GET_ONE = "REST request to get one " + ENTITY_NAME + " by id %d";
    public static final String REST_REQUEST_FOR_SAVE = "REST request for save " + ENTITY_NAME + " %s";
    public static final String REST_REQUEST_FOR_UPDATE = "REST request for save " + ENTITY_NAME + " %s";
    public static final String CANNOT_CREATE_WITH_ID = "A new " + ENTITY_NAME + " cannot already have an ID %d";
    public static final String NEW_USER = "Create new user request from user %d, restaurant %d with value %s";
    private final Logger log = LoggerFactory.getLogger(UserResource.class);


    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> findOne(@PathVariable Long id) {
        log.info(format(REST_REQUEST_TO_GET_ONE, id));
        UserDTO UserDTO = userService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(UserDTO));
    }


    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> findAll() {
        log.info(REST_REQUEST_TO_GET_ALL);
        List<UserDTO> listUserDTO = userService.findAll();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(listUserDTO));
    }


    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO UserDTO) throws URISyntaxException {
        String strLog = format(REST_REQUEST_FOR_SAVE, UserDTO.getId());
        log.info(strLog);
        if (UserDTO.getId() != null) {
            throw new ApplicationResourceException(format(CANNOT_CREATE_WITH_ID, UserDTO.getId()), HttpStatus.BAD_REQUEST);
        }
        UserDTO result = userService.save(UserDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/user")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO UserDTO) {
        String strLog = format(REST_REQUEST_FOR_UPDATE, UserDTO.getId());
        log.info(strLog);
        UserDTO result = userService.save(UserDTO);
        return ResponseEntity.ok().body(result);
    }


}
