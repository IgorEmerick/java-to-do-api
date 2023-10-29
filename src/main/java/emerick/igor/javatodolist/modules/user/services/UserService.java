package emerick.igor.javatodolist.modules.user.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import emerick.igor.javatodolist.modules.user.database.repositories.IUserRepository;
import emerick.igor.javatodolist.shared.errors.HttpError;
import emerick.igor.javatodolist.shared.providers.models.ICacheProvider;
import emerick.igor.javatodolist.shared.providers.models.IEnvironmentProvider;
import emerick.igor.javatodolist.shared.providers.models.IHashProvider;
import emerick.igor.javatodolist.shared.providers.models.ITokenProvider;
import emerick.igor.javatodolist.shared.utils.Utils;

@Service
public class UserService {
  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IHashProvider hashProvider;

  @Autowired
  private IEnvironmentProvider environmentProvider;

  @Autowired
  private ITokenProvider tokenProvider;

  @Autowired
  ICacheProvider<String, Integer> integerCacheProvider;

  public UserEntity create(String name, String email, String password) throws HttpError {
    UserEntity existsUser = this.userRepository.findByEmail(email);

    if (existsUser != null)
      throw new HttpError(400, "User already exists!");

    if (!Utils.validateEmail(email))
      throw new HttpError(400, "Invalid email!");

    if (!Utils.validateStrongPassword(password))
      throw new HttpError(400, "Weak password!");

    String hashPassword = this.hashProvider.getHash(password);

    UserEntity createdUser = this.userRepository.save(new UserEntity(name, email, hashPassword));

    createdUser.setPassword(null);

    return createdUser;
  }

  public String authenticate(String email, String password, Boolean validFor30Days) throws HttpError {
    String key = email + "@wrong-login-attempts";

    Integer wrongLoginAttempts = this.integerCacheProvider.get(key);

    if (wrongLoginAttempts != null && wrongLoginAttempts >= 3) {
      throw new HttpError(403, "Authentication denied!");
    }

    UserEntity user = this.userRepository.findByEmail(email);

    if (user == null || !this.hashProvider.compare(user.getPassword(), password)) {
      LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

      if (wrongLoginAttempts == null)
        this.integerCacheProvider.set(key, 1, tomorrow);
      else {
        this.integerCacheProvider.invalidate(key);

        this.integerCacheProvider.set(key, wrongLoginAttempts + 1, tomorrow);
      }

      throw new HttpError(401, "Invalid authentication!");
    }

    LocalDateTime expireDate = validFor30Days ? LocalDateTime.now().plusDays(30) : LocalDateTime.now().plusDays(1);

    return this.tokenProvider.generate(this.environmentProvider.get("AUTH_SECRET"), user.getId().toString(),
        expireDate);
  }
}
