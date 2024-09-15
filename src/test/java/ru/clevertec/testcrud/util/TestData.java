package ru.clevertec.testcrud.util;

import ru.clevertec.testcrud.domain.Car;
import ru.clevertec.testcrud.entity.CarEntity;
import ru.clevertec.testcrud.helper.DateSupplier;

import java.util.List;

public class TestData {
    private static final DateSupplier DATE_SUPPLIER = new DateSupplierTestImpl();

    public static Car generateCar() {
        return Car.builder()
                .id(1L)
                .make("Lamborghini")
                .model("Aventador")
                .releaseDate(DATE_SUPPLIER.getCurrentDateTime())
                .build();
    }

    public static List<Car> generateCars() {
        Car car = TestData.generateCar();
        return List.of(car, car, car);
    }

    public static CarEntity generateCarEntity() {
        return CarEntity.builder()
                .id(1L)
                .make("Lamborghini")
                .model("Aventador")
                .releaseDate(DATE_SUPPLIER.getCurrentDateTime())
                .build();
    }

    public static List<CarEntity> generateCarEntities() {
        CarEntity carEntity = TestData.generateCarEntity();
        return List.of(carEntity, carEntity, carEntity);
    }
}
