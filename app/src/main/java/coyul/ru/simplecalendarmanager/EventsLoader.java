package coyul.ru.simplecalendarmanager;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.CalendarContract;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

import coyul.ru.simplecalendarmanager.model.Event;

public class EventsLoader extends AsyncTaskLoader<List<Event>> {

    private static final String TAG = "EventsLoader";
    private final EventsContentObserver mEventsObserver;
    private EventsStorage mEventsStorage;

    public EventsLoader(Context context, EventsStorage storage) {
        super(context);
        mEventsObserver = new EventsContentObserver();
        this.mEventsStorage = storage;
        context.getContentResolver().registerContentObserver(CalendarContract.Events.CONTENT_URI, false, mEventsObserver);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
        Log.e(TAG, "onStartLoading");
    }

    @Override
    protected void onReset() {
        super.onReset();
        getContext().getContentResolver().unregisterContentObserver(mEventsObserver);
    }

    @Override
    public List<Event> loadInBackground() {
        List<Event> events = mEventsStorage.getEvents();
        Log.e(TAG, "loadInBackground");
        return events;
    }

    private class EventsContentObserver extends ContentObserver {

        public EventsContentObserver() {
            super(new Handler(Looper.getMainLooper()));
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            onContentChanged();
        }
    }
}
