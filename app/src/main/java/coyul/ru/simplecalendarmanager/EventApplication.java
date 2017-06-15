package coyul.ru.simplecalendarmanager;

import android.app.Application;

import coyul.ru.simplecalendarmanager.helpers.EventDBHelper;

public class EventApplication extends Application {

    private EventsStorage mEventsStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        EventDBHelper dbHelper = new EventDBHelper(this);
        mEventsStorage = new EventsStorage(dbHelper);
    }

    public EventsStorage getEventsStorage() {
        return mEventsStorage;
    }
}
