package emerick.igor.javatodolist.modules.task.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tasks")
public class TaskEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  
  @CreationTimestamp
  private LocalDateTime createdAt;

  private UUID userId;

  private String description;

  private String title;
  
  private LocalDateTime startTime;
  
  private LocalDateTime finishTime;
  
  private String priority;
}
