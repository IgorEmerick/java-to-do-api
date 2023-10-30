package emerick.igor.javatodolist.modules.project.database.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import emerick.igor.javatodolist.modules.project.database.entities.ProjectMemberEntity;

public interface IProjectMemberRepository extends JpaRepository<ProjectMemberEntity, UUID> {

}
