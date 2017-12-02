package Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PaintPreviewPanel extends JPanel {
    private int WIDTH;
    private int HEIGHT;

    private BufferedImage image;
    private Graphics2D g2d;

    public PaintPreviewPanel(int width, int height){
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
        clearImage();
        g2d.setColor(color);
        g2d.fillRect(0,0, WIDTH, HEIGHT);
        repaint();
    }

    private void clearImage(){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0, WIDTH, HEIGHT);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

}
