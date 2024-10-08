package by.harlap.weathersensor.service;

import by.harlap.weathersensor.config.MonitoringServerProperties;
import by.harlap.weathersensor.dto.CreateMeasurementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class SensorDataSenderService {

    private final Random random = new Random();
    private final RestTemplate restTemplate;
    private final MonitoringServerProperties monitoringServerProperties;

    public void sendWeatherData(UUID key) {
        final float value = random.nextFloat() * 200 - 100;
        final boolean raining = random.nextBoolean();
        final CreateMeasurementDto measurementDto = new CreateMeasurementDto();
        measurementDto.setRaining(raining);
        measurementDto.setValue(value);

        log.info("Данные о погоде отправлены на сервер");

        final String url = monitoringServerProperties.getMeasurementUrl().replace("{key}", key.toString());

        restTemplate.postForLocation(url, measurementDto);
    }

    private Integer getRandomInterval() {
        return random.nextInt(13) + 3;
    }

    public void scheduleSendWeatherData(UUID key) {
        while (true) {
            try {
                int interval = getRandomInterval();
                log.info("Отправка данных через " + interval + " секунд");

                sendWeatherData(key);

                Thread.sleep(interval * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}