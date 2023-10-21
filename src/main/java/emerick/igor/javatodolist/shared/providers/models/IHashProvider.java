package emerick.igor.javatodolist.shared.providers.models;

public interface IHashProvider {
  public String getHash(String phrase);

  public Boolean compare(String phrase, String hash);
}
