import javax.swing.*;
//Clear to delete first attempt add comment to everything to ensure git saves them ////
public class EventPlanner
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        EventListPanel eventListPanel = new EventListPanel();
        frame.add(eventListPanel);

        frame.setVisible(true);
    }
}