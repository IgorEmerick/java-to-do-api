package emerick.igor.javatodolist.shared.utils;

public class Utils {
  public static Boolean validateStrongPassword(String password) {
    return password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")
        && password.matches(".*[&* {}\\[\\],=\\-().+;'\"`~/!@#$%^?<>].*") && password.length() >= 10;
  }
}
