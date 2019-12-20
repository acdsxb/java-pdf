package com.chensubao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class JavaPdfApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaPdfApplication.class, args);
    }

}
