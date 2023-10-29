package emerick.igor.javatodolist.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class ExternalConnectionsConfig {
  @Bean
  JedisConnectionFactory redisConnectionFactory() {
    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6479);

    JedisConnectionFactory connectionFactory = new JedisConnectionFactory(config);

    return connectionFactory;
  }
}
