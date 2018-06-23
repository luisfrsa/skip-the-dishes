package com.luisskipthedishes.order.service;

import com.luisskipthedishes.order.exception.ApplicationServiceException;
import com.luisskipthedishes.order.model.Restaurant;
import com.luisskipthedishes.order.repository.RestaurantRepository;
import com.luisskipthedishes.order.service.dto.RestaurantDTO;
import com.luisskipthedishes.order.service.mapper.RestaurantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class RestaurantService {

    private static final String ENTITY_NAME = "restaurant";

    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);
    private static final String REQUEST_SAVE = "Request for save " + ENTITY_NAME + " with id";
    private static final String REQUEST_UPDATE = "Request for update " + ENTITY_NAME + " with id %s";
    private static final String PERSIST_REQUESTED_WITH_NULL_OBJECT = "Persist requested with null " + ENTITY_NAME;
    private static final String CANT_DELETE_NOT_FOUND = "Could not delete the " + ENTITY_NAME + ". Reason: " + ENTITY_NAME + " not found ";

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;


    public RestaurantDTO findOne(Long id) {
        Restaurant restaurant = findOneEntity(id);
        return restaurantMapper.toDto(restaurant);
    }

    @Transactional(readOnly = true)
    public Restaurant findOneEntity(Long id) {
        return restaurantRepository.getOne(id);
    }

    public List<RestaurantDTO> findAll() {
        return findAllEntity()
                .stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<Restaurant> findAllEntity() {
        return restaurantRepository.findAll();

    }

    @Transactional
    public RestaurantDTO persist(RestaurantDTO restaurantDTO) {
        if (Objects.isNull(restaurantDTO)) {
            throw new ApplicationServiceException(PERSIST_REQUESTED_WITH_NULL_OBJECT, HttpStatus.PRECONDITION_FAILED);
        }
        if (restaurantDTO.getId() > 0) {
            return save(restaurantDTO);
        }
        return update(restaurantDTO);
    }


    public RestaurantDTO save(RestaurantDTO restaurantDTO) {
        String stringDebug = format(REQUEST_SAVE);
        log.info(stringDebug);
        Restaurant restaurant = restaurantMapper.toEntity(restaurantDTO);
        return restaurantMapper.toDto(restaurantRepository.save(restaurant));
    }

    public RestaurantDTO update(RestaurantDTO restaurantDTO) {
        String stringDebug = format(REQUEST_UPDATE, restaurantDTO.getId());
        log.info(stringDebug);
        Restaurant restaurant = restaurantMapper.toEntity(restaurantDTO);
        return restaurantMapper.toDto(restaurantRepository.save(restaurant));
    }

    @Transactional
    public void delete(Long id) {
        Restaurant restaurant = restaurantRepository.getOne(id);
        if (Objects.isNull(restaurant)) {
            throw new ApplicationServiceException(CANT_DELETE_NOT_FOUND, HttpStatus.PRECONDITION_FAILED);
        }
        restaurantRepository.deleteById(restaurant.getId());
    }

    public void delete(RestaurantDTO restaurantDTO) {
        restaurantRepository.deleteById(restaurantDTO.getId());
    }

}