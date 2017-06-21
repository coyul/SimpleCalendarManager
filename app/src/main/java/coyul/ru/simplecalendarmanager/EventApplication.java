package coyul.ru.simplecalendarmanager;

import android.app.Application;

import coyul.ru.simplecalendarmanager.helpers.EventDBHelper;

public class EventApplication extends Application implements EventsStorageProvider {

    private EventsStorage mEventsStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        EventDBHelper dbHelper = new EventDBHelper(this);
        mEventsStorage = new EventsStorage(dbHelper);
    }

    @Override
    public EventsStorage getEventsStorage() {
        return mEventsStorage;
    }
}
