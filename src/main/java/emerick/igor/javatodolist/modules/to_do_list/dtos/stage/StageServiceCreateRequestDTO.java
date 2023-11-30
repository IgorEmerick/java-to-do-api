package emerick.igor.javatodolist.modules.to_do_list.dtos.stage;

import java.util.UUID;

import lombok.Data;

@Data
public class StageServiceCreateRequestDTO {
  private String name;
  private UUID projectId;
  private UUID requesterId;

  public StageServiceCreateRequestDTO(String name, UUID projectId, UUID requesterId) {
    this.name = name;
    this.projectId = projectId;
    this.requesterId = requesterId;
  }
}
