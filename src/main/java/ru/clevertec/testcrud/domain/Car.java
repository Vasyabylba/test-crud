package ru.clevertec.testcrud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Long id;
    private String make;
    private String model;
    private Instant releaseDate;
}
