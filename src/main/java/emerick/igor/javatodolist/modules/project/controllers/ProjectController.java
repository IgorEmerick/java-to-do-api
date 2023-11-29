package emerick.igor.javatodolist.modules.project.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emerick.igor.javatodolist.modules.project.database.entities.ProjectEntity;
import emerick.igor.javatodolist.modules.project.dtos.ProjectControllerCreateRequestDTO;
import emerick.igor.javatodolist.modules.project.dtos.ProjectControllerUpdateMembersRequestDTO;
import emerick.igor.javatodolist.modules.project.dtos.ProjectControllerUpdateRequestDTO;
import emerick.igor.javatodolist.modules.project.dtos.ProjectServiceCreateRequestDTO;
import emerick.igor.javatodolist.modules.project.dtos.ProjectServiceUpdateMembersRequestDTO;
import emerick.igor.javatodolist.modules.project.dtos.ProjectServiceUpdateRequestDTO;
import emerick.igor.javatodolist.modules.project.services.ProjectService;
import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import emerick.igor.javatodolist.shared.errors.HttpError;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/project")
public class ProjectController {
  @Autowired
  private ProjectService projectService;

  @PostMapping("/")
  public ResponseEntity<ProjectEntity> create(@RequestBody ProjectControllerCreateRequestDTO requestBody,
      HttpServletRequest request)
      throws HttpError {

    UUID userId = (UUID) request.getAttribute("userId");

    ProjectServiceCreateRequestDTO createRequest = new ProjectServiceCreateRequestDTO(requestBody.getName(),
        requestBody.getDescription(), userId, requestBody.getMembers());

    ProjectEntity project = this.projectService.create(createRequest);

    return ResponseEntity.status(201).body(project);
  }

  @PutMapping("/update/{projectId}")
  public ResponseEntity<ProjectEntity> update(@PathVariable UUID projectId,
      @RequestBody ProjectControllerUpdateRequestDTO requestBody, HttpServletRequest request) throws HttpError {

    UUID userId = (UUID) request.getAttribute("userId");

    ProjectEntity project = this.projectService
        .update(new ProjectServiceUpdateRequestDTO(projectId, requestBody.getOwnerId(), requestBody.getName(),
            requestBody.getDescription(), userId));

    return ResponseEntity.ok(project);
  }

  @PutMapping("/member/{projectId}")
  public ResponseEntity<List<UserEntity>> updateMembers(@PathVariable UUID projectId,
      @RequestBody ProjectControllerUpdateMembersRequestDTO requestBody, HttpServletRequest request) throws HttpError {

    UUID userId = (UUID) request.getAttribute("userId");

    List<UserEntity> memberList = this.projectService
        .updateMembers(new ProjectServiceUpdateMembersRequestDTO(requestBody.getMembersEmails(), projectId, userId));

    return ResponseEntity.ok(memberList);
  }
}
