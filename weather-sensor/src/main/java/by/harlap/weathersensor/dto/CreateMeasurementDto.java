package by.harlap.weathersensor.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateMeasurementDto {

    private Float value;
    private Boolean raining;
}