package emerick.igor.javatodolist.modules.project.database.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "projects")
@JsonIgnoreProperties(value = { "owner", "members" })
public class ProjectEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Column(name = "owner_id")
  private UUID ownerId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id", insertable = false, updatable = false)
  private UserEntity owner;

  private String name;

  @Column(nullable = true)
  private String description;

  @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
  private Set<ProjectMemberEntity> members = new HashSet<>();
}
