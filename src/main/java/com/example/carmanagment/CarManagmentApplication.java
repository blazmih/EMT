package com.example.carmanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
@SpringBootApplication
public class CarManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarManagmentApplication.class, args);
    }
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
