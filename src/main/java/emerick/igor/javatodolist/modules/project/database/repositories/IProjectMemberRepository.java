package emerick.igor.javatodolist.modules.project.database.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import emerick.igor.javatodolist.modules.project.database.entities.ProjectMemberEntity;

import java.util.Collection;
import java.util.List;

public interface IProjectMemberRepository extends JpaRepository<ProjectMemberEntity, UUID> {
  public List<ProjectMemberEntity> findByProjectId(UUID projectId);

  public void deleteByIdIn(Collection<UUID> ids);
}
