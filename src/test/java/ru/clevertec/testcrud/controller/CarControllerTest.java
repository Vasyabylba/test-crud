package ru.clevertec.testcrud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.clevertec.testcrud.domain.Car;
import ru.clevertec.testcrud.service.CarService;
import ru.clevertec.testcrud.util.TestData;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the {@link CarController}
 */
@WebMvcTest({CarController.class})
class CarControllerTest {
    @MockBean
    private CarService carService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetList() throws Exception {
        //given
        List<Car> cars = TestData.generateCars();

        when(carService.getList())
                .thenReturn(cars);

        //when, then
        mockMvc.perform(get("/api/v1/cars"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cars)))
                .andDo(print());
    }

    @Test
    void shouldGetOne() throws Exception {
        //given
        Car car = TestData.generateCar();

        when(carService.getOne(car.getId()))
                .thenReturn(car);

        //when, then
        mockMvc.perform(get("/api/v1/cars/{id}", car.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(car)))
                .andDo(print());
    }

    @Test
    void shouldCreate() throws Exception {
        //given
        Car car = TestData.generateCar();
        String carJSON = objectMapper.writeValueAsString(car);

        when(carService.create(car))
                .thenReturn(car);

        //when, then
        mockMvc.perform(post("/api/v1/cars")
                        .content(carJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().json(carJSON))
                .andDo(print());
    }

    @Test
    void shouldUpdate() throws Exception {
        //given
        Car car = TestData.generateCar();
        String carJSON = objectMapper.writeValueAsString(car);

        when(carService.update(car.getId(), car))
                .thenReturn(car);

        //when, then
        mockMvc.perform(put("/api/v1/cars/{id}", car.getId())
                        .content(carJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().json(carJSON))
                .andDo(print());
    }

    @Test
    void shouldDelete() throws Exception {
        //given
        Long carId = 1L;

        //when, then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/cars/{id}", carId))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
                .andDo(print());
    }
}
