package emerick.igor.javatodolist.modules.to_do_list.dtos.stage;

import java.util.UUID;

import lombok.Data;

@Data
public class StageServiceUpdateRequestDTO {
  private UUID requesterId;
  private UUID stageId;
  private String name;

  public StageServiceUpdateRequestDTO(UUID requesterId, UUID stageId, String name) {
    this.name = name;
    this.requesterId = requesterId;
    this.stageId = stageId;
  }
}
