package Settings;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PaintTab extends JPanel {
    private Settings settings;

    private GroupLayout layout;

    private JLabel presetLabel;
    private JLabel customLabel;

    private JColorChooser pcc;

    PresetColorPanel presetPanel;
    CustomColorPanel customPanel;


    public PaintTab(Settings settings){
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
                Color chosenColor = pcc.getColor();
                //  settings.paintColor = chosenColor;
                //customPanel.syncColor(chosenColor);


                Color it = new Color(chosenColor.getRed(), chosenColor.getGreen(), chosenColor.getBlue(), 10);
                settings.setPaintColor(it);
                customPanel.syncColor(it);
            }
        });

        presetPanel = new PresetColorPanel();
        customPanel = new CustomColorPanel();

        AbstractColorChooserPanel panels[] = {presetPanel, customPanel};
        pcc.setChooserPanels(panels);

        //  pcc.setPreviewPanel(new JPanel());

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
                                .addComponent(presetLabel)
                                .addComponent(customLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(presetPanel)
                                .addComponent(customPanel)
                                .addComponent(pcc.getPreviewPanel()))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(presetLabel))
                        .addComponent(presetPanel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(customLabel)
                                .addComponent(customPanel))
                        .addComponent(pcc.getPreviewPanel())
        );

        layout.preferredLayoutSize(this);
    }
}
