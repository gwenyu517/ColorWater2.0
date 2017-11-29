package Settings;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class CustomColorPanel extends AbstractColorChooserPanel
                              {
    private GroupLayout layout;

    private JSlider rSlider;
    private JSlider gSlider;
    private JSlider bSlider;

    private JLabel rLabel;
    private JLabel gLabel;
    private JLabel bLabel;

    private int rVal;
    private int gVal;
    private int bVal;

    private final int MIN = 0;
    private final int MAX = 255;
    private final int INIT = 0;


    public void updateChooser(){

    }

    protected void buildChooser(){
     /*   Dimension size = new Dimension(100, 30);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);*/

        rLabel = new JLabel("R");
        createRSlider();

        gLabel = new JLabel("G");
        createGSlider();

        bLabel = new JLabel("B");
        createBSlider();

        createLayout();
    }

    private void createRSlider(){
        rSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);

        //Turn on labels at major tick marks
        rSlider.setMajorTickSpacing(16);
        rSlider.setMinorTickSpacing(256);
        rSlider.setPaintTicks(true);
        rSlider.setPaintLabels(false);
    //    rSlider.setBorder(                BorderFactory.createEmptyBorder(0,0,10,0));

        rSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    rVal = (int)source.getValue();
                    System.out.println("R " + rVal);
            }
        });
    }
    private void createGSlider(){
        gSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);

        //Turn on labels at major tick marks
        gSlider.setMajorTickSpacing(16);
        gSlider.setMinorTickSpacing(256);
        gSlider.setPaintTicks(true);
        gSlider.setPaintLabels(false);
   //     gSlider.setBorder(                BorderFactory.createEmptyBorder(0,0,10,0));

        gSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    gVal = (int)source.getValue();
                    System.out.println("G " + gVal);
            }
        });
    }
    private void createBSlider(){
        bSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);

        //Turn on labels at major tick marks
        bSlider.setMajorTickSpacing(16);
        bSlider.setMinorTickSpacing(256);
        bSlider.setPaintTicks(true);
        bSlider.setPaintLabels(false);
     //   bSlider.setBorder(                BorderFactory.createEmptyBorder(0,0,10,0));

        bSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    bVal = (int)source.getValue();
                    System.out.println("B " + bVal);
            }
        });
    }

    private void createLayout(){
        layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
          layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(rLabel)
                        .addComponent(gLabel)
                        .addComponent(bLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(rSlider)
                        .addComponent(gSlider)
                        .addComponent(bSlider))
        );

        layout.setVerticalGroup(
          layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(rLabel)
                        .addComponent(rSlider))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(gLabel)
                        .addComponent(gSlider))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bLabel)
                        .addComponent(bSlider))
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
