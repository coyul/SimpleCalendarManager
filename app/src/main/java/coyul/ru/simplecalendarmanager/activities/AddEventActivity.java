package coyul.ru.simplecalendarmanager.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

import coyul.ru.simplecalendarmanager.EventApplication;
import coyul.ru.simplecalendarmanager.model.Event;
import coyul.ru.simplecalendarmanager.R;

public class AddEventActivity extends EventsInputActivity {

    private static final String TAG = "AddEventActivity";

    public static Intent newIntent(Context context) {
        return new Intent(context, AddEventActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventsStorage = ((EventApplication) getApplication()).getEventsStorage();
        onCreateActivity();
    }

    @Override
    protected void setUpCalendars() {
        mStartCalendar = Calendar.getInstance();
        mStartCalendar.set(Calendar.MINUTE, 0);
        mStartCalendar.add(Calendar.HOUR_OF_DAY, TIME_DELTA);

        mEndCalendar = Calendar.getInstance();
        mEndCalendar.set(Calendar.MINUTE, 0);
        mEndCalendar.add(Calendar.HOUR_OF_DAY, 2 * TIME_DELTA);

    }

    @Override
    protected void setUpTextInViews() {
        mOnChangeButton.setText(R.string.add_button_text);
    }

    @Override
    protected void setButtonClickListener() {

        mOnButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEvent();
            }
        };
    }

    private void createEvent() {
        Event event = getEventFromCurrentData();
        Log.e(TAG, "created event " + event.toString());
        mEventsStorage.addEvent(event);
        finish();
    }


}
