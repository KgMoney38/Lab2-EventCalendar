import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class AddEventModal extends JDialog
{
    public AddEventModal(EventListPanel parent)
    {
        setTitle("Add Event");
        setSize(300, 200);
        setLayout(new GridLayout(4,1));

        JTextField nameField = new JTextField();
        add(new JLabel("Event Name:"));
        add(nameField);

        JButton addButton = new JButton("Add Deadline");
        addButton.addActionListener(e->
        {
            parent.addEvent(new Deadline(nameField.getText(), LocalDateTime.now().plusDays(5)));
            dispose();
        });
        add(addButton);

        setVisible(true);
    }
}
