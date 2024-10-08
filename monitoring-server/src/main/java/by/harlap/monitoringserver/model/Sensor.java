package by.harlap.monitoringserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "sensors")
@Table(name = "sensors", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private UUID key;

    @Column(nullable = false)
    private boolean active = true;

    @Column
    private LocalDateTime lastMeasurementTime;
}

