package by.harlap.monitoringserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSensorDto {

    @NotBlank(message = "Название сенсора не должно быть пустым.")
    @Size(min = 3, max = 30, message = "Название сенсора должно содержать от 3 до 30 символов.")
    private String name;
}
