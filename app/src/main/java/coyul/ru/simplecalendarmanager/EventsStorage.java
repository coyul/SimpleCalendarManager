package coyul.ru.simplecalendarmanager;

import java.util.List;

import coyul.ru.simplecalendarmanager.helpers.EventDBHelper;
import coyul.ru.simplecalendarmanager.model.Event;

public class EventsStorage {

    private final EventDBHelper mEventDBHelper;


    public EventsStorage(EventDBHelper dbHelper) {
        mEventDBHelper = dbHelper;
    }

    public List<Event> getEvents() {
        return mEventDBHelper.getAllEvents();
    }

    public Event getEvent(long id) {
        return mEventDBHelper.getSingleEvent(id);
    }

    public void addEvent(Event event) {
        mEventDBHelper.insertEvent(event);

    }

    public void deleteEvent(Event event) {
        mEventDBHelper.deleteEvent(event);
    }

    public void updateEvent(Event event) {
        mEventDBHelper.updateEvent(event);
    }
}
