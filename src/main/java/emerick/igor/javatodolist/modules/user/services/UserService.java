package emerick.igor.javatodolist.modules.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import emerick.igor.javatodolist.modules.user.database.repositories.IUserRepository;
import emerick.igor.javatodolist.shared.errors.HttpError;
import emerick.igor.javatodolist.shared.providers.models.IHashProvider;

@Service
public class UserService {
  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IHashProvider hashProvider;

  public UserEntity create(String name, String email, String password) throws HttpError {
    UserEntity existsUser = this.userRepository.findByEmail(email);

    if (existsUser != null) {
      throw new HttpError(400, "User already exists!");
    }

    String hashPassword = this.hashProvider.getHash(password);

    return this.userRepository.save(new UserEntity(name, email, hashPassword));
  }
}
