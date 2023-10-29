package emerick.igor.javatodolist.shared.providers.models;

import java.time.LocalDateTime;

public interface ICacheProvider<K, V> {
  public void set(K key, V value);

  public void set(K key, V value, LocalDateTime expiresAt);

  public V get(K key);

  public void invalidate(K key);
}
