package ru.clevertec.testcrud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.testcrud.domain.Car;
import ru.clevertec.testcrud.entity.CarEntity;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    CarEntity toEntity(Car car);

    Car toDomain(CarEntity carEntity);

    List<CarEntity> toEntities(List<Car> car);

    List<Car> toDomains(List<CarEntity> carEntity);
}