package coyul.ru.simplecalendarmanager.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.TimeZone;

import coyul.ru.simplecalendarmanager.helpers.DateFormatUtils;
import coyul.ru.simplecalendarmanager.model.Event;
import coyul.ru.simplecalendarmanager.EventsStorage;
import coyul.ru.simplecalendarmanager.R;

public abstract class EventsInputActivity extends AppCompatActivity implements View.OnClickListener {

    protected static final int TIME_DELTA = 1;
    protected static final int CALENDAR_DEFAULT_ID = 5;

    protected EventsStorage mEventsStorage;

    protected TextInputLayout mTitleTextInput;
    protected EditText mTitleEditText;
    protected TextInputLayout mLocationTextInput;
    protected EditText mLocationEditText;
    protected TextInputLayout mDescriptionTextInput;
    protected EditText mDescriptionEditText;

    protected EditText mDateStartEditText;
    protected EditText mTimeStartEditText;
    protected EditText mDateEndEditText;
    protected EditText mTimeEndEditText;

    protected Button mOnChangeButton;
    protected View.OnClickListener mOnButtonClickListener;
    protected Calendar mStartCalendar;
    protected Calendar mEndCalendar;

    protected abstract void setUpCalendars();
    protected abstract void setUpTextInViews();
    protected abstract void setButtonClickListener();


    protected void onCreateActivity() {
        setContentView(R.layout.activity_input);
        setButtonClickListener();
        setUpViews();
        setUpCalendars();
        setTextInDateFields();
        setUpTextInViews();
    }

    protected void setUpViews() {
        mTitleTextInput = (TextInputLayout) findViewById(R.id.title_text_input);
        mTitleEditText = (EditText) findViewById(R.id.title_edit_text);
        mLocationTextInput = (TextInputLayout) findViewById(R.id.location_text_input);
        mLocationEditText = (EditText) findViewById(R.id.location_edit_text);
        mDescriptionTextInput = (TextInputLayout) findViewById(R.id.description_text_input);
        mDescriptionEditText = (EditText) findViewById(R.id.description_edit_text);
        mDateStartEditText = (EditText) findViewById(R.id.date_start_edit_text);
        mTimeStartEditText = (EditText) findViewById(R.id.time_start_edit_text);
        mDateEndEditText = (EditText) findViewById(R.id.date_end_edit_text);
        mTimeEndEditText = (EditText) findViewById(R.id.time_end_edit_text);

        mOnChangeButton = (Button) findViewById(R.id.confirm_button);
        mOnChangeButton.setOnClickListener(mOnButtonClickListener);

        mDateStartEditText.setOnClickListener(this);
        mTimeStartEditText.setOnClickListener(this);
        mDateEndEditText.setOnClickListener(this);
        mTimeEndEditText.setOnClickListener(this);

        mTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(mTitleEditText.getText())) {
                    mOnChangeButton.setEnabled(true);
                }
            }
        });
    }

    protected void setTextInDateFields() {
        mDateStartEditText.setText(DateFormatUtils.getDateFromCalendar(mStartCalendar));
        mDateEndEditText.setText(DateFormatUtils.getDateFromCalendar(mEndCalendar));
        mTimeStartEditText.setText(DateFormatUtils.getTimeFromCalendar(mStartCalendar));
        mTimeEndEditText.setText(DateFormatUtils.getTimeFromCalendar(mEndCalendar));
    }

    protected Event getEventFromCurrentData() {
        Event event = new Event();
        event.setCalendarId(CALENDAR_DEFAULT_ID);
        event.setTitle(mTitleEditText.getText().toString());
        event.setLocation(mLocationEditText.getText().toString());
        event.setDescription(mDescriptionEditText.getText().toString());
        event.setStart(mStartCalendar.getTimeInMillis());
        event.setEnd(mEndCalendar.getTimeInMillis());
        event.setTimeZone(TimeZone.getDefault().getID());
        return event;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date_start_edit_text:
                new DatePickerDialog(this,
                        new startDateListener(),
                        mStartCalendar.get(Calendar.YEAR),
                        mStartCalendar.get(Calendar.MONTH),
                        mStartCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;

            case R.id.time_start_edit_text:
                new TimePickerDialog(this,
                        new startTimeListener(),
                        mStartCalendar.get(Calendar.HOUR_OF_DAY),
                        mStartCalendar.get(Calendar.MINUTE), false)
                        .show();
                break;

            case R.id.date_end_edit_text:
                new DatePickerDialog(this,
                        new endDateListener(),
                        mEndCalendar.get(Calendar.YEAR),
                        mEndCalendar.get(Calendar.MONTH),
                        mEndCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;

            case R.id.time_end_edit_text:
                new TimePickerDialog(this,
                        new endTimeListener(),
                        mEndCalendar.get(Calendar.HOUR_OF_DAY),
                        mEndCalendar.get(Calendar.MINUTE), false)
                        .show();
                break;
        }
    }


    private class startDateListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            mStartCalendar.set(Calendar.YEAR, year);
            mStartCalendar.set(Calendar.MONTH, month);
            mStartCalendar.set(Calendar.DAY_OF_MONTH, day);
            mDateStartEditText.setText((DateFormatUtils.getDateFromCalendar(mStartCalendar)));

            mEndCalendar.set(Calendar.YEAR, year);
            mEndCalendar.set(Calendar.MONTH, month);
            mEndCalendar.set(Calendar.DAY_OF_MONTH, day);
            mDateEndEditText.setText((DateFormatUtils.getDateFromCalendar(mEndCalendar)));
        }

    }

    private class endDateListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            mEndCalendar.set(Calendar.YEAR, year);
            mEndCalendar.set(Calendar.MONTH, month);
            mEndCalendar.set(Calendar.DAY_OF_MONTH, day);

            mDateEndEditText.setText((DateFormatUtils.getDateFromCalendar(mEndCalendar)));
        }

    }

    private class startTimeListener implements TimePickerDialog.OnTimeSetListener {

        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            mStartCalendar.set(Calendar.HOUR_OF_DAY, hour);
            mStartCalendar.set(Calendar.MINUTE, minute);
            mTimeStartEditText.setText(DateFormatUtils.getTimeFromCalendar(mStartCalendar));

            mEndCalendar.set(Calendar.HOUR_OF_DAY, hour);
            mEndCalendar.add(Calendar.HOUR_OF_DAY, TIME_DELTA);
            mEndCalendar.set(Calendar.MINUTE, minute);
            mTimeEndEditText.setText(DateFormatUtils.getTimeFromCalendar(mEndCalendar));
        }
    }

    private class endTimeListener implements TimePickerDialog.OnTimeSetListener {

        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            mEndCalendar.set(Calendar.HOUR_OF_DAY, hour);
            mEndCalendar.set(Calendar.MINUTE, minute);

            mTimeEndEditText.setText(DateFormatUtils.getTimeFromCalendar(mEndCalendar));
        }
    }


}
