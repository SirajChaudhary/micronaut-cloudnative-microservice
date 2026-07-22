package com.sirajchaudhary.healthcare;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Healthcare Appointment Service",
                version = "1.0.0",
                description = "Cloud Native Healthcare Appointment Microservice built using Micronaut.",
                contact = @Contact(
                        name = "Siraj Chaudhary",
                        url = "https://www.linkedin.com/in/sirajchaudhary/"
                ),
                license = @License(
                        name = "Apache License 2.0"
                )
        )
)
public class HealthcareAppointmentApplication {

    public static void main(String[] args) {
        Micronaut.run(HealthcareAppointmentApplication.class, args);
    }
}