package ru.clevertec.testcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.testcrud.domain.Car;
import ru.clevertec.testcrud.service.CarService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/cars", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<Car> getList() {
        return carService.getList();
    }

    @GetMapping("/{id}")
    public Car getOne(@PathVariable Long id) {
        return carService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car create(@RequestBody Car car) {
        return carService.create(car);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car update(@PathVariable Long id, @RequestBody Car car) {
        return carService.update(id, car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        carService.delete(id);
    }
}

