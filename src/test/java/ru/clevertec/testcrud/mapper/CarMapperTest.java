package ru.clevertec.testcrud.mapper;

import org.junit.jupiter.api.Test;
import ru.clevertec.testcrud.domain.Car;
import ru.clevertec.testcrud.entity.CarEntity;
import ru.clevertec.testcrud.util.TestData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CarMapperTest {
    private final CarMapper carMapper = new CarMapperImpl();

    @Test
    void shouldProperlyCarToCarEntity() {
        //given
        Car car = TestData.generateRandomCar();

        //when
        CarEntity carEntity = carMapper.toEntity(car);

        //then
        assertNotNull(carEntity);
        assertEquals(car.getId(), carEntity.getId());
        assertEquals(car.getMake(), carEntity.getMake());
        assertEquals(car.getModel(), carEntity.getModel());
        assertEquals(car.getReleaseDate(), carEntity.getReleaseDate());
    }

    @Test
    void shouldReturnNull_whenCarIsNull() {
        //when
        CarEntity carEntity = carMapper.toEntity(null);

        //then
        assertNull(carEntity);
    }

    @Test
    void shouldProperlyCarEntityToCar() {
        //given
        CarEntity carEntity = TestData.generateRandomCarEntity();

        //when
        Car car = carMapper.toDomain(carEntity);

        //then
        assertNotNull(car);
        assertEquals(carEntity.getId(), car.getId());
        assertEquals(carEntity.getMake(), car.getMake());
        assertEquals(carEntity.getModel(), car.getModel());
        assertEquals(carEntity.getReleaseDate(), car.getReleaseDate());
    }

    @Test
    void shouldReturnNull_whenCarEntityIsNull() {
        //when
        Car car = carMapper.toDomain(null);

        //then
        assertNull(car);
    }

    @Test
    void shouldProperlyCarsToCarEntities() {
        //given
        List<Car> cars = TestData.generateRandomCars();

        //when
        List<CarEntity> carEntities = carMapper.toEntities(cars);

        //then
        assertNotNull(carEntities);
        assertEquals(cars.size(), carEntities.size());
    }

    @Test
    void shouldReturnNull_whenCarsIsNull() {
        //when
        List<CarEntity> carEntities = carMapper.toEntities(null);

        //then
        assertNull(carEntities);
    }

    @Test
    void shouldProperlyCarEntitiesToCars() {
        //given
        List<CarEntity> carEntities = TestData.generateRandomCarEntities();

        //when
        List<Car> cars = carMapper.toDomains(carEntities);

        //then
        assertNotNull(cars);
        assertEquals(carEntities.size(), cars.size());
    }

    @Test
    void shouldReturnNull_whenCarEntitiesIsNull() {
        //when
        List<Car> cars = carMapper.toDomains(null);

        //then
        assertNull(cars);
    }
}