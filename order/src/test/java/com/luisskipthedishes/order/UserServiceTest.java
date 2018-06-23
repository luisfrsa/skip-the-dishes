package com.luisskipthedishes.order;

import com.luisskipthedishes.order.model.User;
import com.luisskipthedishes.order.repository.UserRepository;
import com.luisskipthedishes.order.service.UserService;
import com.luisskipthedishes.order.service.dto.UserDTO;
import com.luisskipthedishes.order.service.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class UserServiceTest {


    @InjectMocks
    private UserService service;

    @Mock
    private UserMapper mapper;

    @Mock
    private UserRepository repository;

    private UserDTO dto;
    private User entity;

    @Before
    public void setUp() {
        dto = createDTO(1L);
        entity = createEntity(1L);
        when(mapper.toDto(entity)).thenReturn(dto);
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
    }

    @Test
    public void save() {
        UserDTO savedDTO = service.persist(dto);
        assertEquals(dto.getName(), savedDTO.getName());
    }

//No time enough for tests....


    private User createEntityWithoutId() {
        User entity = new User();
        entity.setName("myName");
        return entity;
    }

    private User createEntity(Long id) {
        User entity = createEntityWithoutId();
        entity.setId(id);
        return entity;
    }

    private UserDTO createDTOWithoutId() {
        UserDTO dto = new UserDTO();
        dto.setName("myName");
        return dto;
    }

    private UserDTO createDTO(Long id) {
        UserDTO entity = createDTOWithoutId();
        entity.setId(id);
        return entity;
    }


    private List<UserDTO> createDTOList(int n) {
        return IntStream.range(0, n).mapToObj(i -> createDTO(Long.valueOf(i))).collect(Collectors.toList());
    }

    private List<User> createEntityList(int n) {
        return IntStream.range(0, n).mapToObj(i -> createEntity(Long.valueOf(i))).collect(Collectors.toList());
    }


}