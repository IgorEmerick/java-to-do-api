package emerick.igor.javatodolist.shared.providers.implementations;

import at.favre.lib.crypto.bcrypt.BCrypt;
import emerick.igor.javatodolist.shared.providers.models.IHashProvider;

public class BCryptHashProviderImpl implements IHashProvider {

  @Override
  public String getHash(String phrase) {
    return BCrypt.withDefaults().hashToString(12, phrase.toCharArray());
  }

}
