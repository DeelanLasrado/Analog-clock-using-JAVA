import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;


public class ClockDisplay extends JFrame {
    private StillClock clock;
    private JLabel timeLabel;

    public ClockDisplay() {
        // Create the still clock and label
        clock = new StillClock();
        timeLabel = new JLabel(getTimeString());
        timeLabel.setFont(new Font("Serif", Font.BOLD, 30));

        // Create a panel to hold the clock and label
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(clock, BorderLayout.CENTER);
        panel.add(timeLabel, BorderLayout.SOUTH);

        // Add the panel to the frame
        add(panel);

        // Create a timer to update the clock and label every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the clock and label with the current time
                clock.setCurrentTime();
                timeLabel.setText(getTimeString());
                timeLabel.setHorizontalAlignment(JLabel.CENTER);
            }
        });

        // Start the timer
        timer.start();

        // Set the frame properties
        setTitle("Clock Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Helper method to get the current time as a string
    private String getTimeString() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public static void main(String[] args) {
        // Create the clock display
        new ClockDisplay();
    }
}

