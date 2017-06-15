package coyul.ru.simplecalendarmanager.helpers;


import android.content.ContentValues;
import android.database.Cursor;
import android.provider.CalendarContract;

import java.util.List;

import coyul.ru.simplecalendarmanager.model.Event;

public class EventInflater {

    public static void fillList(Cursor source, List<Event> target) {

        if (source.moveToFirst()) {
            while (!source.isAfterLast()) {
                target.add(createEventFromCursor(source));
                source.moveToNext();
            }
        }
    }

    public static Event createEventFromCursor(Cursor cursor) {
        Event event = new Event();
        event.setId(getLong(cursor, CalendarContract.Events._ID));
        event.setCalendarId(getLong(cursor, CalendarContract.Events.CALENDAR_ID));
        event.setTitle(getString(cursor, CalendarContract.Events.TITLE));
        event.setDescription(getString(cursor, CalendarContract.Events.DESCRIPTION));
        event.setLocation(getString(cursor, CalendarContract.Events.EVENT_LOCATION));
        event.setStart(getLong(cursor, CalendarContract.Events.DTSTART));
        event.setEnd(getLong(cursor, CalendarContract.Events.DTEND));
        event.setTimeZone(getString(cursor, CalendarContract.Events.EVENT_TIMEZONE));
        return event;
    }

    public static ContentValues createValuesFromEvent(Event event) {
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.CALENDAR_ID, event.getCalendarId());
        values.put(CalendarContract.Events.TITLE, event.getTitle());
        values.put(CalendarContract.Events.DESCRIPTION, event.getDescription());
        values.put(CalendarContract.Events.EVENT_LOCATION, event.getLocation());
        values.put(CalendarContract.Events.DTSTART, event.getStart());
        values.put(CalendarContract.Events.DTEND, event.getEnd());
        values.put(CalendarContract.Events.EVENT_TIMEZONE, event.getTimeZone());
        return values;
    }

    private static long getLong(Cursor cursor, String ColumnName) {
        return cursor.getLong(cursor.getColumnIndex(ColumnName));
    }

    public static String getString(Cursor cursor, String ColumnName) {
        return cursor.getString(cursor.getColumnIndex(ColumnName));
    }

    private static int getInt(Cursor cursor, String ColumnName) {
        return cursor.getInt(cursor.getColumnIndex(ColumnName));
    }
}
