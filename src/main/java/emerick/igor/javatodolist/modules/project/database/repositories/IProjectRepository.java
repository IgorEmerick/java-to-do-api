package emerick.igor.javatodolist.modules.project.database.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import emerick.igor.javatodolist.modules.project.database.entities.ProjectEntity;
import java.util.Optional;

public interface IProjectRepository extends JpaRepository<ProjectEntity, UUID> {
  public ProjectEntity findByOwnerIdAndName(UUID ownerId, String name);

  public Optional<ProjectEntity> findById(UUID id);
}
