package emerick.igor.javatodolist.shared.providers.models;

import java.time.LocalDateTime;
import java.util.Map;

public interface ITokenProvider {
  public String generate(String secret, Map<String, String> payload, LocalDateTime expireDate);

  public Map<String, String> verify(String secret, String token);
}
