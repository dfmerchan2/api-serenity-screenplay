package co.com.booker.utilities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConversionUtility {

  public static String getCurrentDateFormatted(String format) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
    return dateTimeFormatter.format(LocalDateTime.now());
  }

  public static String getFutureOrPastDate(String format, int days) {
    Date date = new Date();
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_YEAR, days);
    return new SimpleDateFormat(format).format(calendar.getTime());
  }
}
