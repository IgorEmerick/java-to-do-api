package emerick.igor.javatodolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
  UserEntity findByUsername(String username);
}
