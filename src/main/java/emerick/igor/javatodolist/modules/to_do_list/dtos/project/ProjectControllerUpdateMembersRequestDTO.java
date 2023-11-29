package emerick.igor.javatodolist.modules.to_do_list.dtos.project;

import lombok.Data;

@Data
public class ProjectControllerUpdateMembersRequestDTO {
  private String[] membersEmails;
}
