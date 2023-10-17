package emerick.igor.javatodolist.modules.task.database.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "tasks")
public class TaskEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  
  @CreationTimestamp
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private UserEntity user;

  @Column(name = "user_id")
  private UUID userId;

  private String description;

  private String title;
  
  private LocalDateTime startTime;
  
  private LocalDateTime finishTime;
  
  private String priority;
}
