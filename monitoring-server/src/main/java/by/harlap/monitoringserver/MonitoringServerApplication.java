package by.harlap.monitoringserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MonitoringServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoringServerApplication.class, args);
    }

}
