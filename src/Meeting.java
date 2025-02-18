import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
//Few comments but mostly straight forward
//File complete

public class Meeting extends Event implements Completable
{
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;

    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location)
    {
        //Added isHoliday for the panel when creating an event
        this(name,start,end,location,false);
    }

    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location, boolean isHoliday)
    {
        //Super to call parent class constructor
        super(name,start, isHoliday);
        this.endDateTime = end;
        this.location = location;
        this.complete = false;

    }

    @Override
    public void complete()
    {
        this.complete = true;
    }

    @Override
    public boolean isComplete()
    {
        return complete;
    }


    public LocalDateTime getEndDateTime()
    {
        return endDateTime;
    }

    public Duration getDuration()
    {
        return Duration.between(dateTime, endDateTime);
    }

    public String getLocation()
    {
        return location;
    }

    public void setEndDateTime(LocalDateTime end)
    {
        this.endDateTime = end;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
