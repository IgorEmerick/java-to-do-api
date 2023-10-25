package emerick.igor.javatodolist.shared.providers.models;

public interface ITokenProvider {
  public String generate(String secret, String payload);
}
