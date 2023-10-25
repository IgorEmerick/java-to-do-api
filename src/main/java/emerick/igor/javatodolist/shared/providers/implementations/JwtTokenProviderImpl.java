package emerick.igor.javatodolist.shared.providers.implementations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import emerick.igor.javatodolist.shared.providers.models.ITokenProvider;

public class JwtTokenProviderImpl implements ITokenProvider {

  @Override
  public String generate(String secret, String payload) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.create().withClaim("payload", payload).withIssuer("auth0").sign(algorithm);
  }

}
