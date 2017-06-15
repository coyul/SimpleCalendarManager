package coyul.ru.simplecalendarmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import coyul.ru.simplecalendarmanager.model.Event;
import coyul.ru.simplecalendarmanager.EventsStorage;
import coyul.ru.simplecalendarmanager.R;
import coyul.ru.simplecalendarmanager.activities.UpdateEventActivity;


public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private EventsStorage mEventsStorage;
    private List<Event> mEvents;
    private static String TAG = "EventAdapter";

    public EventAdapter(EventsStorage storage) {
        mEvents = new ArrayList<>();
        mEventsStorage = storage;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, final int position) {
        Event event = mEvents.get(position);
        holder.bindView(event);
        holder.mPopUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopUpMenu(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    @Override
    public long getItemId(int position) {
        return mEvents.get(position).getId();
    }

    public void setUpAdapter(List<Event> calls) {
        mEvents.clear();
        mEvents.addAll(calls);
        notifyDataSetChanged();
    }

    private void createPopUpMenu(View view, final int position) {
        final Context context = view.getContext();

        PopupMenu popup = new PopupMenu(context, view);
        popup.getMenuInflater().inflate(R.menu.event_popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_update:
                        Intent intent = UpdateEventActivity.newIntent(context);
                        intent.putExtra("eventID", mEvents.get(position).getId());
                        context.startActivity(intent);
                        break;
                    case R.id.menu_delete:
                        mEventsStorage.deleteEvent(mEvents.get(position));
                        break;
                }
                return false;
            }
        });
        popup.show();
    }


}
