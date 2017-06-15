package coyul.ru.simplecalendarmanager.helpers;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import coyul.ru.simplecalendarmanager.model.Event;


public class EventDBHelper {

    private ContentResolver mContentResolver;
    private static final String TAG = "EventDBHelper";
    private static final Uri CALENDAR_CONTRACT_URI = CalendarContract.Events.CONTENT_URI;

    public EventDBHelper(Context context) {
        mContentResolver = context.getContentResolver();
    }

    @SuppressWarnings("MissingPermission")
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sortOrder = CalendarContract.Events.DTSTART + " DESC";
        Cursor cursor = mContentResolver.query(CALENDAR_CONTRACT_URI, null, null, null, sortOrder);
        if (cursor != null) {
            EventInflater.fillList(cursor, events);
            cursor.close();
        } else {
            Log.e(TAG, "cursor is null");
        }
        return events;
    }

    public Event getSingleEvent(long id) {
        Event result = null;
        String selection = CalendarContract.Events._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = mContentResolver.query(CALENDAR_CONTRACT_URI, null, selection, selectionArgs, null);

        if (cursor != null) {
            cursor.moveToFirst();
            result = EventInflater.createEventFromCursor(cursor);
            Log.e(TAG, "result " + result.toString());
            cursor.close();
        } else {
            Log.e(TAG, "cursor is null");
        }
        return result;
    }

    public void updateEvent(Event event) {
        ContentValues values = EventInflater.createValuesFromEvent(event);
        String where = CalendarContract.Events._ID + "=?";
        String[] selectionArgs = {String.valueOf(event.getId())};

        int result = mContentResolver.update(CALENDAR_CONTRACT_URI, values, where, selectionArgs);
        Log.e(TAG, "Updated event with id " + selectionArgs[0] + " result = " + result);
    }

    public void insertEvent(Event event) {
        Log.e(TAG, "inserting event " + event.toString());
        ContentValues values = EventInflater.createValuesFromEvent(event);
        Uri result = mContentResolver.insert(CALENDAR_CONTRACT_URI, values);
        Log.e(TAG, "Inserted row with uri " + result);
    }

    @SuppressWarnings("MissingPermission")
    public void deleteEvent(Event event) {
        String whereClause = CalendarContract.Events._ID + "=?";
        String[] whereArgs = {String.valueOf(event.getId())};

        int result = mContentResolver.delete(CALENDAR_CONTRACT_URI, whereClause, whereArgs);
        Log.e(TAG, "deleted event with id " + whereArgs[0] + " result = " + result);
    }

}
