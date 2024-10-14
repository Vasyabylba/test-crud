package ru.clevertec.testcrud.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.testcrud.entity.CarEntity;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CarRepository {
    private static final Map<Long, CarEntity> DATA = new HashMap<>(
            Map.of(
                    1L, new CarEntity(1L, "Volkswagen", "CC", Instant.now()),
                    2L, new CarEntity(2L, "Nissan", "JUKE", Instant.now()),
                    3L, new CarEntity(3L, "Ford", "Escape", Instant.now()),
                    4L, new CarEntity(4L, "Cadillac", "Escalade", Instant.now()),
                    5L, new CarEntity(5L, "Audi", "80", Instant.now())
            )
    );

    public CarEntity create(CarEntity entity) {
        return DATA.put(entity.getId(), entity);
    }

    public Optional<CarEntity> findById(Long id) {
        return Optional.ofNullable(DATA.get(id));
    }

    public boolean existsById(Long id) {
        return DATA.get(id) != null;
    }

    public List<CarEntity> findAll() {
        return DATA.values().stream().toList();
    }

    public CarEntity update(Long id, CarEntity newEntity) {
        return DATA.put(id, newEntity);
    }

    public void deleteById(Long id) {
        DATA.remove(id);
    }
}
