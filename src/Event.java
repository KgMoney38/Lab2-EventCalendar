import java.time.LocalDateTime;

//Dont forget abstract, had to come back and add it
//Other than that this is all very straight forward I only typed like 8 lines, the rest came from Intelli J, no comments or notes needed
//File Complete

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
