package emerick.igor.javatodolist.modules.project.dtos;

import lombok.Data;

@Data
public class ProjectControllerUpdateMembersRequestDTO {
  private String[] membersEmails;
}
