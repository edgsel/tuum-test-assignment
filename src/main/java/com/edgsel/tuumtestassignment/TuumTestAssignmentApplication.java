package com.edgsel.tuumtestassignment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "TUUM Test Assignment",
        version = "1.0",
        description = "Core banking solution for TUUM"
    )
)
public class TuumTestAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuumTestAssignmentApplication.class, args);
    }
}
