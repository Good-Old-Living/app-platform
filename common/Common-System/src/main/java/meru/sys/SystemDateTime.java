package meru.sys;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SystemDateTime {

  private static final String USER_DATE_TIME_FORMAT = "dd-MM-yyyy hh:mm:ss a";
  private static final String USER_TIMEZONE_OFFSET = " +05:30";

  private static final DateTimeFormatter USER_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(USER_DATE_TIME_FORMAT);
  private static final DateTimeFormatter USER_DATE_TIME_FORMATTER_WITH_TZ = DateTimeFormatter.ofPattern(USER_DATE_TIME_FORMAT
      + " z");

  private static final ZoneId USER_TIMEZONE = ZoneId.of("Asia/Kolkata");

  public static boolean isPast(Calendar calendar,
                               Calendar calendarToBeCompared) {

    ZonedDateTime zdateTime = ZonedDateTime.ofInstant(calendar.toInstant(),
                                                      ZoneId.systemDefault());
    ZonedDateTime czdateTime = ZonedDateTime.ofInstant(calendarToBeCompared.toInstant(),
                                                       ZoneId.systemDefault());

    return zdateTime.isAfter(czdateTime);

  }

  public static String serialize(Calendar calendar) {

    ZonedDateTime zdateTime = ZonedDateTime.ofInstant(calendar.toInstant(),
                                                      ZoneId.systemDefault());

    ZonedDateTime czdateTime = zdateTime.withZoneSameInstant(USER_TIMEZONE);

    return USER_DATE_TIME_FORMATTER.format(czdateTime);

  }

  public static ZonedDateTime getUserTZDateTime(Calendar calendar) {

    ZonedDateTime zdateTime = ZonedDateTime.ofInstant(calendar.toInstant(),
                                                      ZoneId.systemDefault());

    ZonedDateTime czdateTime = zdateTime.withZoneSameInstant(USER_TIMEZONE);

    return czdateTime;

  }
  
  public static ZonedDateTime getCurrentUserTZDateTime() {

    ZonedDateTime zdateTime = ZonedDateTime.now();
    
    ZonedDateTime czdateTime = zdateTime.withZoneSameInstant(USER_TIMEZONE);

    return czdateTime;

  }

  public static final Calendar parseDateTime(String dateTimeStr) {

    dateTimeStr = dateTimeStr.trim();
    ZonedDateTime dateTime = ZonedDateTime.parse(dateTimeStr + USER_TIMEZONE_OFFSET,
                                                 USER_DATE_TIME_FORMATTER_WITH_TZ);

    return GregorianCalendar.from(dateTime);
  }

}
