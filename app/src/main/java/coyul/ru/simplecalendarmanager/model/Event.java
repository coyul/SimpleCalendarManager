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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (getId() != event.getId()) return false;
        if (getCalendarId() != event.getCalendarId()) return false;
        if (getStart() != event.getStart()) return false;
        if (getEnd() != event.getEnd()) return false;
        if (getTitle() != null ? !getTitle().equals(event.getTitle()) : event.getTitle() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(event.getDescription()) : event.getDescription() != null)
            return false;
        if (getLocation() != null ? !getLocation().equals(event.getLocation()) : event.getLocation() != null)
            return false;
        return getTimeZone() != null ? getTimeZone().equals(event.getTimeZone()) : event.getTimeZone() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (getCalendarId() ^ (getCalendarId() >>> 32));
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        result = 31 * result + (int) (getStart() ^ (getStart() >>> 32));
        result = 31 * result + (int) (getEnd() ^ (getEnd() >>> 32));
        result = 31 * result + (getTimeZone() != null ? getTimeZone().hashCode() : 0);
        return result;
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
