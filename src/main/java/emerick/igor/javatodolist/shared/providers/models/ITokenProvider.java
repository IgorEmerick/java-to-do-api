package emerick.igor.javatodolist.shared.providers.models;

import java.time.LocalDateTime;

public interface ITokenProvider {
  public String generate(String secret, String payload, LocalDateTime expireDate);
}
