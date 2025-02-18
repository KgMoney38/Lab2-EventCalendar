import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class EventPanel extends JPanel
{
        private Event event;

        //Used to mark tasks as completed
        private JButton completeButton;

        //Label to show when an item is completed. NOTE: I also left the completed option for
        //the Holidays even tho holidays are never ending, because my Holidays are also attached to a year.
        private JLabel completedLabel;

        public EventPanel(Event event) {

            //Layout for each event panel
            this.event = event;
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder(event.getName()));
            setBackground(Color.DARK_GRAY);

            //Panel for all my information for events to be displayed on
            JPanel infoPanel = new JPanel(new GridLayout(4, 1));

            //add Date to my panel
            infoPanel.add(new JLabel("Date:" +event.getDateTime()));

            //Background color for panel
            infoPanel.setBackground(Color.lightGray);

            //Label for completion status hidden until I click it
            completedLabel = new JLabel("->Completed<-");
            completedLabel.setForeground(Color.BLUE);
            completedLabel.setVisible(event instanceof Completable &&((Completable)event).isComplete());

            //For Meeting only
            if (event instanceof Meeting)
            {
                infoPanel.add(new JLabel("End Time: " + ((Meeting) event).getEndDateTime()));
                infoPanel.add(new JLabel("Location: " + ((Meeting) event).getLocation()));
            }

            //For Holiday only
            if(event.isHoliday())
            {
                infoPanel.add(new JLabel("Holiday: YES (ALL DAY EVENT)"));
            }

            add(infoPanel, BorderLayout.CENTER);
            updateUrgency(infoPanel);

            //If my complete button is clicked show my blue label marking the event complete
            if (event instanceof Completable)
            {
                completeButton = new JButton("Complete");
                completeButton.addActionListener(e -> {
                    ((Completable) event).complete();
                    completeButton.setEnabled(false);
                    completedLabel.setVisible(true);
                });
                add(completeButton, BorderLayout.SOUTH);
            }
            add(completedLabel, BorderLayout.EAST);
        }

        public void updateUrgency(JPanel infoPanel)
        {
            LocalDateTime now = LocalDateTime.now();

            //Used this to determine the end time based on the length of my meeting in minutes
            long daysUntil = ChronoUnit.DAYS.between(now, event.getDateTime());

            //More Labels for my events
            JLabel daysUntilLabel = new JLabel("Full Days until event: " + daysUntil);
            JLabel dueSoon= new JLabel("SOON!");;
            JLabel duePast= new JLabel("PAST!!!");
            JLabel dueEventually= new JLabel("You Have Time");


            //If past set info panel accordingly
            if(daysUntil < 0)
            {
                setBackground(Color.red);
                infoPanel.add(duePast);
                infoPanel.add(daysUntilLabel);
                duePast.setForeground(Color.red);
            }
            //If soon set info panel accordingly
            else if (daysUntil <= 3)
            {
                setBackground(Color.yellow);
                infoPanel.add(dueSoon);
                infoPanel.add(daysUntilLabel);
                dueSoon.setForeground(Color.yellow);
            }
            //If no time soon set info panel accordingly
            else
            {
                setBackground(Color.green);
                infoPanel.add(dueEventually);
                infoPanel.add(daysUntilLabel);
                dueEventually.setForeground(Color.green);

            }
        }
}
