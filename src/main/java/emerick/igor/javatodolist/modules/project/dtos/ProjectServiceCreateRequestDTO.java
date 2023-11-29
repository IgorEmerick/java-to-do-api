package emerick.igor.javatodolist.modules.project.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ProjectServiceCreateRequestDTO {
  private String name;
  private String description;
  private UUID userId;
  private String[] members;

  public ProjectServiceCreateRequestDTO(String name, String description, UUID userId, String[] members) {
    this.name = name;
    this.description = description;
    this.userId = userId;
    this.members = members;
  }
}
