package emerick.igor.javatodolist.modules.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emerick.igor.javatodolist.modules.project.database.entities.ProjectEntity;
import emerick.igor.javatodolist.modules.project.database.entities.ProjectMemberEntity;
import emerick.igor.javatodolist.modules.project.database.repositories.IProjectMemberRepository;
import emerick.igor.javatodolist.modules.project.database.repositories.IProjectRepository;
import emerick.igor.javatodolist.modules.project.dtos.ProjectServiceCreateRequestDTO;
import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import emerick.igor.javatodolist.modules.user.database.repositories.IUserRepository;
import emerick.igor.javatodolist.shared.errors.HttpError;

@Service
public class ProjectService {
  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IProjectRepository projectRepository;

  @Autowired
  private IProjectMemberRepository projectMemberRepository;

  public ProjectEntity create(ProjectServiceCreateRequestDTO request) throws HttpError {
    UserEntity owner = this.userRepository.findById(request.getUserId()).get();

    if (owner == null)
      throw new HttpError(404, "User not found!");

    ProjectEntity createProject = new ProjectEntity(owner.getId(), request.getName(), request.getDescription());

    ProjectEntity project = this.projectRepository.save(createProject);

    List<ProjectMemberEntity> projectMembers = new ArrayList<>();

    projectMembers.add(new ProjectMemberEntity(project.getId(), owner.getId()));

    if (request.getMembers() == null) {
      this.projectMemberRepository.saveAll(projectMembers);

      return project;
    }

    for (String memberEmail : request.getMembers()) {
      UserEntity member = this.userRepository.findByEmail(memberEmail);

      if (member == null)
        continue;

      projectMembers.add(new ProjectMemberEntity(project.getId(), member.getId()));
    }

    this.projectMemberRepository.saveAll(projectMembers);

    return project;
  }
}
