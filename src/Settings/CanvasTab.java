package Settings;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CanvasTab extends JPanel {
    /* SIZE
     Radio buttons:
        Presets:    1000x1000 (Default)
                    800x800
                    500x800
       Text enter:
         Custom:
            Width: _______ Enter
            Height: _______ Enter

       TILT
       Radio buttons:
            None (selected)
            right
            left
            top
            bottom
            custom
                JSliders
                    vertical: top to bottom
                    horizontal: left to right
     */

    private Settings settings;

    private GroupLayout layout;

    private JLabel sizeLabel;
    private JLabel tiltLabel;

    CanvasSizePanel sizePanel;
    CanvasTiltPanel tiltPanel;

    public CanvasTab(Settings settings){
        this.settings = settings;

        sizeLabel = new JLabel("Size");
        tiltLabel = new JLabel("Tilt");

        sizePanel = new CanvasSizePanel(settings.getCanvasDimension());
        tiltPanel = new CanvasTiltPanel(settings);

        createLayout();
    }

    private void createLayout(){
        layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(sizeLabel)
                                .addComponent(tiltLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(sizePanel)
                                .addComponent(tiltPanel))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(sizeLabel)
                                .addComponent(sizePanel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(tiltLabel)
                                .addComponent(tiltPanel))
        );
        layout.preferredLayoutSize(this);
    }
}