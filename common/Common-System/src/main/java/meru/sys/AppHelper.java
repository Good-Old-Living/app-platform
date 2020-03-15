package meru.sys;

import java.util.regex.Pattern;

public class AppHelper {

  private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
  private static final String MOBILE_NO_REGEX = "\\d{10}";

  private static Pattern emailPattern = Pattern.compile(EMAIL_REGEX,
                                                        Pattern.CASE_INSENSITIVE);
  private static Pattern mobileNoPattern = Pattern.compile(MOBILE_NO_REGEX);

  public static boolean isValidEmail(String email) {
    return emailPattern.matcher(email).matches();
  }

  public static boolean isValidMobileNumber(String mobileNumber) {
    return mobileNoPattern.matcher(mobileNumber).matches();
  }

  public static void main(String[] args) {
    
    System.out.println(isValidMobileNumber("9843919333"));
    
  }
}
