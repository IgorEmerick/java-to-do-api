package emerick.igor.javatodolist.modules.project.controllers;

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
import emerick.igor.javatodolist.modules.project.dtos.CreateProjectDTO;
import emerick.igor.javatodolist.modules.project.dtos.ProjectControllerUpdateRequestDTO;
import emerick.igor.javatodolist.modules.project.dtos.ProjectServiceCreateRequestDTO;
import emerick.igor.javatodolist.modules.project.dtos.ProjectServiceUpdateRequestDTO;
import emerick.igor.javatodolist.modules.project.services.ProjectService;
import emerick.igor.javatodolist.shared.errors.HttpError;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/project")
public class ProjectController {
  @Autowired
  private ProjectService projectService;

  @PostMapping("/")
  public ResponseEntity<ProjectEntity> create(@RequestBody CreateProjectDTO requestBody, HttpServletRequest request)
      throws HttpError {
    UUID userId = (UUID) request.getAttribute("userId");

    ProjectServiceCreateRequestDTO createRequest = new ProjectServiceCreateRequestDTO(requestBody.getName(),
        requestBody.getDescription(), userId, requestBody.getMembers());

    ProjectEntity project = this.projectService.create(createRequest);

    return ResponseEntity.status(201).body(project);
  }

  @PutMapping("/update/{projectId}")
  public ResponseEntity<ProjectEntity> update(HttpServletRequest request, @PathVariable UUID projectId,
      @RequestBody ProjectControllerUpdateRequestDTO requestBody) throws HttpError {
    UUID userId = (UUID) request.getAttribute("userId");

    ProjectEntity project = this.projectService
        .update(new ProjectServiceUpdateRequestDTO(projectId, requestBody.getOwnerId(), requestBody.getName(),
            requestBody.getDescription()));

    return ResponseEntity.ok(project);
  }
}
