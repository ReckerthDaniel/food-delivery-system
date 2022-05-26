package business.validator;

import java.util.regex.Pattern;

public class FieldValidator {

  public static final String IS_NUMBER = "^[0-9]+$";
  public static final String IS_REAL_NUMBER = "^[0-9]+\\.?[0-9]*$";

  public static boolean isFieldNumber(String number) {
    return Pattern.matches(IS_NUMBER, number);
  }

  public static boolean isFieldRealNumber(String number) {
    return Pattern.matches(IS_REAL_NUMBER, number);
  }

  public static boolean isFieldEmpty(String field) {
    return field.isEmpty();
  }
}
