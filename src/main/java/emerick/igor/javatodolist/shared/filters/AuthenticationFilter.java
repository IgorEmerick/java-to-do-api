package emerick.igor.javatodolist.shared.filters;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import emerick.igor.javatodolist.modules.user.database.entities.UserEntity;
import emerick.igor.javatodolist.modules.user.database.repositories.IUserRepository;
import emerick.igor.javatodolist.shared.providers.models.IEnvironmentProvider;
import emerick.igor.javatodolist.shared.providers.models.ITokenProvider;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
  private IUserRepository userRepository;

  private ITokenProvider tokenProvider;

  private IEnvironmentProvider environmentProvider;

  public AuthenticationFilter(IUserRepository userRepository, ITokenProvider tokenProvider,
      IEnvironmentProvider environmentProvider) {
    this.userRepository = userRepository;
    this.tokenProvider = tokenProvider;
    this.environmentProvider = environmentProvider;
  }

  @Override
  public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) arg0;
    HttpServletResponse response = (HttpServletResponse) arg1;

    String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader == null) {
      response.sendError(401);

      return;
    }

    String[] authorizationParts = authorizationHeader.split(" ");

    if (authorizationParts.length != 2 || !authorizationParts[0].equals("Bearer")) {
      response.sendError(401);

      return;
    }

    Map<String, String> payload = this.tokenProvider.verify(this.environmentProvider.get("AUTH_SECRET"),
        authorizationParts[1]);

    if (payload == null) {
      response.sendError(401);

      return;
    }

    String userId = payload.get("userId");

    if (userId == null) {
      response.sendError(401);

      return;
    }

    UserEntity user = this.userRepository.findById(UUID.fromString(userId)).get();

    if (user == null) {
      response.sendError(401);

      return;
    }

    request.setAttribute("userId", user.getId());

    chain.doFilter(request, response);
  }

}
