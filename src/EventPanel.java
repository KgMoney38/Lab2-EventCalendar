import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class EventPanel extends JPanel
{
        private Event event;
        private JButton completeButton;

        public EventPanel(Event event) {
            this.event = event;
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(event.getName()));

            JPanel infoPanel = new JPanel(new GridLayout(4, 1));
            infoPanel.add(new JLabel("Date:" +event.getDateTime()));

            if (event instanceof Meeting)
            {
                infoPanel.add(new JLabel("End Time: " + ((Meeting) event).getEndDateTime()));
                infoPanel.add(new JLabel("Location: " + ((Meeting) event).getLocation()));
            }

            if(event.isHoliday())
            {
                infoPanel.add(new JLabel("Holiday: YES (ALL DAY EVENT)"));
            }

            add(infoPanel, BorderLayout.CENTER);
            updateUrgency(infoPanel);

            if (event instanceof Completable)
            {
                completeButton = new JButton("Complete");
                completeButton.addActionListener(e -> {
                    ((Completable) event).complete();
                    completeButton.setEnabled(false);
                });
                add(completeButton, BorderLayout.SOUTH);
            }
        }

        public void updateUrgency(JPanel infoPanel)
        {
            LocalDateTime now = LocalDateTime.now();
            long daysUntil = ChronoUnit.DAYS.between(now, event.getDateTime());

            JLabel daysUntilLabel = new JLabel("Days until event: " + daysUntil);
            JLabel dueSoon= new JLabel("SOON!");
            JLabel duePast= new JLabel("OVERDUE!!!");
            JLabel dueEventually= new JLabel("You Have Time!");



            if(daysUntil < 0)
            {
                setBackground(Color.red);
                infoPanel.add(duePast);
                infoPanel.add(daysUntilLabel);
            }
            else if (daysUntil <= 3)
            {
                setBackground(Color.yellow);
                infoPanel.add(dueSoon);
                infoPanel.add(daysUntilLabel);

            }
            else
            {
                setBackground(Color.green);
                infoPanel.add(dueEventually);
                infoPanel.add(daysUntilLabel);
            }
        }
}
