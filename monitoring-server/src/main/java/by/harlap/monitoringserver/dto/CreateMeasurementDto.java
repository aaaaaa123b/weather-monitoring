package by.harlap.monitoringserver.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateMeasurementDto {

    @NotNull(message = "Температура не должна быть пустой.")
    @Min(value = -100, message = "Температура не может быть меньше -100.")
    @Max(value = 100, message = "Температура не может быть больше 100.")
    private Float value;

    @NotNull(message = "Информация о дожде не должна быть пустой.")
    private Boolean raining;
}