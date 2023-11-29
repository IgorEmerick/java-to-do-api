package emerick.igor.javatodolist.modules.to_do_list.database.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import emerick.igor.javatodolist.modules.to_do_list.database.entities.ProjectMemberEntity;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;

public interface IProjectMemberRepository extends JpaRepository<ProjectMemberEntity, UUID> {
  @Transactional
  public List<ProjectMemberEntity> deleteByIdIn(Collection<UUID> ids);

  public List<ProjectMemberEntity> findByProjectId(UUID projectId);
}
