package emerick.igor.javatodolist.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import emerick.igor.javatodolist.shared.providers.implementations.BCryptHashProviderImpl;
import emerick.igor.javatodolist.shared.providers.models.IHashProvider;

@Configuration
public class ProvidersConfig {
  @Bean
  IHashProvider hashProvider() {
    return new BCryptHashProviderImpl();
  }
}
