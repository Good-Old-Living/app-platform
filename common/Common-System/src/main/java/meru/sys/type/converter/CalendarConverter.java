package meru.sys.type.converter;

import java.util.Calendar;

import meru.sys.SystemDateTime;

public class CalendarConverter implements TypeConverter<Calendar> {

    //private SystemCalendar mSystemCalendar = SystemCalendar.getInstance();

    public String serialize(Calendar calendar) {

     return SystemDateTime.serialize(calendar);
     
    /* return mSystemCalendar.toCalendarString(calendar,
                                     mDateTimeFormat);*/
    }

    public Calendar deserialize(String calendarStr) {

      return SystemDateTime.parseDateTime(calendarStr);
//        return mSystemCalendar.parseCalendar(calendarStr,
//                                             mDateTimeFormat);
      
 
    }

}