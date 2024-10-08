package by.harlap.monitoringserver.controller;

import by.harlap.monitoringserver.controller.openapi.SensorOpenAPI;
import by.harlap.monitoringserver.dto.CreateMeasurementDto;
import by.harlap.monitoringserver.dto.CreateSensorDto;
import by.harlap.monitoringserver.dto.CreatedSensorDto;
import by.harlap.monitoringserver.facade.MeasurementFacade;
import by.harlap.monitoringserver.model.Measurement;
import by.harlap.monitoringserver.model.Sensor;
import by.harlap.monitoringserver.service.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController implements SensorOpenAPI {

    private final SensorService sensorService;
    private final MeasurementFacade measurementFacade;

    @PostMapping("/registration")
    public CreatedSensorDto registerSensor(@Valid @RequestBody CreateSensorDto createSensorDto) {
        return sensorService.registerSensor(createSensorDto.getName());
    }

    @PostMapping("/{key}/measurements")
    public Measurement addMeasurement(@PathVariable UUID key, @Valid @RequestBody CreateMeasurementDto createMeasurementDto) {
        return measurementFacade.addMeasurement(key, createMeasurementDto);
    }

    @GetMapping()
    public List<Sensor> getAllActiveSensors() {
        return sensorService.findAllActive();
    }

    @GetMapping("/{key}/measurements")
    public List<Measurement> getLast20Measurements(@PathVariable UUID key) {
        return measurementFacade.getLast20Measurements(key);
    }

    @GetMapping("/measurements")
    public List<Measurement> getActualMeasurements() {
        return measurementFacade.getActualMeasurements();
    }
}
