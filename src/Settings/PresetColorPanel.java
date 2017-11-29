package Settings;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.security.acl.Group;

public class PresetColorPanel extends AbstractColorChooserPanel{

    private final int SQUARE_SIZE = 8;

    private GroupLayout layout;

    private JToggleButton red,
                          orange,
                          yellow,
                          green,
                          blue,
                          indigo,
                          violet,
                          black;

    private BufferedImage redImage,
                          orangeImage,
                          yellowImage,
                          greenImage,
                          blueImage,
                          indigoImage,
                          violetImage,
                          blackImage;

    public void updateChooser(){
        Color color = getColorFromModel();
        if (Color.RED.equals(color))
            red.setSelected(true);
        else if (Color.ORANGE.equals(color))
            orange.setSelected(true);
        else if (Color.YELLOW.equals(color))
            yellow.setSelected(true);
        else if (Color.GREEN.equals(color))
            green.setSelected(true);
        else if (Color.BLUE.equals(color))
            blue.setSelected(true);
        else if ((new Color(8, 0, 130)).equals(color))
            indigo.setSelected(true);
        else if ((new Color(128, 0, 238)).equals(color))
            violet.setSelected(true);
        else if (Color.BLACK.equals(color))
            black.setSelected(true);
    }

    protected void buildChooser(){
     //   setLayout(new GridLayout(1,7, 3,1));
     /*   Dimension size = new Dimension(250, 20);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);*/

        ButtonGroup presetSquares = new ButtonGroup();
        Border border = BorderFactory.createEmptyBorder();

        createImages();

        red = createSquares("red", redImage, border);
        presetSquares.add(red);
        add(red);

        orange = createSquares("orange", orangeImage, border);
        presetSquares.add(orange);
        add(orange);

        yellow = createSquares("yellow", yellowImage, border);
        presetSquares.add(yellow);
        add(yellow);

        green = createSquares("green", greenImage, border);
        presetSquares.add(green);
        add(green);

        blue = createSquares("blue", blueImage, border);
        presetSquares.add(blue);
        add(blue);

        indigo = createSquares("indigo", indigoImage, border);
        presetSquares.add(indigo);
        add(indigo);

        violet = createSquares("violet", violetImage, border);
        presetSquares.add(violet);
        add(violet);

        black = createSquares("black", blackImage, border);
        presetSquares.add(black);
        add(black);

        createLayout();
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
                else if ("black".equals(command))
                    newColor = Color.BLACK;

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

        blackImage = new BufferedImage(SQUARE_SIZE,SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = blackImage.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,SQUARE_SIZE,SQUARE_SIZE);
    }

    private void createLayout(){
        layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
          layout.createSequentialGroup()
                .addComponent(red)
                .addComponent(orange)
                .addComponent(yellow)
                .addComponent(green)
                .addComponent(blue)
                .addComponent(indigo)
                .addComponent(violet)
                .addComponent(black)
        );

        layout.setVerticalGroup(
          layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(red)
                        .addComponent(orange)
                        .addComponent(yellow)
                        .addComponent(green)
                        .addComponent(blue)
                        .addComponent(indigo)
                        .addComponent(violet)
                        .addComponent(black))
        );
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
