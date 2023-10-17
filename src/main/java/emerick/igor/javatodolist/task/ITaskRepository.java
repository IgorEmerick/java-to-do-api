package emerick.igor.javatodolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface ITaskRepository extends JpaRepository<TaskEntity, UUID> {
  List<TaskEntity> findByUserId(UUID userId);
  Optional<TaskEntity> findById(UUID id);
}
