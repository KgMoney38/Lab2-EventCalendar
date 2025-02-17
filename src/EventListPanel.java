import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class EventListPanel extends JPanel
{
    private ArrayList<Event> events;
    private JPanel displayPanel;
    private JComboBox<String> sortDropDown;
    private JCheckBox filterCompleted;

    public EventListPanel()
    {
        events = new ArrayList<>();
        displayPanel = new JPanel();
        setLayout(new BorderLayout());

        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        sortDropDown = new JComboBox<>(new String[]{"None", "By Name", "By Date"});
        sortDropDown.addActionListener(e -> sortEvents());
        filterCompleted = new JCheckBox("Filter completed");
        filterCompleted.addActionListener(e -> updateDisplay());

        controlPanel.add(sortDropDown);
        controlPanel.add(filterCompleted);

        JButton addButton= new JButton("Add Event");
        //Not really sure why I had to add "this" but it was the only way to get it to work properly research this issue
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

        for(Event event : events)
        {
            if(filterCompleted.isSelected() && event instanceof Completable && ((Completable) event).isComplete())
            {
                continue;
            }
            displayPanel.add(new EventPanel(event));
        }
        revalidate();
        repaint();
    }

    public void sortEvents()
    {
        if(sortDropDown.getSelectedIndex() == 1)
        {
            events.sort((a,b) -> a.getName().compareToIgnoreCase(b.getName()));
        }else if (sortDropDown.getSelectedIndex() == 2)
        {
            Collections.sort(events);
        }
        else
        {
            //No sort
        }
        updateDisplay();
    }
}
