package emerick.igor.javatodolist.modules.to_do_list.dtos.stage;

import java.util.UUID;

import lombok.Data;

@Data
public class StageControllerCreateRequestDTO {
  private String name;
  private UUID projectId;
}
