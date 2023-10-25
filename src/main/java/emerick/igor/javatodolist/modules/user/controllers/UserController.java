package emerick.igor.javatodolist.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emerick.igor.javatodolist.modules.dtos.AuthenticateUserDTO;
import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import emerick.igor.javatodolist.modules.user.services.UserService;
import emerick.igor.javatodolist.shared.errors.HttpError;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/")
  public ResponseEntity<UserEntity> create(@RequestBody UserEntity user) throws HttpError {
    UserEntity createdUser = this.userService.create(user.getName(), user.getEmail(), user.getPassword());

    return ResponseEntity.status(201).body(createdUser);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<String> authenticate(@RequestBody AuthenticateUserDTO authenticateRequest) throws HttpError {
    String token = this.userService.authenticate(authenticateRequest.getEmail(), authenticateRequest.getPassword());

    return ResponseEntity.ok(token);
  }
}
