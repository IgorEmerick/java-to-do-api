package emerick.igor.javatodolist.modules.user.database.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

  public UserEntity() {
  }

  public UserEntity(String username, String name, String password) {
    this.username = username;
    this.name = name;
    this.password = password;
  }
}
