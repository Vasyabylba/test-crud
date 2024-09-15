package ru.clevertec.testcrud.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.testcrud.domain.Car;
import ru.clevertec.testcrud.entity.CarEntity;
import ru.clevertec.testcrud.exception.CarAlreadyExistsException;
import ru.clevertec.testcrud.exception.CarNotFoundException;
import ru.clevertec.testcrud.mapper.CarMapper;
import ru.clevertec.testcrud.repository.CarRepository;
import ru.clevertec.testcrud.util.TestData;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarService carService;

    @Test
    void shouldCreateCar() {
        //given
        Car car = TestData.generateCar();
        CarEntity carEntity = TestData.generateCarEntity();

        when(carRepository.existsById(car.getId()))
                .thenReturn(false);
        when(carMapper.toEntity(car))
                .thenReturn(carEntity);
        when(carRepository.create(carEntity))
                .thenReturn(carEntity);
        when(carMapper.toDomain(carEntity))
                .thenReturn(car);

        //when
        Car actualCar = carService.create(car);

        //then
        assertEquals(car.getId(), actualCar.getId());
    }

    @Test
    void shouldThrowCarAlreadyExistsException_whenCarAlreadyExists() {
        long carId = -1L;
        Car car = TestData.generateCar();
        car.setId(carId);
        CarEntity carEntity = TestData.generateCarEntity();
        carEntity.setId(carId);

        when(carRepository.existsById(carId))
                .thenReturn(true);

        //when, then
        assertThrows(CarAlreadyExistsException.class, () ->
                carService.create(car));

        verifyNoInteractions(carMapper);
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    void shouldGetCarById() {
        //given
        CarEntity carEntity = TestData.generateCarEntity();
        Car car = TestData.generateCar();

        when(carRepository.findById(car.getId()))
                .thenReturn(Optional.of(carEntity));
        when(carMapper.toDomain(carEntity))
                .thenReturn(car);

        //when
        Car actualCar = carService.getOne(car.getId());

        //then
        assertEquals(car.getId(), actualCar.getId());
    }

    @Test
    void shouldThrowCarNotFoundException_whenCarNotFound() {
        long carId = -1L;
        Car car = TestData.generateCar();
        car.setId(carId);
        CarEntity carEntity = TestData.generateCarEntity();
        carEntity.setId(carId);

        when(carRepository.findById(car.getId()))
                .thenReturn(Optional.empty());

        //when, then
        assertThrows(CarNotFoundException.class, () ->
                carService.getOne(carId));

        verifyNoInteractions(carMapper);
    }

    @Test
    void shouldGetAllCars() {
        //given
        List<CarEntity> carEntities = TestData.generateCarEntities();
        List<Car> cars = TestData.generateCars();

        when(carRepository.findAll())
                .thenReturn(carEntities);
        when(carMapper.toDomains(carEntities))
                .thenReturn(cars);

        //when
        List<Car> actualCars = carService.getList();

        //then
        assertIterableEquals(cars, actualCars);
    }

    @Test
    void shouldUpdateCar() {
        //given
        Car car = TestData.generateCar();
        long carId = car.getId();
        CarEntity carEntity = TestData.generateCarEntity();

        when(carRepository.existsById(car.getId()))
                .thenReturn(true);
        when(carMapper.toEntity(car))
                .thenReturn(carEntity);
        when(carRepository.update(carId, carEntity))
                .thenReturn(carEntity);
        when(carMapper.toDomain(carEntity))
                .thenReturn(car);

        //when
        Car actualCar = carService.update(carId, car);

        //then
        assertEquals(car.getId(), actualCar.getId());
    }

    @Test
    void shouldThrowCarNotFoundException_whenUpdateAndCarNotFoundException() {
        long carId = -1L;
        Car car = TestData.generateCar();
        car.setId(carId);
        CarEntity carEntity = TestData.generateCarEntity();
        carEntity.setId(carId);

        when(carRepository.existsById(carId))
                .thenReturn(false);

        //when, then
        assertThrows(CarNotFoundException.class, () ->
                carService.update(carId, car));

        verifyNoInteractions(carMapper);
        verifyNoMoreInteractions(carRepository);
    }

    @Test
    void shouldDeleteCarById() {
        //given
        long carId = 1L;

        doNothing()
                .when(carRepository).deleteById(carId);

        //when
        carService.delete(carId);

        //then
        verifyNoMoreInteractions(carRepository);
    }
}