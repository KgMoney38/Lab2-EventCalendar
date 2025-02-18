import javax.swing.*;
import java.awt.event.ActionListener;

//Complete file

//Class to implement a dropdown checkbox list for my filter options
public class FilterDropDown extends JButton
{
private JPopupMenu popup;

private JCheckBoxMenuItem filterCompleted;
private JCheckBoxMenuItem filterDeadlines;
private JCheckBoxMenuItem filterMeetings;

public FilterDropDown(String buttonText, Runnable updateDisplayFilter)
{
    super(buttonText);
    popup = new JPopupMenu();

    //Filter Options
    filterCompleted = new JCheckBoxMenuItem("Hide Completed");
    filterDeadlines = new JCheckBoxMenuItem("Hide Deadlines");
    filterMeetings = new JCheckBoxMenuItem("Hide Meetings");

    //Add actually add them to the list
    popup.add(filterCompleted);
    popup.add(filterDeadlines);
    popup.add(filterMeetings);

    //Update my display when filter changed
    ActionListener filterListener = e -> updateDisplayFilter.run();
    filterCompleted.addActionListener(filterListener);
    filterDeadlines.addActionListener(filterListener);
    filterMeetings.addActionListener(filterListener);

    //When clicked show my list                       |from here on came from Intelli J; study it
    this.addActionListener(e -> popup.show(this,0,this.getHeight()));
}

//Next three just return selected filters
public boolean isCompletedFilter()
{
    return filterCompleted.isSelected();
}

public boolean isDeadlinesFiltered()
{
    return filterDeadlines.isSelected();
}

public boolean isMeetingsFiltered()
{
    return filterMeetings.isSelected();
}
}
