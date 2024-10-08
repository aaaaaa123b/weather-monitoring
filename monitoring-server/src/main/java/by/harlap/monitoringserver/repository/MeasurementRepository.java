package by.harlap.monitoringserver.repository;

import by.harlap.monitoringserver.model.Measurement;
import by.harlap.monitoringserver.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    List<Measurement> findTop20BySensorOrderByTimestampDesc(Sensor sensor);

    List<Measurement> findAllByTimestampAfter(LocalDateTime oneMinuteAgo);
}
