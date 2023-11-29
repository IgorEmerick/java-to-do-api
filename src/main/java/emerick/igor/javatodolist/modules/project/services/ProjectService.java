package emerick.igor.javatodolist.modules.project.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emerick.igor.javatodolist.modules.project.database.entities.ProjectEntity;
import emerick.igor.javatodolist.modules.project.database.entities.ProjectMemberEntity;
import emerick.igor.javatodolist.modules.project.database.repositories.IProjectMemberRepository;
import emerick.igor.javatodolist.modules.project.database.repositories.IProjectRepository;
import emerick.igor.javatodolist.modules.project.dtos.ProjectServiceCreateRequestDTO;
import emerick.igor.javatodolist.modules.project.dtos.ProjectServiceUpdateMembersRequestDTO;
import emerick.igor.javatodolist.modules.project.dtos.ProjectServiceUpdateRequestDTO;
import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import emerick.igor.javatodolist.modules.user.database.repositories.IUserRepository;
import emerick.igor.javatodolist.shared.errors.HttpError;
import emerick.igor.javatodolist.shared.utils.Utils;

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

    ProjectEntity existsProject = this.projectRepository.findByOwnerIdAndName(owner.getId(), request.getName());

    if (existsProject != null)
      throw new HttpError(400, "Project already exists!");

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

  public ProjectEntity update(ProjectServiceUpdateRequestDTO request) throws HttpError {

    ProjectEntity project = this.projectRepository.findById(request.getId()).get();

    if (project == null)
      throw new HttpError(404, "Project not found!");

    if (!project.getOwnerId().equals(request.getRequestId())) {
      throw new HttpError(403, "Access denied!");
    }

    Utils.copyNonNullProperties(request, project);

    return this.projectRepository.save(project);
  }

  public List<UserEntity> updateMembers(ProjectServiceUpdateMembersRequestDTO request) throws HttpError {
    ProjectEntity project = this.projectRepository.findById(request.getProjectId()).get();

    if (project == null) {
      throw new HttpError(404, "Project not found!");
    }

    if (!project.getOwnerId().equals(request.getRequesterId()))
      throw new HttpError(403, "Access denied!");

    List<ProjectMemberEntity> projectMemberList = this.projectMemberRepository.findByProjectId(project.getId());

    List<UserEntity> memberUserList = this.userRepository.findByEmailIn(Arrays.asList(request.getMembersEmails()));

    List<UUID> removeMemberIdList = projectMemberList.stream()
        .filter(member -> memberUserList.stream().noneMatch(user -> user.getId().equals(member.getUserId())))
        .map(member -> member.getId()).toList();

    if (removeMemberIdList.size() > 0)
      this.projectMemberRepository.deleteByIdIn(removeMemberIdList);

    List<ProjectMemberEntity> createMemberList = memberUserList.stream()
        .filter(user -> projectMemberList.stream().noneMatch(member -> member.getUserId().equals(user.getId())))
        .map(user -> new ProjectMemberEntity(project.getId(), user.getId())).toList();

    if (createMemberList.size() > 0)
      this.projectMemberRepository.saveAll(createMemberList);

    return memberUserList;
  }
}
