package coyul.ru.simplecalendarmanager.model;


public class Event {

    private long mId;
    private long mCalendarId;
    private String mTitle;
    private String mDescription;
    private String mLocation;
    private long mStart;
    private long mEnd;
    private String mTimeZone;


    public long getId() {
        return mId;
    }

    public long getCalendarId() {
        return mCalendarId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getStart() {
        return mStart;
    }

    public long getEnd() {
        return mEnd;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setId(long id) {
        mId = id;
    }

    public void setCalendarId(long calendarId) {
        mCalendarId = calendarId;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public void setStart(long start) {
        mStart = start;
    }

    public void setEnd(long end) {
        mEnd = end;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("mId=").append(mId);
        sb.append(", mCalendarId=").append(mCalendarId);
        sb.append(", mTitle='").append(mTitle).append('\'');
        sb.append(", mDescription='").append(mDescription).append('\'');
        sb.append(", mLocation='").append(mLocation).append('\'');
        sb.append(", mStart=").append(mStart);
        sb.append(", mEnd=").append(mEnd);
        sb.append(", mTimeZone='").append(mTimeZone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
