package com.xiongs.dataway;

import net.hasor.spring.boot.EnableHasor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableHasor(useProperties = true)
@SpringBootApplication(scanBasePackages = "com.xiongs.dataway")
public class DataSpringbootApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DataSpringbootApplication.class, args);
    }
}
