package Settings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BrushTab extends JPanel {
    private Settings settings;

    private GroupLayout layout;

    private final int SIZE_MIN = 1;
    private final int SIZE_MAX = 10;
    private final int SIZE_INIT = 5;

    private JLabel pbLabel;
    private JLabel wbLabel;

    private JSlider paintBrushSizeSlider;
    private JSlider waterBrushSizeSlider;

    protected BrushTab(Settings settings){
        this.settings = settings;

        pbLabel = new JLabel("Paint Brush Size");
        wbLabel = new JLabel("Water Brush Size");

        // Create slider
        addPBSlider();
        addWBSlider();

        // ToDo: addPreview();

        createLayout();
    }

    private void addPBSlider(){
        paintBrushSizeSlider = new JSlider(JSlider.HORIZONTAL, SIZE_MIN, SIZE_MAX, SIZE_INIT);

        //Turn on labels at major tick marks
        paintBrushSizeSlider.setMajorTickSpacing(9);
        paintBrushSizeSlider.setMinorTickSpacing(1);
        paintBrushSizeSlider.setPaintTicks(true);
        paintBrushSizeSlider.setPaintLabels(true);
        paintBrushSizeSlider.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));

        paintBrushSizeSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    settings.setPaintBrushSize(source.getValue());
            }
        });
    }

    private void addWBSlider(){
        waterBrushSizeSlider = new JSlider(JSlider.HORIZONTAL, SIZE_MIN, SIZE_MAX, SIZE_INIT);

        //Turn on labels at major tick marks
        waterBrushSizeSlider.setMajorTickSpacing(9);
        waterBrushSizeSlider.setMinorTickSpacing(1);
        waterBrushSizeSlider.setPaintTicks(true);
        waterBrushSizeSlider.setPaintLabels(true);
        waterBrushSizeSlider.setBorder(
                BorderFactory.createEmptyBorder(0,0,10,0));

        waterBrushSizeSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    settings.setWaterBrushSize(source.getValue());
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
