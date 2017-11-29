package Settings;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PaintPanel extends JPanel {
    private Settings settings;

    private GroupLayout layout;

    private JLabel presetLabel;
    private JLabel customLabel;

    private JColorChooser pcc;

    AbstractColorChooserPanel presetPanel;
    AbstractColorChooserPanel customPanel;


    public PaintPanel(Settings settings){
        this.settings = settings;

   /*     Dimension size = new Dimension(500, 100);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);*/

        presetLabel = new JLabel("Presets");
        customLabel = new JLabel("Custom");

        pcc = new JColorChooser();
        pcc.getSelectionModel().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                settings.paintColor = pcc.getColor();
            }
        });

        presetPanel = new PresetColorPanel();
        customPanel = new CustomColorPanel();

        AbstractColorChooserPanel panels[] = {presetPanel, customPanel};
        pcc.setChooserPanels(panels);

        pcc.setPreviewPanel(new JPanel());

        createLayout();
    }

    private void createLayout(){
        layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel label1 = new JLabel("Presets");
        JLabel label2 = new JLabel("Custom");

        layout.setHorizontalGroup(
          layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                          .addComponent(label1)
                          .addComponent(label2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(presetPanel)
                        .addComponent(customPanel))
        );

        layout.setVerticalGroup(
          layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                          .addComponent(label1)
                          .addComponent(presetPanel))
                  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                          .addComponent(label2)
                          .addComponent(customPanel))
        );

        layout.preferredLayoutSize(this);
    }
}
