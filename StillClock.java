import java.awt.*;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.Timer;

public class StillClock extends Canvas {
    private int hour;
    private int minute;
    private int second;

    public StillClock() {
        setSize(200, 200);
        setCurrentTime();

        Timer timer = new Timer(1000, e -> {
            setCurrentTime();
            repaint();
        });
        timer.start();
    }


    public void setCurrentTime() {
        // Get the current time
        Calendar cal = Calendar.getInstance();
        this.hour = cal.get(Calendar.HOUR);
        this.minute = cal.get(Calendar.MINUTE);
        this.second = cal.get(Calendar.SECOND);
    }

    @Override
    public void paint(Graphics g) {
        // Get the center of the clock
        int x = getWidth() / 2;
        int y = getHeight() / 2;

        // Get the radius of the clock
        int radius = (int) (Math.min(x, y) * 0.8);

        // Draw the clock face
        g.setColor(Color.pink);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.setColor(Color.BLACK);

        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);

//        int borderThickness = 5;
//        g.drawOval(x - radius + borderThickness, y - radius + borderThickness,
//                2 * (radius - borderThickness), 2 * (radius - borderThickness));

        Font font = new Font("Arial", Font.BOLD, 18);
        g.setFont(font);

        // Draw the hour marks
        for (int i = 1; i <= 12; i++) {
            int markLength = (int) (radius * 0.75);
            int markX = (int) (x + markLength * Math.sin(Math.toRadians(30 * i)));
            int markY = (int) (y - markLength * Math.cos(Math.toRadians(30 * i)));
            g.drawString(Integer.toString(i), markX, markY);
        }

//        // Draw the current time in the center of the clock
//        String time = String.format("%02d:%02d:%02d", hour, minute, second);
//        FontMetrics fm = g.getFontMetrics();
//        int timeWidth = fm.stringWidth(time);
//        int timeHeight = fm.getAscent() - fm.getDescent();
//        g.drawString(time, x - timeWidth/2, y + timeHeight/2);



        // Draw the hour hand
        int hourLength = (int) (radius * 0.5);
        int hourX = (int) (x + hourLength * Math.sin(Math.toRadians(30 * hour + 0.5 * minute)));
        int hourY = (int) (y - hourLength * Math.cos(Math.toRadians(30 * hour + 0.5 * minute)));
        g.drawLine(x, y, hourX, hourY);

        // Draw the minute hand
        int minuteLength = (int) (radius * 0.7);
        int minuteX = (int) (x + minuteLength * Math.sin(Math.toRadians(6 * minute + 0.1 * second)));
        int minuteY = (int) (y - minuteLength * Math.cos(Math.toRadians(6 * minute + 0.1 * second)));
        g.drawLine(x, y, minuteX, minuteY);

        // Draw the second hand
        int secondLength = (int) (radius * 0.9);
        int secondX = (int) (x + secondLength * Math.sin(Math.toRadians(6 * second)));
        int secondY = (int) (y - secondLength * Math.cos(Math.toRadians(6 * second)));
        g.setColor(Color.RED);
        g.drawLine(x, y, secondX, secondY);
    }
}

