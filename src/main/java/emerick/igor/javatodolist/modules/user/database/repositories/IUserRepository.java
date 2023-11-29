package emerick.igor.javatodolist.modules.user.database.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
  public UserEntity findByEmail(String email);

  public Optional<UserEntity> findById(UUID id);

  public List<UserEntity> findByEmailIn(Collection<String> emails);
}
