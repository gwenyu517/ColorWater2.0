import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import Settings.Settings;
import Settings.SettingsPanel;
import Settings.CanvasDimension;

public class ImageFrame extends JFrame implements Observer{
    private SettingsPanel tabs;
    private Settings settings;
    private Canvas canvas;

    public ImageFrame(int width, int height){
        this.setTitle("ColorWater");
        this.setSize(width, height);

        settings = new Settings();
        settings.getCanvasDimension().addObserver(this);


        //      addMenu();
        addTabs();
        addCanvas();

        this.pack();
        this.setVisible(true);
    }

 /*   private void addMenu(){
        JMenu fileMenu = new JMenu("File");

        JMenuItem waterItem = new JMenuItem("Water mode");
        waterItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                canvas.setWaterMode(true);
            }
        } );

        fileMenu.add(waterItem);

        JMenuItem colorItem = new JMenuItem("Color mode");
        colorItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                canvas.setWaterMode(false);
            }
        } );

        fileMenu.add(colorItem);


        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }*/

    private void addTabs(){
        tabs = new SettingsPanel(settings);
        this.getContentPane().add(tabs, BorderLayout.WEST);
    }

    private void addCanvas(){
        canvas = new Canvas(settings);
        this.getContentPane().add(canvas, BorderLayout.CENTER);
    }

    public void update(Observable o, Object arg){
 /*       Object[] options = { "OK", "CANCEL" };
        int k = JOptionPane.showOptionDialog(null,
                "Changing the size will clear the canvas. Continue?", "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
        if (k == 0){
            canvas.stopPaint();
            remove(canvas);
            canvas = new Canvas(settings);
            this.getContentPane().add(canvas, BorderLayout.CENTER);
            repaint();
        }*/

    }
}