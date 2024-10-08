package by.harlap.monitoringserver.repository;

import by.harlap.monitoringserver.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

    Optional<Sensor> findByName(String name);

    Optional<Sensor> findByKey(UUID key);

    List<Sensor> findAllByActive(boolean active);
}
