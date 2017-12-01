package Settings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BrushPanel extends JPanel {
    private Settings settings;

    private GroupLayout layout;

    final int SIZE_MIN = 0;
    final int SIZE_MAX = 10;
    final int SIZE_INIT = 5;

    JLabel pbLabel;
    JLabel wbLabel;

    JSlider paintBrushSizeSlider;
    JSlider waterBrushSizeSlider;

    public BrushPanel(Settings settings){
        this.settings = settings;

        // Create the label
 //       JLabel sliderLabel = new JLabel("Brush Size", JLabel.LEFT);
   //     sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        pbLabel = new JLabel("Paint Brush Size");
        wbLabel = new JLabel("Water Brush Size");

        // Create slider
        addPBSlider();
        addWBSlider();

        // ToDo: addPreview();

        createLayout();

        // Put everything together
     //   this.add(sliderLabel);
       // this.add(paintBrushSizeSlider);
        //this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    private void addPBSlider(){
        paintBrushSizeSlider = new JSlider(JSlider.HORIZONTAL, SIZE_MIN, SIZE_MAX, SIZE_INIT);

        //Turn on labels at major tick marks
        paintBrushSizeSlider.setMajorTickSpacing(10);
        paintBrushSizeSlider.setMinorTickSpacing(1);
        paintBrushSizeSlider.setPaintTicks(true);
        paintBrushSizeSlider.setPaintLabels(true);
        paintBrushSizeSlider.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));

        paintBrushSizeSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    settings.paintBrushSize = (int)source.getValue();
            }
        });
    }

    private void addWBSlider(){
        waterBrushSizeSlider = new JSlider(JSlider.HORIZONTAL, SIZE_MIN, SIZE_MAX, SIZE_INIT);

        //Turn on labels at major tick marks
        waterBrushSizeSlider.setMajorTickSpacing(10);
        waterBrushSizeSlider.setMinorTickSpacing(1);
        waterBrushSizeSlider.setPaintTicks(true);
        waterBrushSizeSlider.setPaintLabels(true);
        waterBrushSizeSlider.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));

        waterBrushSizeSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    settings.waterBrushSize = (int)source.getValue();
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
                                .addComponent(pbLabel)
                                .addComponent(wbLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(paintBrushSizeSlider)
                                .addComponent(waterBrushSizeSlider))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pbLabel))
                        .addComponent(paintBrushSizeSlider)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(wbLabel)
                                .addComponent(waterBrushSizeSlider))
        );

        layout.preferredLayoutSize(this);
    }
}
