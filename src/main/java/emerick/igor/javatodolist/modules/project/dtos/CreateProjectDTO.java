package emerick.igor.javatodolist.modules.project.dtos;

import lombok.Data;

@Data
public class CreateProjectDTO {
  private String name;
  private String description;
  private String[] members;
}
