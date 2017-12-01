package Settings;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CanvasSizePanel extends JPanel {
    CanvasDimension dimension;

    protected int chosenWidth = 1000;
    protected int chosenHeight = 1000;
    /* SIZE
     Radio buttons:
        Presets:    1000x1000 (Default)
                    800x800
                    500x800
       Text enter:
         Custom:
            Width: _______ Enter
            Height: _______ Enter*/
    JRadioButton preset1;
    JRadioButton preset2;
    JRadioButton preset3;

    ButtonGroup group;

    public CanvasSizePanel(CanvasDimension cd){
        this.dimension = cd;
        createButtons();
        groupButtons();
        addButtons();
    }

    private void createButtons(){
        preset1 = new JRadioButton("1000 x 1000");
        preset1.setActionCommand("Default");
        preset1.setSelected(true);
        preset1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dimension.setWidth(1000);
                dimension.setHeight(1000);
            }
        });

        preset2 = new JRadioButton("800 x 800");
        preset2.setActionCommand("preset2");
        preset2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dimension.setWidth(800);
                dimension.setHeight(800);
            }
        });

        preset3 = new JRadioButton("500 x 800");
        preset3.setActionCommand("preset3");
        preset3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dimension.setWidth(500);
                dimension.setHeight(800);
            }
        });
    }

    private void groupButtons(){
        group = new ButtonGroup();
        group.add(preset1);
        group.add(preset2);
        group.add(preset3);
    }

    private void addButtons(){
        add(preset1);
        add(preset2);
        add(preset3);
    }
}
