import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Settings.Settings;
import Settings.SettingsPanel;
import Settings.CanvasDimension;

public class ImageFrame extends JFrame implements Observer{
    private SettingsPanel tabs;
    private Settings settings;
    private Canvas canvas;

    private final JFileChooser chooser;

    public ImageFrame(int width, int height){
        this.setTitle("ColorWater");
        this.setSize(width, height);

        settings = new Settings();
        settings.getCanvasDimension().addObserver(this);

        chooser = new JFileChooser();

        addMenu();
        addTabs();
        addCanvas();

        this.pack();
        this.setVisible(true);
    }

    private void addMenu(){
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                open();
            }
        } );

        fileMenu.add(openItem);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                save();
            }
        } );

        fileMenu.add(saveItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        } );

        fileMenu.add(exitItem);


        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

    private void addTabs(){
        tabs = new SettingsPanel(settings);
        this.getContentPane().add(tabs, BorderLayout.WEST);
    }

    private void addCanvas(){
        canvas = new Canvas(settings);
        this.getContentPane().add(canvas, BorderLayout.CENTER);
    }

    private void open(){
        File file = getFile();
        if (file != null){
            displayFile(file);
        }
    }

    private File getFile(){
        File file =  null;
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();
        }
        return file;
    }

    private void displayFile(File file){
        try{
            canvas.setPaintLayer(ImageIO.read(file));
        }
        catch(IOException exception){
            JOptionPane.showMessageDialog(this, exception);
        }
    }

    private void save(){
        File outputFile = promptOutputFile();
        try {
            javax.imageio.ImageIO.write(canvas.getImage(), "png", outputFile);
        }
        catch ( IOException e )
        {
            JOptionPane.showMessageDialog( ImageFrame.this,
                    "Error saving file",
                    "oops!",
                    JOptionPane.ERROR_MESSAGE );
        }
    }

    private File promptOutputFile(){
        String fileName = JOptionPane.showInputDialog("Output file name?");
        if (fileName.indexOf('.') != -1)
            fileName = fileName.substring(0, fileName.indexOf('.'));

        return new File(fileName + ".png");
    }

    public void update(Observable o, Object arg){
        Object[] options = { "OK", "CANCEL" };
        int k = JOptionPane.showOptionDialog(null,
                "Changing the size will clear the canvas. Continue?", "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
        if (k == 0){
            canvas.stopPaint();
            remove(canvas);
            addCanvas();
            this.pack();
            this.setVisible(true);
        }

    }
}