package com.luisskipthedishes.order.service;

import com.luisskipthedishes.order.exception.ApplicationServiceException;
import com.luisskipthedishes.order.model.User;
import com.luisskipthedishes.order.repository.UserRepository;
import com.luisskipthedishes.order.service.dto.UserDTO;
import com.luisskipthedishes.order.service.mapper.UserMapper;
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
public class UserService {

    private static final String ENTITY_NAME = "user";

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private static final String REQUEST_SAVE = "Request for save " + ENTITY_NAME + " with id";
    private static final String REQUEST_UPDATE = "Request for update " + ENTITY_NAME + " with id %s";
    private static final String PERSIST_REQUESTED_WITH_NULL_OBJECT = "Persist requested with null " + ENTITY_NAME;
    private static final String CANT_DELETE_NOT_FOUND = "Could not delete the " + ENTITY_NAME + ". Reason: " + ENTITY_NAME + " not found ";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;


    public UserDTO findOne(Long id) {
        User user = findOneEntity(id);
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public User findOneEntity(Long id) {
        return userRepository.getOne(id);
    }

    public List<UserDTO> findAll() {
        return findAllEntity()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

    @Transactional(readOnly = true)
    public List<User> findAllEntity() {
        return userRepository.findAll();

    }

    public UserDTO persist(UserDTO userDTO) {
        if (Objects.isNull(userDTO)) {
            throw new ApplicationServiceException(PERSIST_REQUESTED_WITH_NULL_OBJECT, HttpStatus.PRECONDITION_FAILED);
        }
        if (userDTO.getId() > 0) {
            return save(userDTO);
        }
        return update(userDTO);
    }

    @Transactional
    public UserDTO save(UserDTO userDTO) {
        String stringDebug = format(REQUEST_SAVE);
        log.info(stringDebug);
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public UserDTO update(UserDTO userDTO) {
        String stringDebug = format(REQUEST_UPDATE, userDTO.getId());
        log.info(stringDebug);
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDto(userRepository.save(user));
    }

    public void delete(Long id) {
        User user = userRepository.getOne(id);
        if (Objects.isNull(user)) {
            throw new ApplicationServiceException(CANT_DELETE_NOT_FOUND, HttpStatus.PRECONDITION_FAILED);
        }
        userRepository.deleteById(user.getId());
    }

    public void delete(UserDTO userDTO) {

        userRepository.deleteById(userDTO.getId());
    }

}