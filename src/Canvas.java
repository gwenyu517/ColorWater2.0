import Settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
    private Settings settings;

    private ThreadGroup strokes;

    private final int WIDTH;
    private final int HEIGHT;

    // WaterMask
    private BufferedImage waterLayer;
    private Graphics2D w_g2d;
    // Drying stuff
    private Timer dryTimer;
    private int MILLESECONDS_BEFORE_DRY = 1000;
    private Color evapColor = new Color(255, 255, 255, 50);

    // PaintLayer
    private BufferedImage paintLayer;
    private Graphics2D p_g2d;

    public Canvas(Settings settings){
        this.settings = settings;

        strokes = new ThreadGroup("PaintStrokes");

        WIDTH = settings.getCanvasWidth();
        HEIGHT = settings.getCanvasHeight();

        Dimension size = new Dimension(WIDTH, HEIGHT);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

        waterLayer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        w_g2d = waterLayer.createGraphics();

        paintLayer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        p_g2d = paintLayer.createGraphics();

        createNewCanvas();
        createDryMask();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                if (SwingUtilities.isRightMouseButton(event))
                    addWaterDropAt(event.getPoint());
                else if (SwingUtilities.isLeftMouseButton(event))
                    addPaintDropAt(event.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent event){
                if (SwingUtilities.isRightMouseButton(event))
                    addWaterDropAt(event.getPoint());
                else if (SwingUtilities.isLeftMouseButton(event))
                    addPaintDropAt(event.getPoint());
            }
        });

        dryTimer = new Timer(MILLESECONDS_BEFORE_DRY, new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                dryTimer.stop();

                dryWetMask();

                dryTimer.restart();
            }
        });
        dryTimer.start();
    }

    private void createNewCanvas(){
        p_g2d.setColor(Color.WHITE);
        p_g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private void createDryMask(){
        w_g2d.setColor(Color.WHITE);
        w_g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private void dryWetMask(){
        w_g2d.setColor(evapColor);
        w_g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private int dropDuration = 20;

    private void addWaterDropAt(Point point){
        new Thread( strokes, new Runnable(){
            public void run(){
                Drop drop = new Drop(settings.getWaterBrushSize(), waterLayer);
                drop.setWetAreaMask(null);
                drop.setDropColor(new Color(0,0,0,10));

                for (int i = 0; i < dropDuration; ++i){
                    if (i == 0)
                        drop.dripAt(point);
                    else
                        drop.spread();

                    waterLayer = drop.getImage();
                }
            }
        }).start();
    }

    private void addPaintDropAt(Point point){
        new Thread( strokes, new Runnable(){
            public void run(){
                Drop drop = new Drop(settings.getPaintBrushSize(), paintLayer);
                drop.setWetAreaMask(waterLayer);
                drop.setDropColor(settings.getPaintColor());

                for (int i = 0; i < dropDuration; ++i){
                    if (i == 0)
                        drop.dripAt(point);
                    else
                        drop.spread();

                    paintLayer = drop.getImage();

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

    public void stopPaint(){
        strokes.destroy();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(paintLayer, 0, 0, null);
    }
}