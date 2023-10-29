package emerick.igor.javatodolist.shared.providers.implementations;

import emerick.igor.javatodolist.shared.providers.models.IEnvironmentProvider;
import io.github.cdimascio.dotenv.Dotenv;

public class DotenvEnvironmentProviderImpl implements IEnvironmentProvider {

  @Override
  public String get(String key) {
    Dotenv environment = Dotenv.load();

    return environment.get(key);
  }

}
