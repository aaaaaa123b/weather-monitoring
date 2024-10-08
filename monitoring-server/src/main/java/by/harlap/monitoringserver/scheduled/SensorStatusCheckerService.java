package by.harlap.monitoringserver.scheduled;

import by.harlap.monitoringserver.model.Sensor;
import by.harlap.monitoringserver.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class SensorStatusCheckerService {

    private final SensorRepository sensorRepository;

    @Scheduled(fixedRate = 30000)
    @Transactional
    public void checkSensorStatus() {
        log.info("Проведение периодической проверки статусов счётчиков");
        List<Sensor> sensors = sensorRepository.findAll();

        int disabledSensors = 0;
        for (Sensor sensor : sensors) {
            LocalDateTime lastMeasurementTime = sensor.getLastMeasurementTime();

            if (sensor.isActive() && lastMeasurementTime != null && LocalDateTime.now().minusSeconds(30L).isAfter(lastMeasurementTime)) {
                sensor.setActive(false);
                disabledSensors++;
                log.info("Сенсор {} с ключом {} был отключен из-за бездействия более 30 секунд", sensor.getName(), sensor.getKey());
                sensorRepository.save(sensor);
            }
        }

        log.info("Статус {} счётчиков изменён на неактивный", disabledSensors);
    }
}
