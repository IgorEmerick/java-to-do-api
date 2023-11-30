package emerick.igor.javatodolist.modules.to_do_list.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emerick.igor.javatodolist.modules.to_do_list.database.entities.ProjectEntity;
import emerick.igor.javatodolist.modules.to_do_list.database.entities.StageEntity;
import emerick.igor.javatodolist.modules.to_do_list.database.repositories.IProjectRepository;
import emerick.igor.javatodolist.modules.to_do_list.database.repositories.IStageRepository;
import emerick.igor.javatodolist.modules.to_do_list.dtos.stage.StageServiceCreateRequestDTO;
import emerick.igor.javatodolist.shared.errors.HttpError;

@Service
public class StageService {
  @Autowired
  private IStageRepository stageRepository;

  @Autowired
  private IProjectRepository projectRepository;

  public StageEntity create(StageServiceCreateRequestDTO request) throws HttpError {
    ProjectEntity project = this.projectRepository.findById(request.getProjectId()).orElse(null);

    if (project == null)
      throw new HttpError(404, "Project not found!");

    StageEntity stage = this.stageRepository.save(new StageEntity(request.getName(), request.getProjectId()));

    return stage;
  }
}
