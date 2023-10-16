package emerick.igor.javatodolist.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import emerick.igor.javatodolist.task.TaskModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "users")
public class UserModel {
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
  private List<TaskModel> tasks = new ArrayList<>();
}
