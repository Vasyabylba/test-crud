package ru.clevertec.testcrud.exception;

public class CarAlreadyExistsException extends RuntimeException {
    private CarAlreadyExistsException(String message) {
        super(message);
    }

    public static CarAlreadyExistsException getInstanceById(Long id) {
        return new CarAlreadyExistsException(
                String.format("Car with id '%s' already exists", id)
        );
    }
}
