package by.harlap.monitoringserver.facade;

import by.harlap.monitoringserver.dto.CreateMeasurementDto;
import by.harlap.monitoringserver.model.Measurement;
import by.harlap.monitoringserver.model.Sensor;
import by.harlap.monitoringserver.service.MeasurementService;
import by.harlap.monitoringserver.service.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Log4j2
public class MeasurementFacade {

    private final SensorService sensorService;
    private final MeasurementService measurementService;

    @Transactional
    public Measurement addMeasurement(UUID key, CreateMeasurementDto measurementDto) {
        Sensor sensor = sensorService.findByKeyOrFail(key);

        final Measurement measurement = new Measurement();
        measurement.setValue(measurementDto.getValue());
        measurement.setRaining(measurementDto.getRaining());
        measurement.setSensor(sensor);
        measurement.setTimestamp(LocalDateTime.now());

        sensor.setLastMeasurementTime(LocalDateTime.now());
        sensorService.save(sensor);

        log.info("Добавлено измерение для сенсора {} с ключом {}: {}", sensor.getName(), sensor.getKey(), measurementDto);

        return measurementService.addMeasurement(measurement);
    }

    @Transactional(readOnly = true)
    public List<Measurement> getLast20Measurements(UUID key) {
        Sensor sensor = sensorService.findByKeyOrFail(key);
        return measurementService.getLast20Measurements(sensor);
    }

    @Transactional(readOnly = true)
    public List<Measurement> getActualMeasurements() {
        LocalDateTime requestTime = LocalDateTime.now();
        return measurementService.getActualMeasurements(requestTime);
    }
}
