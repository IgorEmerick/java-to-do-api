package emerick.igor.javatodolist.shared.providers.implementations;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import emerick.igor.javatodolist.shared.providers.models.ICacheProvider;

public class RedisCacheProviderImpl<K, V> implements ICacheProvider<K, V> {
  private RedisTemplate<K, V> template;

  public RedisCacheProviderImpl(JedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<K, V> template = new RedisTemplate<>();

    template.setConnectionFactory(redisConnectionFactory);

    this.template = template;
  }

  @Override
  public void set(K key, V value) {
    template.opsForValue().set(key, value);
  }

  @Override
  public void set(K key, V value, LocalDateTime expiresAt) {
    Timestamp nowTimestamp = Timestamp.valueOf(LocalDateTime.now());
    Timestamp expiresAtTimestmp = Timestamp.valueOf(expiresAt);

    Long expiresInMilliseconds = expiresAtTimestmp.getTime() - nowTimestamp.getTime();

    template.opsForValue().set(key, value, Duration.ofMillis(expiresInMilliseconds));
  }

  @Override
  public V get(K key) {
    return template.opsForValue().get(key);
  }

  @Override
  public void invalidate(K key) {
    template.delete(key);
  }
}
