package emerick.igor.javatodolist.shared.providers.implementations;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import emerick.igor.javatodolist.shared.providers.models.ITokenProvider;

public class JwtTokenProviderImpl implements ITokenProvider {

  @Override
  public String generate(String secret, String payload, LocalDateTime expireDate) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.create().withClaim("payload", payload).withExpiresAt(expireDate.toInstant(ZoneOffset.UTC))
        .withIssuer("auth0")
        .sign(algorithm);
  }

}
