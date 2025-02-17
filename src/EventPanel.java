import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel
{
        private Event event;
        private JButton completeButton;

        public EventPanel(Event event) {
            this.event = event;
            setLayout(new GridLayout(2, 1));
            add(new JLabel("Event: " + event.getName()));
            add(new JLabel("Date: " + event.getDateTime()));

            if (event instanceof Completable) {
                completeButton = new JButton("Complete");
                completeButton.addActionListener(e -> {
                    ((Completable) event).complete();
                    completeButton.setEnabled(false);
                });
                add(completeButton);
            }
        }
}
