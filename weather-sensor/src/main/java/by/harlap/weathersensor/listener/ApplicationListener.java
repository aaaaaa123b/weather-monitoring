package by.harlap.weathersensor.listener;

import by.harlap.weathersensor.service.SensorDataSenderService;
import by.harlap.weathersensor.service.SensorRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
@RequiredArgsConstructor
public class ApplicationListener {

    private final SensorDataSenderService sensorDataSenderService;
    private final SensorRegistrationService sensorRegistrationService;

    @EventListener(ApplicationReadyEvent.class)
    public void runMeasureAlerting() {
        log.info("Сенсор запущен");

        final UUID key = sensorRegistrationService.registerSensor();

        sensorDataSenderService.scheduleSendWeatherData(key);
    }
}
