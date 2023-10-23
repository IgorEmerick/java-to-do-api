package emerick.igor.javatodolist.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import emerick.igor.javatodolist.modules.user.services.UserService;
import emerick.igor.javatodolist.shared.infra.errors.HttpError;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private ApplicationContext context;

  @PostMapping("/")
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) throws HttpError {
    UserService userService = context.getBean(UserService.class);

    UserEntity createdUser = userService.create(user.getName(), user.getUsername(), user.getPassword());

    return ResponseEntity.status(201).body(createdUser);
  }
}
