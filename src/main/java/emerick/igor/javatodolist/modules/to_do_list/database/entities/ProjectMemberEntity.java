package emerick.igor.javatodolist.modules.to_do_list.database.entities;

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
@Entity(name = "projects_members")
@JsonIgnoreProperties(value = { "project", "user" })
public class ProjectMemberEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "project_id")
  private UUID projectId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id", insertable = false, updatable = false)
  private ProjectEntity project;

  @Column(name = "user_id")
  private UUID userId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private UserEntity user;

  public ProjectMemberEntity() {
  }

  public ProjectMemberEntity(UUID projectId, UUID userId) {
    this.projectId = projectId;
    this.userId = userId;
  }
}
