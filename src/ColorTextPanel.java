import javax.swing.*;
import java.awt.*;

public class ColorTextPanel extends JPanel {
    private final Color color;
    private final String text;

    public ColorTextPanel(Color color, String text) {
        this.color = color;
        this.text = text;
        this.setPreferredSize(new Dimension(50, 100)); // Adjust size as needed
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Draw the color box
        g2d.setColor(color);
        g2d.fillRect(10, 10, 30, 30); // Draw a small square filled with the color

        // Draw the text
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, 50, 30); // Draw the text next to the color box
    }
}
