package emerick.igor.javatodolist.shared.utils;

public class Utils {
  public static Boolean validateStrongPassword(String password) {
    return password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")
        && password.matches(".*[&* {}\\[\\],=\\-().+;'\"`~/!@#$%^?<>].*") && password.length() >= 10;
  }

  public static Boolean validateEmail(String email) {
    return email.matches("^[a-zA-Z0-9_+&*-] + (?:\\.[a-zA-Z0-9_+&*-] + )*@(?:[a-zA-Z0-9-]+\\.) + [a-zA-Z]{2,7}");
  }
}
