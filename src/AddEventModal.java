import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class AddEventModal extends JDialog
{
    public AddEventModal(EventListPanel parent)
    {
        setTitle("Add Event");
        setSize(400, 450);
        setLayout(new GridLayout(10,2));

        JTextField nameField = new JTextField();
        JTextField locationField = new JTextField();
        JCheckBox isMeetingBox = new JCheckBox("Meeting?");
        JCheckBox isHolidayBox = new JCheckBox("Holiday (All Day Event)?");

        String[] years = {"2025", "2026", "2027", "2028", "2029"};
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] days = new String[31];

        for(int i = 1; i <= 31; i++) {
            days[i - 1] = String.valueOf(i);
        }

        //Generated this list of numbers online because its straight forward but a pain to type
        String[] hours = new String[] {"12:00 AM", "1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM",
            "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM", "10:00 PM", "11:00 PM"};


        //Generated this list of numbers online because its straight forward but a pain to type
        String[] minutes = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
                "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

        JComboBox<String> yearBox = new JComboBox<>(years);
        JComboBox<String> monthBox = new JComboBox<>(months);
        JComboBox<String> dayBox = new JComboBox<>(days);
        JComboBox<String> hourBox = new JComboBox<>(hours);
        JComboBox<String> minuteBox = new JComboBox<>(minutes);

        //Kept getting -1 so had to add these to avoid exception
        yearBox.setSelectedIndex(0);
        monthBox.setSelectedIndex(0);
        dayBox.setSelectedIndex(0);
        hourBox.setSelectedIndex(0);
        minuteBox.setSelectedIndex(0);

        add(new JLabel("Event Name:"));
        add(nameField);
        add(new JLabel("Location (if meeting):"));
        add(locationField);
        add(new JLabel ("Year:"));
        add(yearBox);
        add(new JLabel ("Month:"));
        add(monthBox);
        add(new JLabel ("Day:"));
        add(dayBox);
        add(new JLabel ("Hour:"));
        add(hourBox);
        add(new JLabel ("Minute:"));
        add(minuteBox);

        add(isMeetingBox);
        add(isHolidayBox);

        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(e-> {
            int year = Integer.parseInt((String)yearBox.getSelectedItem());
            int month = Integer.parseInt((String)monthBox.getSelectedItem());
            int day = Integer.parseInt((String)dayBox.getSelectedItem());
            int hour = Integer.parseInt((String)hourBox.getSelectedItem());
            int minute = Integer.parseInt((String)minuteBox.getSelectedItem());

            LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
            boolean isHoliday = isHolidayBox.isSelected();

            if(isHoliday)
            {
                dateTime= dateTime.withHour(0).withMinute(0);
            }

            if (isMeetingBox.isSelected())
            {
                parent.addEvent(new Meeting(nameField.getText(),dateTime,dateTime.plusHours(1), locationField.getText(),isHoliday));
            }
            else
            {
                parent.addEvent(new Deadline(nameField.getText(),dateTime, isHoliday));
            }
            dispose();
        });

        add(addButton);

        setVisible(true);
    }
}
