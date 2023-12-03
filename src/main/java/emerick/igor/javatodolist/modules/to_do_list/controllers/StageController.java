package emerick.igor.javatodolist.modules.to_do_list.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emerick.igor.javatodolist.modules.to_do_list.database.entities.StageEntity;
import emerick.igor.javatodolist.modules.to_do_list.dtos.stage.StageControllerCreateRequestDTO;
import emerick.igor.javatodolist.modules.to_do_list.dtos.stage.StageControllerUpdateRequestDTO;
import emerick.igor.javatodolist.modules.to_do_list.dtos.stage.StageServiceCreateRequestDTO;
import emerick.igor.javatodolist.modules.to_do_list.dtos.stage.StageServiceUpdateRequestDTO;
import emerick.igor.javatodolist.modules.to_do_list.services.StageService;
import emerick.igor.javatodolist.shared.errors.HttpError;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/stage")
public class StageController {
  @Autowired
  private StageService stageService;

  @PostMapping("/")
  public ResponseEntity<StageEntity> create(@RequestBody StageControllerCreateRequestDTO requestBody,
      HttpServletRequest request) throws HttpError {

    UUID userId = (UUID) request.getAttribute("userId");

    StageEntity stage = this.stageService
        .create(new StageServiceCreateRequestDTO(requestBody.getName(), requestBody.getProjectId(), userId));

    return ResponseEntity.status(201).body(stage);
  }

  @PutMapping("/update/{stageId}")
  public ResponseEntity<StageEntity> update(@PathVariable UUID stageId,
      @RequestBody StageControllerUpdateRequestDTO requestBody, HttpServletRequest request) throws HttpError {

    UUID userId = (UUID) request.getAttribute("userId");

    StageEntity stage = this.stageService
        .update(new StageServiceUpdateRequestDTO(userId, stageId, requestBody.getName()));

    return ResponseEntity.ok(stage);
  }
}