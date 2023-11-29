package emerick.igor.javatodolist.shared.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
  public static Boolean validateStrongPassword(String password) {
    return password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")
        && password.matches(".*[&* {}\\[\\],=\\-().+;'\"`~/!@#$%^?<>].*") && password.length() >= 10;
  }

  public static Boolean validateEmail(String email) {
    return email.matches("^[a-zA-Z0-9_+&*-] + (?:\\.[a-zA-Z0-9_+&*-] + )*@(?:[a-zA-Z0-9-]+\\.) + [a-zA-Z]{2,7}");
  }

  public static void copyNonNullProperties(Object source, Object target) {
    BeanWrapper newSource = new BeanWrapperImpl(source);

    PropertyDescriptor[] propertiesDescriptors = newSource.getPropertyDescriptors();

    Set<String> nullProperties = new HashSet<>();

    for (PropertyDescriptor propertyDescriptor : propertiesDescriptors) {
      String propertyName = propertyDescriptor.getName();

      if (newSource.getPropertyValue(propertyName) == null)
        nullProperties.add(propertyName);
    }

    String[] nullPropertiesArray = nullProperties.toArray(new String[nullProperties.size()]);

    BeanUtils.copyProperties(source, target, nullPropertiesArray);
  }
}
