package emerick.igor.javatodolist.shared.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import emerick.igor.javatodolist.shared.providers.implementations.BCryptHashProvider;
import emerick.igor.javatodolist.shared.providers.models.IHashProvider;

@Configuration
public class ProvidersConfig {
  @Bean
  IHashProvider hashProvider() {
    return new BCryptHashProvider();
  }
}
