package com.gms.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Application starts from here.
 *
 * @author Sachin Singh Rajpoot
 * @version 1.0
 */

@SpringBootApplication
public class GmsApplication {

  /**
   * The main entry point of the application.
   *
   * @param args The command-line arguments.
   */
  public static void main(final String[] args) {
    SpringApplication.run(GmsApplication.class, args);
  }

  /**
   * Create a new instance of ModelMapper to be used as a Spring bean.
   *
   * @return The configured ModelMapper instance.
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
