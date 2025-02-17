import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventListPanel extends JPanel
{
    private ArrayList<Event> events;
    private JPanel displayPanel;

    public EventListPanel()
    {
        events = new ArrayList<>();
        displayPanel = new JPanel();
        setLayout(new BorderLayout());
        add(displayPanel, BorderLayout.CENTER);
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        JButton addButton= new JButton("Add Event");
        //Not really sure why I had to add "this" but it was the only way to get it to work properly research this issue
        addButton.addActionListener(e -> new AddEventModal(this));
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
            displayPanel.add(new EventPanel(event));
        }
        revalidate();
        repaint();
    }
}
