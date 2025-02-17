import javax.swing.*;

public class EventPlanner
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        EventListPanel eventListPanel = new EventListPanel();
        frame.add(eventListPanel);

        frame.setVisible(true);
    }
}