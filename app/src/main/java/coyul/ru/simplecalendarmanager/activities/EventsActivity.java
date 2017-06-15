package coyul.ru.simplecalendarmanager.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import coyul.ru.simplecalendarmanager.model.Event;
import coyul.ru.simplecalendarmanager.adapter.EventAdapter;
import coyul.ru.simplecalendarmanager.EventApplication;
import coyul.ru.simplecalendarmanager.EventsLoader;
import coyul.ru.simplecalendarmanager.EventsStorage;
import coyul.ru.simplecalendarmanager.R;

public class EventsActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "EventsActivity";
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int LOADER_ID = 1;

    private EventsStorage mEventsStorage;
    private RecyclerView mRecyclerView;
    private EventAdapter mEventAdapter;
    private FloatingActionButton mFAB;

    private String [] mCalendarPermissions = {
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        mEventsStorage = ((EventApplication)getApplication()).getEventsStorage();
        setUpViews();
        checkCallLogPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startLoading();
        }
    }

    private void checkCallLogPermission() {
        if (PermissionChecker.checkSelfPermission(EventsActivity.this, mCalendarPermissions[0]) != PermissionChecker.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, mCalendarPermissions, PERMISSION_REQUEST_CODE);
        } else {
            startLoading();
        }
    }

    private void startLoading() {
        getSupportLoaderManager().initLoader(LOADER_ID, null, new EventLoaderCallBack());
    }

    private void setUpViews(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mFAB = (FloatingActionButton) findViewById(R.id.fab);
        mFAB.setOnClickListener(this);

        mEventAdapter = new EventAdapter(mEventsStorage);
        mRecyclerView.setAdapter(mEventAdapter);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    public void onClick(View view) {
        startActivity(AddEventActivity.newIntent(this));
    }

    private class EventLoaderCallBack implements LoaderManager.LoaderCallbacks<List<Event>> {

        @Override
        public Loader<List<Event>> onCreateLoader(int id, Bundle args) {
            return new EventsLoader(EventsActivity.this, mEventsStorage);
        }

        @Override
        public void onLoadFinished(Loader<List<Event>> loader, List<Event> data) {

            mEventAdapter.setUpAdapter(data);
            Log.e(TAG, "on load finished events = " + data);
        }

        @Override
        public void onLoaderReset(Loader<List<Event>> loader) {

        }
    }
}
