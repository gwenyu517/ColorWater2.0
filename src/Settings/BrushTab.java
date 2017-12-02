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

    private final JLabel label = new JLabel("Brush Size");
    private final JLabel pbLabel = new JLabel("Paint Brush");
    private final JLabel wbLabel = new JLabel("Water Brush");

    private JSlider paintBrushSizeSlider;
    private JSlider waterBrushSizeSlider;

    private BrushPreviewPanel pBPreview;
    private BrushPreviewPanel wBPreview;

    protected BrushTab(Settings settings){
        this.settings = settings;

        // Create slider
        createPBSlider();
        createWBSlider();

        createPBPreview();
        createWBPreview();

        createLayout();
    }

    private void createPBSlider(){
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
               // if (!source.getValueIsAdjusting()) {
                    settings.setPaintBrushSize(source.getValue());
                    pBPreview.drawShape(source.getValue());
             //   }
            }
        });
    }

    private void createWBSlider(){
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
              //  if (!source.getValueIsAdjusting())
                    settings.setWaterBrushSize(source.getValue());
                    wBPreview.drawShape(source.getValue());
            }
        });
    }

    private void createPBPreview(){
        pBPreview = new BrushPreviewPanel(100, 100);
        pBPreview.drawShape(5);
    }

    private void createWBPreview(){
        wBPreview = new BrushPreviewPanel(100, 100);
        wBPreview.drawShape(5);
    }

    private void createLayout(){
        layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pbLabel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(paintBrushSizeSlider)
                                        .addComponent(pBPreview)
                                )
                                .addComponent(wbLabel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(waterBrushSizeSlider)
                                        .addComponent(wBPreview)
                                )
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(pbLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(paintBrushSizeSlider)
                                .addComponent(pBPreview)
                        )
                        .addComponent(wbLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(waterBrushSizeSlider)
                                .addComponent(wBPreview)
                        )
        );


       /*

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pbLabel)
                                .addComponent(wbLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(paintBrushSizeSlider)
                                .addComponent(waterBrushSizeSlider))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pBPreview)
                                .addComponent(wBPreview)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pbLabel)
                                .addComponent(paintBrushSizeSlider)
                                .addComponent(pBPreview))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(wbLabel)
                                .addComponent(waterBrushSizeSlider)
                                .addComponent(wBPreview))
        );*/

        layout.preferredLayoutSize(this);
    }
}