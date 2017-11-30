import Settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class CanvasPanel extends JPanel {
    private Settings settings;

    private final int WIDTH;
    private final int HEIGHT;

    // WaterMask
    private BufferedImage waterLayer;
    private Graphics2D w_g2d;
    // Drying stuff
    private Timer dryTimer;
    private int MILLESECONDS_BEFORE_DRY = 1000;
    private Color evapColor = new Color(255, 255, 255, 50);

    public CanvasPanel(Settings settings){
        this.settings = settings;

        WIDTH = settings.getCanvasWidth();
        HEIGHT = settings.getCanvasHeight();

        Dimension size = new Dimension(WIDTH, HEIGHT);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

        waterLayer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        w_g2d = waterLayer.createGraphics();

        createNewCanvas();
        createDryMask();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
            //    eh = event.getPoint();
                addDropAt(event.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent event){
                addDropAt(event.getPoint());
            }
        });

        dryTimer = new Timer(MILLESECONDS_BEFORE_DRY, new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                dryTimer.stop();

                dryWetMask();
          //      k++;
            //    displayMask();

                dryTimer.restart();
            }
        });
        dryTimer.start();
    }

    private void createNewCanvas(){

    }

    private void createDryMask(){
        w_g2d.setColor(Color.WHITE);
        w_g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private void dryWetMask(){
        w_g2d.setColor(evapColor);
        w_g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }
/*
    private int k = 0;
    private Point eh = new Point();

    private void displayMask(){
        new Thread( new Runnable(){
            public void run(){

                System.out.println(k + " " + extractR(waterLayer.getRGB(eh.x, eh.y)));
                    SwingUtilities.invokeLater(
                            new Runnable(){
                                public void run(){
                                    repaint();
                                }
                            }
                    );
            }
        }).start();
    }

    private static int extractR(int ARGB){
        return (ARGB >>> 16) & 0x000000FF;
    }
*/
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
    private int dropDuration = 20;

    private void addDropAt(Point point){
        new Thread( new Runnable(){
            public void run(){
                Drop drop = new Drop(settings.getBrushSize(), waterLayer);
                drop.setDropColor(new Color(0,0,0,10));

                for (int i = 0; i < dropDuration; ++i){
                    if (i == 0)
                        drop.dripAt(point);
                    else
                        drop.spread();

                    waterLayer = drop.getImage();

                    SwingUtilities.invokeLater(
                            new Runnable(){
                                public void run(){
                                    repaint();
                                }
                            }
                    );
                }
            }
        }).start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(waterLayer, 0, 0, null);
    }
}