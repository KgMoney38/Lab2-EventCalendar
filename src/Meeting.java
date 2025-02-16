import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Meeting extends Event implements Completable
{
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;

    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location)
    {
        super(name,start);
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
    public boolean isCompleted()
    {
        return false;
    }

    public LocalDateTime getEndTime()
    {
        return this.endDateTime;
    }

    public Duration getDuration()
    {
        return Duration.between(this.endDateTime, LocalDateTime.now());
    }

    public String getLocation()
    {
        return this.location;
    }

    public void setEndTime(LocalDateTime end)
    {
        this.endDateTime = end;
    }

    public void setLocation(String Location)
    {
        this.location = Location;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
