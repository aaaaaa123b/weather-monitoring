package by.harlap.monitoringserver.service;

import by.harlap.monitoringserver.model.Measurement;
import by.harlap.monitoringserver.model.Sensor;
import by.harlap.monitoringserver.repository.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    public List<Measurement> getLast20Measurements(Sensor sensor) {
        return measurementRepository.findTop20BySensorOrderByTimestampDesc(sensor);
    }

    public Measurement addMeasurement(Measurement measurement) {
        return measurementRepository.save(measurement);
    }

    public List<Measurement> getActualMeasurements(LocalDateTime requestTime) {
        LocalDateTime oneMinuteAgo = requestTime.minusMinutes(1);
        return measurementRepository.findAllByTimestampAfter(oneMinuteAgo);
    }
}