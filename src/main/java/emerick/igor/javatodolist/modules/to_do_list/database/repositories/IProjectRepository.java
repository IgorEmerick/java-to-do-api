package emerick.igor.javatodolist.modules.to_do_list.database.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import emerick.igor.javatodolist.modules.to_do_list.database.entities.ProjectEntity;

import java.util.Optional;

public interface IProjectRepository extends JpaRepository<ProjectEntity, UUID> {
  public ProjectEntity findByOwnerIdAndName(UUID ownerId, String name);

  public Optional<ProjectEntity> findById(UUID id);
}
