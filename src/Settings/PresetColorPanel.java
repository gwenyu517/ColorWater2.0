package Settings;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class PresetColorPanel extends AbstractColorChooserPanel{

    private final int SQUARE_SIZE = 8;

    private JToggleButton r,o,y,g,b,i,v;

    private BufferedImage redImage;
    private BufferedImage orangeImage;
    private BufferedImage yellowImage;
    private BufferedImage greenImage;
    private BufferedImage blueImage;
    private BufferedImage indigoImage;
    private BufferedImage violetImage;

    public void updateChooser(){
        Color color = getColorFromModel();
        if (Color.RED.equals(color))
            r.setSelected(true);
        else if (Color.ORANGE.equals(color))
            o.setSelected(true);
        else if (Color.YELLOW.equals(color))
            y.setSelected(true);
        else if (Color.GREEN.equals(color))
            g.setSelected(true);
        else if (Color.BLUE.equals(color))
            b.setSelected(true);
        else if ((new Color(8, 0, 130)).equals(color))
            i.setSelected(true);
        else if ((new Color(128, 0, 238)).equals(color))
            v.setSelected(true);
    }

    private JToggleButton createSquares(String name, BufferedImage image, Border normalBorder){
        JToggleButton square = new JToggleButton();
        square.setActionCommand(name);
        square.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color newColor = null;
                String command = ((JToggleButton)e.getSource()).getActionCommand();

                if ("red".equals(command))
                    newColor = Color.RED;
                else if ("orange".equals(command))
                    newColor = Color.ORANGE;
                else if ("yellow".equals(command))
                    newColor = Color.YELLOW;
                else if ("green".equals(command))
                    newColor = Color.GREEN;
                else if ("blue".equals(command))
                    newColor = Color.BLUE;
                else if ("indigo".equals(command))
                    newColor = new Color(8, 0, 130);
                else if ("violet".equals(command))
                    newColor = new Color(128, 0, 238);

                getColorSelectionModel().setSelectedColor(newColor);
            }
        });

        ImageIcon icon = new ImageIcon(image);

        if (icon != null){
            square.setIcon(icon);
            square.setToolTipText(name);
            square.setBorder(normalBorder);
        }
        else {
        }

        return square;
    }

    protected void buildChooser(){
        setLayout(new GridLayout(1,7, 3,1));

        ButtonGroup presetSquares = new ButtonGroup();
        Border border = BorderFactory.createEmptyBorder();

        createImages();

        r = createSquares("red", redImage, border);
        presetSquares.add(r);
        add(r);

        o = createSquares("orange", orangeImage, border);
        presetSquares.add(o);
        add(o);

        y = createSquares("yellow", yellowImage, border);
        presetSquares.add(y);
        add(y);

        g = createSquares("green", greenImage, border);
        presetSquares.add(g);
        add(g);

        b = createSquares("blue", blueImage, border);
        presetSquares.add(b);
        add(b);

        i = createSquares("indigo", indigoImage, border);
        presetSquares.add(i);
        add(i);

        v = createSquares("violet", violetImage, border);
        presetSquares.add(v);
        add(v);
    }

    private void createImages(){
        Graphics2D g2d;

        redImage = new BufferedImage(SQUARE_SIZE,SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = redImage.createGraphics();
        g2d.setColor(Color.RED);
        g2d.fillRect(0,0,SQUARE_SIZE,SQUARE_SIZE);

        orangeImage = new BufferedImage(SQUARE_SIZE,SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = orangeImage.createGraphics();
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(0,0,SQUARE_SIZE,SQUARE_SIZE);

        yellowImage = new BufferedImage(SQUARE_SIZE,SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = yellowImage.createGraphics();
        g2d.setColor(Color.YELLOW);
        g2d.fillRect(0,0,SQUARE_SIZE,SQUARE_SIZE);

        greenImage = new BufferedImage(SQUARE_SIZE,SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = greenImage.createGraphics();
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0,0,SQUARE_SIZE,SQUARE_SIZE);

        blueImage = new BufferedImage(SQUARE_SIZE,SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = blueImage.createGraphics();
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0,0,SQUARE_SIZE,SQUARE_SIZE);

        indigoImage = new BufferedImage(SQUARE_SIZE,SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = indigoImage.createGraphics();
        g2d.setColor(new Color(8, 0, 130));
        g2d.fillRect(0,0,SQUARE_SIZE,SQUARE_SIZE);

        violetImage = new BufferedImage(SQUARE_SIZE,SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = violetImage.createGraphics();
        g2d.setColor(new Color(128, 0, 238));
        g2d.fillRect(0,0,SQUARE_SIZE,SQUARE_SIZE);
    }

    public String getDisplayName(){
        return null;
    }

    public Icon getSmallDisplayIcon(){
        return null;
    }

    public Icon getLargeDisplayIcon(){
        return null;
    }
}
