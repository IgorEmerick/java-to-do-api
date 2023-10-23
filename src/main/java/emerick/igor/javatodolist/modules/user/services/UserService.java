package emerick.igor.javatodolist.modules.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import emerick.igor.javatodolist.modules.user.database.repositories.models.IUserRepository;
import emerick.igor.javatodolist.shared.infra.errors.HttpError;
import emerick.igor.javatodolist.shared.providers.models.IHashProvider;

@Service
public class UserService {
  @Autowired
  private IUserRepository userRepository;

  private IHashProvider hashProvider;

  public UserService(IHashProvider hashProvider) {
    this.hashProvider = hashProvider;
  }

  public UserEntity create(String name, String username, String password) throws HttpError {
    UserEntity user = this.userRepository.findByUsername(username);

    if (user != null) {
      throw new HttpError(400, "User already exists!");
    }

    String hashPassword = this.hashProvider.getHash(password);

    UserEntity createUser = new UserEntity(username, name, hashPassword);

    UserEntity createdUser = this.userRepository.save(createUser);

    createUser.setPassword(null);

    return createdUser;
  }
}
