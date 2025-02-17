import java.time.LocalDateTime;
//
class Deadline extends Event implements Completable
{
    //Thank God for Intelli J I only had to type like 2 sentences in this class

    private boolean complete;

    public Deadline(String name, LocalDateTime deadline)
    {
        this(name, deadline, false);
    }
    public Deadline(String name, LocalDateTime deadline, boolean isHoliday)
    {
        super(name, deadline, isHoliday);
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

    @Override
    public String getName()
    {
        return name;
    }

}
