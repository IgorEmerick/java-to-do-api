package emerick.igor.javatodolist.modules.project.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ProjectServiceUpdateRequestDTO {
  private UUID id;
  private UUID ownerId;
  private UUID requestId;
  private String name;
  private String description;

  public ProjectServiceUpdateRequestDTO(UUID id, UUID ownerId, String name, String description, UUID requestId) {
    this.id = id;
    this.ownerId = ownerId;
    this.name = name;
    this.description = description;
    this.requestId = requestId;
  }
}
