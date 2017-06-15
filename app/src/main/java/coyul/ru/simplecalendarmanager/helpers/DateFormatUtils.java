package coyul.ru.simplecalendarmanager.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateFormatUtils {

    public static String getFullDateFromSec(long milliSeconds) {
        String format = "dd.MM.yyyy hh:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String getDateFromCalendar(Calendar calendar) {
        String format = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getTimeFromCalendar(Calendar calendar) {
        String format = "hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(calendar.getTime());
    }


}
