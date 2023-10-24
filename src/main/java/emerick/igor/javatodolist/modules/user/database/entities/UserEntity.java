package emerick.igor.javatodolist.modules.user.database.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  private String name;

  @Column(unique = true)
  private String email;

  private String password;

  public UserEntity() {
  }

  public UserEntity(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }
}
