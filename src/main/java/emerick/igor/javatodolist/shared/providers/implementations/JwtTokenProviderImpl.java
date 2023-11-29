package emerick.igor.javatodolist.shared.providers.implementations;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import emerick.igor.javatodolist.shared.providers.models.ITokenProvider;

public class JwtTokenProviderImpl implements ITokenProvider {

  @Override
  public String generate(String secret, Map<String, String> payload, LocalDateTime expireDate) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    Builder jwtBuilder = JWT.create().withExpiresAt(expireDate.toInstant(ZoneOffset.UTC))
        .withIssuer("emerick");

    if (payload != null) {
      for (Map.Entry<String, String> entry : payload.entrySet())
        jwtBuilder.withClaim(entry.getKey(), entry.getValue());
    }

    return jwtBuilder.sign(algorithm);
  }

  @Override
  public Map<String, String> verify(String secret, String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    JWTVerifier verifier = JWT.require(algorithm).withIssuer("emerick").build();

    DecodedJWT decodedJwt;

    try {
      decodedJwt = verifier.verify(token);
    } catch (JWTVerificationException error) {
      return null;
    }

    Map<String, String> payload = new HashMap<>();

    for (Map.Entry<String, Claim> entry : decodedJwt.getClaims().entrySet())
      payload.put(entry.getKey(), entry.getValue().asString());

    return payload;
  }

}
