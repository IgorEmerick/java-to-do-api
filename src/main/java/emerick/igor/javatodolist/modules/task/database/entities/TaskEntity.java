package emerick.igor.javatodolist.modules.task.database.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "tasks")
@JsonIgnoreProperties(value = { "user" })
public class TaskEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private UserEntity user;

  @Column(name = "user_id")
  private UUID userId;

  private String description;

  private String title;

  @Column(nullable = true)
  private LocalDateTime startTime;

  @Column(nullable = true)
  private LocalDateTime finishTime;

  @Column(nullable = true)
  private String priority;

  public TaskEntity() {
  }

  public TaskEntity(UUID userId, String description, String title) {
    this.userId = userId;
    this.description = description;
    this.title = title;
  }
}
