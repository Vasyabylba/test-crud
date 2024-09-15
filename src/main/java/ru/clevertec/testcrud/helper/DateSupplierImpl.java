package ru.clevertec.testcrud.helper;

import java.time.Instant;

public class DateSupplierImpl implements DateSupplier {
    @Override
    public Instant getCurrentDateTime() {
        return Instant.now();
    }
}
