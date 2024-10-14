package ru.clevertec.testcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.testcrud.domain.Car;
import ru.clevertec.testcrud.entity.CarEntity;
import ru.clevertec.testcrud.exception.CarAlreadyExistsException;
import ru.clevertec.testcrud.exception.CarNotFoundException;
import ru.clevertec.testcrud.mapper.CarMapper;
import ru.clevertec.testcrud.repository.CarRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public Car create(Car car) {
        Long carId = car.getId();
        if (carRepository.existsById(carId)) {
            throw CarAlreadyExistsException.getInstanceById(carId);
        }

        CarEntity carEntity = carMapper.toEntity(car);
        CarEntity createdCarEntity = carRepository.create(carEntity);

        return carMapper.toDomain(createdCarEntity);
    }

    public Car getOne(Long id) {
        CarEntity carEntity = carRepository.findById(id).orElseThrow(() ->
                CarNotFoundException.byCarId(id)
        );

        return carMapper.toDomain(carEntity);
    }

    public List<Car> getList() {
        List<CarEntity> carEntities = carRepository.findAll();

        return carMapper.toDomains(carEntities);
    }

    public Car update(Long id, Car car) {
        if (!carRepository.existsById(id)) {
            throw CarNotFoundException.byCarId(id);
        }

        CarEntity carEntity = carMapper.toEntity(car);
        CarEntity updatedCarEntity = carRepository.update(id, carEntity);

        return carMapper.toDomain(updatedCarEntity);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
