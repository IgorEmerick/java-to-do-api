package emerick.igor.javatodolist.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import emerick.igor.javatodolist.modules.user.database.repositories.models.IUserRepository;
import emerick.igor.javatodolist.shared.errors.HttpError;
import emerick.igor.javatodolist.shared.providers.models.IHashProvider;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private IUserRepository userRepository;

  private IHashProvider hashProvider;

  public UserController(IHashProvider hashProvider) {
    this.hashProvider = hashProvider;
  }

  @PostMapping("/")
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) throws HttpError {
    var existsUser = this.userRepository.findByUsername(user.getUsername());

    if (existsUser != null) {
      throw new HttpError(400, "User already exists!");
    }

    var cypherPassword = this.hashProvider.getHash(user.getPassword());

    user.setPassword(cypherPassword);

    var createdUser = this.userRepository.save(user);

    return ResponseEntity.status(201).body(createdUser);
  }
}
