package by.harlap.weathersensor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "monitoring-server")
@Getter
@Setter
public class MonitoringServerProperties {

    private String registerUrl;
    private String measurementUrl;
}
