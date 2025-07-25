import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SimpleCalendar extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton[] buttons;
    private JLabel headerLabel;
    private int currentMonth;
    private int currentYear;

    public SimpleCalendar() {
        setTitle("Simple Calendar");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        currentYear = Calendar.getInstance().get(Calendar.YEAR);

        // Create the header label
        headerLabel = new JLabel();
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create header panel with navigation buttons
        JPanel headerPanel = new JPanel(new BorderLayout());
        JButton prevButton = new JButton("<");
        prevButton.addActionListener(e -> {
            currentMonth--;
            if (currentMonth < 0) {
                currentMonth = 11;
                currentYear--;
            }
            updateCalendar();
        });

        JButton nextButton = new JButton(">");
        nextButton.addActionListener(e -> {
            currentMonth++;
            if (currentMonth > 11) {
                currentMonth = 0;
                currentYear++;
            }
            updateCalendar();
        });

        headerPanel.add(prevButton, BorderLayout.WEST);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);

        // Create calendar panel with grid layout
        JPanel calendarPanel = new JPanel(new GridLayout(0, 7)); // 7 columns (days of the week)

        // Initialize buttons array
        buttons = new JButton[42]; // maximum 42 days in a month (6 rows for weeks)

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setForeground(Color.BLACK);
            calendarPanel.add(buttons[i]);
        }

        // Add panels to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);

        // Update calendar display
        updateCalendar();
    }

    private void updateCalendar() {
        // Get the calendar instance for the current month and year
        Calendar calendar = new GregorianCalendar(currentYear, currentMonth, 1);
        int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK); // Sunday=1, Saturday=7
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Set the header with current month and year
        String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        headerLabel.setText(monthName + " " + currentYear);

        // Clear all previous buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(false);
        }

        // Fill the calendar days
        for (int i = 0; i < daysInMonth; i++) {
            int buttonIndex = firstDayOfMonth + i - 1;
            buttons[buttonIndex].setText(String.valueOf(i + 1));
            buttons[buttonIndex].setEnabled(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleCalendar calendar = new SimpleCalendar();
            calendar.setVisible(true);
        });
    }
}

//Copyright Novaa
//25-07-2025