package emerick.igor.javatodolist.modules.user.database.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import emerick.igor.javatodolist.modules.task.database.entities.TaskEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(unique = true)
  private String username;

  private String name;

  private String password;

  @OneToMany(mappedBy = "user")
  private Set<TaskEntity> tasks = new HashSet<>();
}
