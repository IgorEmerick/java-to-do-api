package emerick.igor.javatodolist.modules.to_do_list.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emerick.igor.javatodolist.modules.to_do_list.database.entities.StageEntity;
import emerick.igor.javatodolist.modules.to_do_list.database.repositories.IStageRepository;
import emerick.igor.javatodolist.modules.to_do_list.dtos.stage.StageServiceCreateRequestDTO;

@Service
public class StageService {
  @Autowired
  private IStageRepository stageRepository;

  public StageEntity create(StageServiceCreateRequestDTO request) {
    StageEntity stage = this.stageRepository.save(new StageEntity(request.getName(), request.getProjectId()));

    return stage;
  }
}
