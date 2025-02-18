import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

//File Complete

public class EventListPanel extends JPanel
{
    //Drop down menu for my filters
    private FilterDropDown filterDropDown;

    //List of my added events
    private ArrayList<Event> events;

    private JPanel displayPanel;
    private JComboBox<String> sortDropDown;

    //Used this to determine what filters were selected
    private int currentFilter= 0;

    public EventListPanel() {
        events = new ArrayList<>();
        displayPanel = new JPanel();
        setLayout(new BorderLayout());

        //Looks confusing but this is just all of my layout and design preferences for the initial displayPanel
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);
        displayPanel.setBackground(Color.darkGray);
        JLabel emptyLabel = new JLabel("No events found: GET TO PLANNING!");
        emptyLabel.setForeground(Color.RED);
        emptyLabel.setFont(new Font("Serif", Font.BOLD, 45));
        displayPanel.add(emptyLabel);

        //Panel to display all of my event information
        JPanel controlPanel = new JPanel();

        //Sort dropdown menu
        JLabel sortLabel = new JLabel("Sort by:");
        String[] sortOptions = {"None", "By Ascending Name", "By Date", "By Descending Name"};
        sortDropDown = new JComboBox<>(sortOptions);
        sortDropDown.addActionListener(e -> sortEvents());

        //Filter dropdown menu
        JLabel filterLabel = new JLabel("Filter By:");
        filterDropDown= new FilterDropDown("Filter Options", this::updateDisplay);

        //Add to my controlPanel
        controlPanel.add(sortLabel);
        controlPanel.add(sortDropDown);
        controlPanel.add(filterLabel);
        controlPanel.add(filterDropDown);

        //Button to add an event
        JButton addButton= new JButton("Add Event");

        //Not sure why I had to add "this" but Intelli J recommended it and it worked properly, research this
        addButton.addActionListener(e -> new AddEventModal(this));
        add(controlPanel, BorderLayout.NORTH);
        add(addButton, BorderLayout.SOUTH);
    }

    public void addEvent (Event event)
    {
        events.add(event);
        updateDisplay();
    }

    public void updateDisplay()
    {
        displayPanel.removeAll();

        //For loop to determine which filters are on
        for(Event event : events)
        {
            boolean shouldDisplay= true;

            if(filterDropDown.isCompletedFilter() && event instanceof Completable && ((Completable) event).isComplete())
            {
                shouldDisplay= false;
            }
            else if (filterDropDown.isDeadlinesFiltered() && event instanceof Deadline)
            {
                shouldDisplay= false;
            }
            else if (filterDropDown.isMeetingsFiltered() && event instanceof Meeting)
            {
                shouldDisplay= false;
            }

            //Sets the display accordingly
            if(shouldDisplay)
            {
                displayPanel.add(new EventPanel(event));
            }
        }
        revalidate();
        repaint();
    }

    public void sortEvents()
    {
        int selectedIndex= sortDropDown.getSelectedIndex();

        //Determine how to sort based on user selection
        if(selectedIndex== 1)
        {
            //A-Z
            events.sort((a,b) -> a.getName().compareToIgnoreCase(b.getName()));
        }
        else if (selectedIndex == 2)
        {
            //By Date
            Collections.sort(events);
        }
        else if (selectedIndex == 3)
        {
            //Z-A
            events.sort((a,b) -> b.getName().compareToIgnoreCase(a.getName()));
        }
        else
        {
            //Date by default
            Collections.sort(events);

        }
        updateDisplay();
    }
}
