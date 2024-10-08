package by.harlap.weathersensor.service;

import by.harlap.weathersensor.config.MonitoringServerProperties;
import by.harlap.weathersensor.dto.CreateSensorDto;
import by.harlap.weathersensor.dto.CreatedSensorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class SensorRegistrationService {

    private final RestTemplate restTemplate;
    private final MonitoringServerProperties monitoringServerProperties;

    @Value("${spring.application.name}")
    private String applicationName;

    private final Random random = new Random();

    public UUID registerSensor() {
        final CreateSensorDto sensorDto = new CreateSensorDto();
        sensorDto.setName(applicationName + "-" + Math.abs(random.nextInt() % 1000));

        log.info("Данные о погоде отправлены на сервер");

        final String url = monitoringServerProperties.getRegisterUrl();

        CreatedSensorDto createdSensorDto = restTemplate.postForObject(url, sensorDto, CreatedSensorDto.class);

        return createdSensorDto.getKey();
    }
}