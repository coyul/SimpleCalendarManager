package coyul.ru.simplecalendarmanager.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateFormatUtils {

    private static final String FULL_DATE_FORMAT = "dd.MM.yyyy hh:mm";
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String TIME_FORMAT = "hh:mm";

    public static String getFullDateFromSec(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(FULL_DATE_FORMAT, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String getDateFromCalendar(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getTimeFromCalendar(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
        return simpleDateFormat.format(calendar.getTime());
    }


}
