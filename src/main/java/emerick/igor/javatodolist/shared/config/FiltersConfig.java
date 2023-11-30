package emerick.igor.javatodolist.shared.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import emerick.igor.javatodolist.modules.user.database.repositories.IUserRepository;
import emerick.igor.javatodolist.shared.filters.AuthenticationFilter;
import emerick.igor.javatodolist.shared.providers.models.IEnvironmentProvider;
import emerick.igor.javatodolist.shared.providers.models.ITokenProvider;

@Configuration
public class FiltersConfig {
  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private ITokenProvider tokenProvider;

  @Autowired
  private IEnvironmentProvider environmentProvider;

  @Bean
  FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
    FilterRegistrationBean<AuthenticationFilter> registration = new FilterRegistrationBean<>();

    registration.setFilter(new AuthenticationFilter(this.userRepository, this.tokenProvider, this.environmentProvider));
    registration.addUrlPatterns("/project/*");
    registration.addUrlPatterns("/stage/*");
    registration.setOrder(0);

    return registration;
  }
}
