package emerick.igor.javatodolist.shared.providers.implementations;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;
import emerick.igor.javatodolist.shared.providers.models.IHashProvider;

public class BCryptHashProvider implements IHashProvider {

  @Override
  public String getHash(String phrase) {
    return BCrypt.withDefaults().hashToString(12, phrase.toCharArray());
  }

  @Override
  public Boolean compare(String phrase, String hash) {
    Result verification = BCrypt.verifyer().verify(phrase.toCharArray(), hash.toCharArray());

    return verification.verified;
  }

}
