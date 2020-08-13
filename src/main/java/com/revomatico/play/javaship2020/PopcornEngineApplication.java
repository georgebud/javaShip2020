package com.revomatico.play.javaship2020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@SpringBootApplication
public class PopcornEngineApplication {

  public static void main(String[] args) {
    SpringApplication.run(PopcornEngineApplication.class, args);
  }

  @Configuration
  @EnableWebFlux
  public class CorsGlobalConfiguration implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
      corsRegistry.addMapping("/**")
        .allowedOrigins("http://localhost:3000");
    }
  }
}
