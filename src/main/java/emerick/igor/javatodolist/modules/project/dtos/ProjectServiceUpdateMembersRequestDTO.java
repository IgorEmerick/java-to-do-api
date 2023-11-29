package emerick.igor.javatodolist.modules.project.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ProjectServiceUpdateMembersRequestDTO {
  private String[] membersEmails;
  private UUID projectId;

  public ProjectServiceUpdateMembersRequestDTO(String[] membersEmails, UUID projectId) {
    this.membersEmails = membersEmails;
    this.projectId = projectId;
  }
}
