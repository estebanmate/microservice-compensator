package com.masmovil.it.compensator;

import com.masmovil.it.compensator.Application;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = 
    @Info(
      title = "Microservice Compensator",
      version = "0.1",
      description = "Microservice Compensator"
    )
)
public class Application {
  public static void main(String[] args) {
    Micronaut.run(Application.class);
  }
}