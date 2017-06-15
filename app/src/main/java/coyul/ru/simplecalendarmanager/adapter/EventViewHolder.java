package coyul.ru.simplecalendarmanager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import coyul.ru.simplecalendarmanager.model.Event;
import coyul.ru.simplecalendarmanager.R;
import coyul.ru.simplecalendarmanager.helpers.DateFormatUtils;

public class EventViewHolder extends RecyclerView.ViewHolder{

    private TextView mTitleTextView;
    private TextView mLocationTextView;
    private TextView mDescriptionTextView;
    private TextView mTimingTextView;
    public ImageView mPopUpView;


    public EventViewHolder(View itemView) {
        super(itemView);

        mTitleTextView = (TextView) itemView.findViewById(R.id.event_title);
        mLocationTextView = (TextView) itemView.findViewById(R.id.event_location);
        mDescriptionTextView = (TextView) itemView.findViewById(R.id.event_description);
        mTimingTextView = (TextView) itemView.findViewById(R.id.event_timing);
        mPopUpView = (ImageView) itemView.findViewById(R.id.popup_view);
    }

    public void bindView(Event event) {
        mTitleTextView.setText(event.getTitle());
        mLocationTextView.setText(event.getLocation());
        mDescriptionTextView.setText(event.getDescription());
        mTimingTextView.setText(mTimingTextView.getContext()
                .getString(R.string.two_dates_format,
                DateFormatUtils.getFullDateFromSec(event.getStart()),
                DateFormatUtils.getFullDateFromSec(event.getEnd())));
    }
}
