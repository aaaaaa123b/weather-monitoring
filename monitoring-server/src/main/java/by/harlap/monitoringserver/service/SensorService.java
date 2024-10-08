package by.harlap.monitoringserver.service;

import by.harlap.monitoringserver.dto.CreatedSensorDto;
import by.harlap.monitoringserver.exception.EntityNotFoundException;
import by.harlap.monitoringserver.exception.SensorAlreadyExistsException;
import by.harlap.monitoringserver.model.Sensor;
import by.harlap.monitoringserver.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class SensorService {

    private final SensorRepository sensorRepository;

    public CreatedSensorDto registerSensor(String name) {
        final Optional<Sensor> existingSensor = sensorRepository.findByName(name);
        if (existingSensor.isPresent()) {
            throw new SensorAlreadyExistsException("Сенсор с таким именем уже существует.");
        }

        final Sensor sensor = new Sensor();
        sensor.setActive(true);
        sensor.setName(name);
        sensor.setKey(UUID.randomUUID());

        log.info("Зарегистрирован новый сенсор {} с ключом {}", name, sensor.getKey());

        final Sensor savedSensor = sensorRepository.save(sensor);
        return new CreatedSensorDto(savedSensor.getKey());
    }

    public Optional<Sensor> findByKey(UUID key) {
        return sensorRepository.findByKey(key);
    }

    public Sensor findByKeyOrFail(UUID key) {
        return findByKey(key)
                .orElseThrow(() -> new EntityNotFoundException("Датчик с ключом %s не найден".formatted(key)));
    }

    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public List<Sensor> findAllActive(){
        return sensorRepository.findAllByActive(true);
    }
}