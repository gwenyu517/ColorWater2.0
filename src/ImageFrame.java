import javax.swing.*;
import java.awt.*;

import Settings.Settings;
import Settings.SettingsPanel;

public class ImageFrame extends JFrame {
    private SettingsPanel tabs;
    private Settings settings;
    private CanvasPanel canvasPanel;

    public ImageFrame(int width, int height){
        this.setTitle("ColorWater");
        this.setSize(width, height);

        settings = new Settings();

        // addMenu();
        addTabs();
        addCanvas();

        this.pack();
        this.setVisible(true);
    }

    private void addMenu(){
        JMenu fileMenu = new JMenu("File");

     /*   JMenuItem waterItem = new JMenuItem("Water mode");
        waterItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
      //          c.setWaterMode(true);
            }
        } );

        fileMenu.add(waterItem);

        JMenuItem colorItem = new JMenuItem("Color mode");
        colorItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
       //         c.setWaterMode(false);
            }
        } );

        fileMenu.add(colorItem);
        */

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

    private void addTabs(){
        tabs = new SettingsPanel(settings);
        this.getContentPane().add(tabs, BorderLayout.WEST);
    }

    private void addCanvas(){
        canvasPanel = new CanvasPanel(settings);
        this.getContentPane().add(canvasPanel, BorderLayout.CENTER);
    }
}
