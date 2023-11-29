package emerick.igor.javatodolist.modules.to_do_list.database.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import emerick.igor.javatodolist.modules.to_do_list.database.entities.StageEntity;

public interface IStageRepository extends JpaRepository<StageEntity, UUID> {

}
