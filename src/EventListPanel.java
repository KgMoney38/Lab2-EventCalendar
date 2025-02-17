import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

//

public class EventListPanel extends JPanel
{
    private ArrayList<Event> events;
    private JPanel displayPanel;
    private JComboBox<String> sortDropDown;
    private int currentFilter= 0;

    public EventListPanel()
    {
        events = new ArrayList<>();
        displayPanel = new JPanel();
        setLayout(new BorderLayout());

        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();

        //Sort dropdown menu
        JLabel sortLabel = new JLabel("Sort by:");
        String[] sortOptions= {"None", "By Ascending Name", "By Date", "By Descending Name"};
        sortDropDown = new JComboBox<>(sortOptions );
        sortDropDown.addActionListener(e -> sortEvents());

        //Filter dropdown menu
        JLabel filterLabel = new JLabel("Filter By:");
        String[] filterOptions= {"None", "Hide Completed", "Hide Deadlines", "Hide Meetings"};
        JComboBox<String> filterDropdown = new JComboBox<>(filterOptions );
        filterDropdown.addActionListener(e -> updateDisplay(filterDropdown.getSelectedIndex()));

        //Add to my controlPanel
        controlPanel.add(sortLabel);
        controlPanel.add(sortDropDown);
        controlPanel.add(filterLabel);
        controlPanel.add(filterDropdown);

        JButton addButton= new JButton("Add Event");
        //Not really sure why I had to add "this" but it was the only way to get it to work properly research this issue
        addButton.addActionListener(e -> new AddEventModal(this));
        add(controlPanel, BorderLayout.NORTH);
        add(addButton, BorderLayout.SOUTH);
    }

    public void addEvent (Event event)
    {
        events.add(event);
        updateDisplay(events.size());
    }

    public void updateDisplay(int filterOption)
    {
        displayPanel.removeAll();

        for(Event event : events)
        {
            boolean shouldDisplay= true;

            if(currentFilter==1 && event instanceof Completable && ((Completable) event).isComplete())
            {
                shouldDisplay= false;
            }
            else if (currentFilter==2 && event instanceof Deadline)
            {
                shouldDisplay= false;
            }
            else if (currentFilter==3 && event instanceof Meeting)
            {
                shouldDisplay= false;
            }

            if(shouldDisplay) {
                displayPanel.add(new EventPanel(event));
            }
        }
        revalidate();
        repaint();
    }

    public void sortEvents()
    {
        int selectedIndex= sortDropDown.getSelectedIndex();

        if(selectedIndex== 1)
        {
            events.sort((a,b) -> a.getName().compareToIgnoreCase(b.getName()));
        }
        else if (selectedIndex == 2)
        {
            Collections.sort(events);
        }
        else if (selectedIndex == 3)
        {
            events.sort((a,b) -> b.getName().compareToIgnoreCase(a.getName()));
        }
        else
        {
            //Date by default
            Collections.sort(events);

        }
        updateDisplay(currentFilter);
    }
}
