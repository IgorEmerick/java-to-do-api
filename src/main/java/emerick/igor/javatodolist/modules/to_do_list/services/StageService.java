package emerick.igor.javatodolist.modules.to_do_list.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emerick.igor.javatodolist.modules.to_do_list.database.entities.ProjectMemberEntity;
import emerick.igor.javatodolist.modules.to_do_list.database.entities.StageEntity;
import emerick.igor.javatodolist.modules.to_do_list.database.repositories.IProjectMemberRepository;
import emerick.igor.javatodolist.modules.to_do_list.database.repositories.IStageRepository;
import emerick.igor.javatodolist.modules.to_do_list.dtos.stage.StageServiceCreateRequestDTO;
import emerick.igor.javatodolist.modules.to_do_list.dtos.stage.StageServiceUpdateRequestDTO;
import emerick.igor.javatodolist.shared.errors.HttpError;

@Service
public class StageService {
  @Autowired
  private IStageRepository stageRepository;

  @Autowired
  private IProjectMemberRepository projectMemberRepository;

  public StageEntity create(StageServiceCreateRequestDTO request) throws HttpError {
    ProjectMemberEntity projectMember = this.projectMemberRepository.findByProjectIdAndUserId(request.getProjectId(),
        request.getRequesterId());

    if (projectMember == null)
      throw new HttpError(403, "Access denied!");

    StageEntity stage = this.stageRepository.save(new StageEntity(request.getName(), request.getProjectId()));

    return stage;
  }

  public StageEntity update(StageServiceUpdateRequestDTO request) throws HttpError {
    StageEntity stage = this.stageRepository.findById(request.getStageId()).orElse(null);

    if (stage == null)
      throw new HttpError(404, "Stage not found!");

    ProjectMemberEntity projectMember = this.projectMemberRepository.findByProjectIdAndUserId(stage.getProjectId(),
        request.getRequesterId());

    if (projectMember == null)
      throw new HttpError(403, "Access denied!");

    stage.setName(request.getName());

    return this.stageRepository.save(stage);
  }
}
