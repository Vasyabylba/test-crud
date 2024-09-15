package ru.clevertec.testcrud.exception;

public class CarNotFoundException extends RuntimeException {
    private CarNotFoundException(String message) {
        super(message);
    }

    public static CarNotFoundException byCarId(Long id) {
        return new CarNotFoundException(
                String.format("Car with id '%s' not found", id)
        );
    }
}
