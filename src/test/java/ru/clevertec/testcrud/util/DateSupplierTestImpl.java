package ru.clevertec.testcrud.util;

import ru.clevertec.testcrud.helper.DateSupplier;

import java.time.Instant;

public class DateSupplierTestImpl implements DateSupplier {
    @Override
    public Instant getCurrentDateTime() {
        return Instant.parse("2024-09-15T01:02:03Z");
    }
}
