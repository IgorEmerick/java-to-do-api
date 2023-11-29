package emerick.igor.javatodolist.modules.to_do_list.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ProjectServiceUpdateMembersRequestDTO {
  private String[] membersEmails;
  private UUID projectId;
  private UUID requesterId;

  public ProjectServiceUpdateMembersRequestDTO(String[] membersEmails, UUID projectId, UUID requestId) {
    this.membersEmails = membersEmails;
    this.projectId = projectId;
    this.requesterId = requestId;
  }
}
