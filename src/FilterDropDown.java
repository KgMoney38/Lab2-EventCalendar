import javax.swing.*;
import java.awt.event.ActionListener;

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

    filterCompleted = new JCheckBoxMenuItem("Hide Completed");
    filterDeadlines = new JCheckBoxMenuItem("Hide Deadlines");
    filterMeetings = new JCheckBoxMenuItem("Hide Meetings");

    popup.add(filterCompleted);
    popup.add(filterDeadlines);
    popup.add(filterMeetings);

    ActionListener filterListener = e -> updateDisplayFilter.run();
    filterCompleted.addActionListener(filterListener);
    filterDeadlines.addActionListener(filterListener);
    filterMeetings.addActionListener(filterListener);

    this.addActionListener(e -> popup.show(this,0,this.getHeight()));
}

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
