package emerick.igor.javatodolist.shared.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import emerick.igor.javatodolist.shared.providers.implementations.BCryptHashProviderImpl;
import emerick.igor.javatodolist.shared.providers.implementations.DotenvEnvironmentProviderImpl;
import emerick.igor.javatodolist.shared.providers.implementations.JwtTokenProviderImpl;
import emerick.igor.javatodolist.shared.providers.implementations.RedisCacheProviderImpl;
import emerick.igor.javatodolist.shared.providers.models.ICacheProvider;
import emerick.igor.javatodolist.shared.providers.models.IEnvironmentProvider;
import emerick.igor.javatodolist.shared.providers.models.IHashProvider;
import emerick.igor.javatodolist.shared.providers.models.ITokenProvider;

@Configuration
public class ProvidersConfig {
  @Autowired
  private JedisConnectionFactory redisConnectionFactory;

  @Bean
  IHashProvider hashProvider() {
    return new BCryptHashProviderImpl();
  }

  @Bean
  ITokenProvider tokenProvider() {
    return new JwtTokenProviderImpl();
  }

  @Bean
  IEnvironmentProvider environmentProvider() {
    return new DotenvEnvironmentProviderImpl();
  }

  @Bean
  ICacheProvider<String, Integer> numberCacheProvider() {
    return new RedisCacheProviderImpl<String, Integer>(this.redisConnectionFactory);
  }
}
