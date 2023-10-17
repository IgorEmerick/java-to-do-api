package emerick.igor.javatodolist.modules.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import emerick.igor.javatodolist.modules.user.entities.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
  UserEntity findByUsername(String username);
}
