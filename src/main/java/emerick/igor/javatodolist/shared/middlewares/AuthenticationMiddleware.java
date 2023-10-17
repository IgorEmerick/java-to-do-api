package emerick.igor.javatodolist.shared.middlewares;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;
import emerick.igor.javatodolist.modules.user.IUserRepository;
import emerick.igor.javatodolist.modules.user.entities.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationMiddleware extends OncePerRequestFilter {
  @Autowired
  private IUserRepository userRepository;
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (!request.getServletPath().startsWith( "/task/")) {
      filterChain.doFilter(request, response);

      return;
    }

    String authorization = request.getHeader("Authorization");

    if (authorization == null) {
      response.sendError(401);

      return;
    }

    String token = authorization.substring(6);

    byte[] decodedToken = Base64.getDecoder().decode(token);

    String[] credentials = new String(decodedToken).split(":");

    String username = credentials[0];
    String password = credentials[1];

    UserEntity user = this.userRepository.findByUsername(username);

    if (user == null) {
      response.sendError(401);

      return;
    }

    Result passwordVerification = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

    if (!passwordVerification.verified) {
      response.sendError(401);

      return;
    }

    request.setAttribute("userId", user.getId());

    filterChain.doFilter(request, response);
  }
}
