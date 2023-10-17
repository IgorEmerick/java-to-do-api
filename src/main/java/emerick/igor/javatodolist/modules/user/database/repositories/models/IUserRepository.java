package emerick.igor.javatodolist.modules.user.database.repositories.models;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
  UserEntity findByUsername(String username);
  Optional<UserEntity> findById(UUID id);
}
