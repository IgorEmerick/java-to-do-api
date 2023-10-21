package emerick.igor.javatodolist.shared.filters;

import java.io.IOException;
import java.util.Base64;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;
import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import emerick.igor.javatodolist.modules.user.database.repositories.models.IUserRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
  private IUserRepository userRepository;

  public AuthenticationFilter(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    String authorizationHeader = req.getHeader("Authorization");

    if (authorizationHeader == null || !authorizationHeader.matches("^Basic .*")) {
      res.sendError(401);

      return;
    }

    String authorizationBase64 = authorizationHeader.split("Basic ")[1];

    String authorization = new String(Base64.getDecoder().decode(authorizationBase64));

    String[] credentials = authorization.split(":");

    String username = credentials[0];
    String password = credentials[1];

    UserEntity user = this.userRepository.findByUsername(username);

    if (user == null) {
      res.sendError(401);

      return;
    }

    Result validPassword = BCrypt.verifyer().verify(password.getBytes(), user.getPassword().getBytes());

    if (!validPassword.verified) {
      res.sendError(401);

      return;
    }

    req.setAttribute("userId", user.getId());

    chain.doFilter(request, response);
  }
}
