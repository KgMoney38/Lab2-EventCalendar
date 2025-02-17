import java.time.LocalDateTime;

abstract class Event implements Comparable<Event>
{
    protected String name;
    protected LocalDateTime dateTime;
    protected boolean isHoliday;

    public Event(String name, LocalDateTime dateTime, boolean isHoliday)
    {
        this.name = name;
        this.dateTime = dateTime;
        this.isHoliday = isHoliday;
    }

    public abstract String getName();

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isHoliday()
    {
        return isHoliday;
    }

    @Override
    public int compareTo(Event e)
    {
        return this.dateTime.compareTo(e.getDateTime());
    }
}
