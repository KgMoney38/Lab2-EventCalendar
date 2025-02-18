import javax.swing.*;
import java.awt.*;

//Basic main like class file, no comments needed
//File complete
public class EventPlanner
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Event Planner");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        EventListPanel eventListPanel = new EventListPanel();
        frame.add(eventListPanel);

        frame.setVisible(true);
    }
}