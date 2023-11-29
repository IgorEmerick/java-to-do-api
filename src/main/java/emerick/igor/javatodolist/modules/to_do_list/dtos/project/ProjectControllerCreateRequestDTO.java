package emerick.igor.javatodolist.modules.to_do_list.dtos.project;

import lombok.Data;

@Data
public class ProjectControllerCreateRequestDTO {
  private String name;
  private String description;
  private String[] members;
}
