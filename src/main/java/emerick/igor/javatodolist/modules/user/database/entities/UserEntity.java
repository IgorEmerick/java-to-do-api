package emerick.igor.javatodolist.modules.user.database.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import emerick.igor.javatodolist.modules.project.database.entities.ProjectEntity;
import emerick.igor.javatodolist.modules.project.database.entities.ProjectMemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "users")
@JsonIgnoreProperties(value = { "ownedProjects", "projects" })
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

  @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
  private Set<ProjectEntity> ownedProjects = new HashSet<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Set<ProjectMemberEntity> projects = new HashSet<>();

  public UserEntity() {
  }

  public UserEntity(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }
}
