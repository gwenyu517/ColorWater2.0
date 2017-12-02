package Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class BrushPreviewPanel extends JPanel {
    private final int WIDTH;
    private final int HEIGHT;

    private BufferedImage image;
    private Graphics2D g2d;

    private int particleRadius = 3;

    public BrushPreviewPanel(int width, int height){
        WIDTH = width;
        HEIGHT = height;

        Dimension size = new Dimension(width, height);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();

    }

    public void setColor(Color color){
        g2d.setColor(color);
    }

    private void clearImage(){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0, WIDTH, HEIGHT);
    }

    public void drawShape(int size){
        clearImage();

        g2d.setColor(Color.BLACK);

        double x = WIDTH / 2;
        double y = HEIGHT / 2;

        int radius = (size + 1) * particleRadius;

        g2d.fill(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius));
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

}
