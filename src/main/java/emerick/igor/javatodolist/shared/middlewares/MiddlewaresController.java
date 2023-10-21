package emerick.igor.javatodolist.shared.middlewares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import emerick.igor.javatodolist.modules.user.database.repositories.models.IUserRepository;
import emerick.igor.javatodolist.shared.filters.AuthenticationFilter;

@Configuration
public class MiddlewaresController {
  @Autowired
  IUserRepository userRepository;

  @Bean
  FilterRegistrationBean<AuthenticationFilter> registerAuthenticationMiddleware() {
    FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<AuthenticationFilter>();

    registrationBean.setFilter(new AuthenticationFilter(this.userRepository));
    registrationBean.addUrlPatterns("/task/*");
    registrationBean.setOrder(0);

    return registrationBean;
  }
}
