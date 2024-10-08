package by.harlap.monitoringserver.controller.openapi;

import by.harlap.monitoringserver.dto.CreateMeasurementDto;
import by.harlap.monitoringserver.dto.CreateSensorDto;
import by.harlap.monitoringserver.dto.CreatedSensorDto;
import by.harlap.monitoringserver.dto.ErrorResponse;
import by.harlap.monitoringserver.model.Measurement;
import by.harlap.monitoringserver.model.Sensor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Tag(name = "Sensors", description = "API для управления сенсорами")
public interface SensorOpenAPI {

    @Operation(summary = "Зарегистрировать новый сенсор", tags = "Sensors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Сенсор успешно зарегистрирован",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreatedSensorDto.class), examples = @ExampleObject("""
                            {
                              "name": "New Sensor",
                              "id": "2a4b6c8d-e2fa-42ef-984d-123456789abc"
                            }
                            """))),
            @ApiResponse(responseCode = "400", description = "Неверные данные запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject("""
                            {
                              "messages": [
                                "Название сенсора не должно быть пустым.",
                                "Название сенсора должно содержать от 3 до 30 символов."
                              ]
                            }
                            """)))
    })
    CreatedSensorDto registerSensor(@Valid @RequestBody CreateSensorDto createSensorDto);

    @Operation(summary = "Добавить новое измерение для сенсора", tags = "Sensors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Измерение успешно добавлено",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Measurement.class), examples = @ExampleObject("""
                            {
                              "value": 21.7,
                              "raining": true
                            }
                            """))),
            @ApiResponse(responseCode = "404", description = "Сенсор не найден"),
            @ApiResponse(responseCode = "400", description = "Неверные данные запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject("""
                            {
                              "messages": [
                                "Температура не должна быть пустой.",
                                "Температура не может быть меньше -100."
                              ]
                            }
                            """)))
    })
    Measurement addMeasurement(@PathVariable UUID key, @Valid @RequestBody CreateMeasurementDto createMeasurementDto);

    @Operation(summary = "Получить все активные сенсоры", tags = "Sensors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список активных сенсоров успешно получен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Sensor.class), examples = @ExampleObject("""
                                [
                                    {
                                      "id": 2,
                                      "name": "weather-sensor-880",
                                      "key": "018a6a48-b4a9-4b1f-b40b-bf9f500225d5",
                                      "active": true,
                                      "lastMeasurementTime": 2024-10-08T06:24:18.617013
                                    }
                                  ]
                            """)))
    })
    List<Sensor> getAllActiveSensors();

    @Operation(summary = "Получить последние 20 измерений по ключу сенсора", tags = "Sensors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Последние 20 измерений успешно получены",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Measurement.class), examples = @ExampleObject("""
                                [
                                    {
                                      "id": 122,
                                      "value": 100,
                                      "raining": true,
                                      "timestamp": "2024-10-08T06:24:18.617013",
                                      "sensor": {
                                        "id": 19,
                                        "name": "string",
                                        "key": "e8887968-28af-4834-8df9-18b2a9f5c500",
                                        "active": true,
                                        "lastMeasurementTime": "2024-10-08T06:24:18.617013"
                                      }
                                    }
                                  ]
                            """))),
            @ApiResponse(responseCode = "404", description = "Сенсор не найден")
    })
    List<Measurement> getLast20Measurements(@PathVariable UUID key);

    @Operation(summary = "Получить актуальные измерения от всех сенсоров", tags = "Sensors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Актуальные измерения успешно получены",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Measurement.class), examples = @ExampleObject("""
                                [
                                     {
                                       "id": 123,
                                       "value": 48.004913,
                                       "raining": true,
                                       "timestamp": "2024-10-08T06:27:51.184279",
                                       "sensor": {
                                         "id": 20,
                                         "name": "weather-sensor-936",
                                         "key": "5fe450f5-a954-4d55-bdbb-584c6e92467f",
                                         "active": true,
                                         "lastMeasurementTime": "2024-10-08T06:27:51.184279"
                                       }
                                     }
                                   ]
                            """)))
    })
    List<Measurement> getActualMeasurements();
}
