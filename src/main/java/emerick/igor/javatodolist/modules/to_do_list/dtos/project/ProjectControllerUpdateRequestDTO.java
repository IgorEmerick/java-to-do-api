package emerick.igor.javatodolist.modules.to_do_list.dtos.project;

import java.util.UUID;

import lombok.Data;

@Data
public class ProjectControllerUpdateRequestDTO {
  private UUID ownerId;
  private String name;
  private String description;
}
