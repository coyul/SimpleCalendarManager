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

public class UpdateEventActivity extends EventsInputActivity {

    private static final String TAG = "UpdateEventActivity";
    private Event mUpdatingEvent;

    public static Intent newIntent(Context context) {
        return new Intent(context, UpdateEventActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventsStorage = ((EventApplication) getApplication()).getEventsStorage();
        long id = getIntent().getLongExtra("eventID", 0);
        mUpdatingEvent = mEventsStorage.getEvent(id);
        onCreateActivity();

    }

    @Override
    protected void setUpCalendars() {
        mStartCalendar = Calendar.getInstance();
        mStartCalendar.setTimeInMillis(mUpdatingEvent.getStart());

        mEndCalendar = Calendar.getInstance();
        mEndCalendar.setTimeInMillis(mUpdatingEvent.getEnd());
    }

    @Override
    protected void setUpTextInViews() {
        mOnChangeButton.setText(R.string.update_button_text);
        mTitleEditText.setText(mUpdatingEvent.getTitle());
        mLocationEditText.setText(mUpdatingEvent.getLocation());
        mDescriptionEditText.setText(mUpdatingEvent.getDescription());
    }

    @Override
    protected void setButtonClickListener() {
        mOnButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEvent();
            }
        };
    }

    private void updateEvent() {
        Event newEvent = getEventFromCurrentData();
        newEvent.setId(mUpdatingEvent.getId());
        mEventsStorage.updateEvent(newEvent);
        finish();
    }
}
