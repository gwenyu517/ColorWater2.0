package Settings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BrushPanel extends JPanel {
    private Settings settings;

    final int SIZE_MIN = 0;
    final int SIZE_MAX = 30;
    final int SIZE_INIT = 15;

    JSlider brushSizeSlider;

    public BrushPanel(Settings settings){
        this.settings = settings;

        // Create the label
        JLabel sliderLabel = new JLabel("Brush Size", JLabel.LEFT);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create slider
        addSlider();

        // ToDo: addPreview();

        // Put everything together
        this.add(sliderLabel);
        this.add(brushSizeSlider);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    private void addSlider(){
        brushSizeSlider = new JSlider(JSlider.HORIZONTAL, SIZE_MIN, SIZE_MAX, SIZE_INIT);

        //Turn on labels at major tick marks
        brushSizeSlider.setMajorTickSpacing(10);
        brushSizeSlider.setMinorTickSpacing(1);
        brushSizeSlider.setPaintTicks(true);
        brushSizeSlider.setPaintLabels(true);
        brushSizeSlider.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));

        brushSizeSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    settings.brushSize = (int)source.getValue();
            }
        });
    }
}
