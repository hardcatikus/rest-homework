package com.netcracker;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Contacts API", version = "1.0", description = "Contacts web service"))
public class ContactRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactRestApplication.class, args);
    }

}
