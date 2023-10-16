package emerick.igor.javatodolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
  public static String[] getNullPropertyNames(Object source) {
    BeanWrapper src = new BeanWrapperImpl(source);

    PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();

    Set<String> emptyProperties = new HashSet<>();

    for (PropertyDescriptor propertyDescriptor: propertyDescriptors) {
      String propertyName = propertyDescriptor.getName();
      
      Object propertyValue = src.getPropertyValue(propertyName);

      if (propertyValue == null) {
        emptyProperties.add(propertyName);
      }
    }

    return emptyProperties.toArray(new String[emptyProperties.size()]);
  }

  public static void copyNonNullProperties(Object source, Object target) {
    BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
  }
}
