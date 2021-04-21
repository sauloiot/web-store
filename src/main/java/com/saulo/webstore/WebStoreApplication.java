package com.saulo.webstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class WebStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebStoreApplication.class, args);
    }

}
