package emerick.igor.javatodolist.modules.dtos;

import lombok.Data;

@Data
public class AuthenticateUserDTO {
  private String email;
  private String password;
  private Boolean validFor30Days;
}
