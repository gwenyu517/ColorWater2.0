package Settings;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PaintTab extends JPanel {
    private Settings settings;

    private GroupLayout layout;

    private final JLabel colorLabel = new JLabel("Color");
    private final JLabel presetLabel = new JLabel("Presets");
    private final JLabel customLabel = new JLabel("Custom");

    private JColorChooser pcc;

    private PresetColorPanel presetPanel;
    private CustomColorPanel customPanel;
    private PaintPreviewPanel previewPanel;


    public PaintTab(Settings settings){
        this.settings = settings;

        pcc = new JColorChooser();
        pcc.getSelectionModel().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Color chosenColor = pcc.getColor();
                Color it = new Color(chosenColor.getRed(), chosenColor.getGreen(), chosenColor.getBlue(), 10);
                settings.setPaintColor(it);
                customPanel.syncColor(it);
                previewPanel.setColor(chosenColor);
            }
        });

        presetPanel = new PresetColorPanel();
        customPanel = new CustomColorPanel();

        AbstractColorChooserPanel panels[] = {presetPanel, customPanel};
        pcc.setChooserPanels(panels);

        previewPanel = new PaintPreviewPanel(30,100);
        pcc.setPreviewPanel(previewPanel);

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
                                .addComponent(colorLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(presetLabel)
                                        .addComponent(presetPanel)
                                )
                                .addComponent(customLabel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(customPanel)
                                        .addComponent(previewPanel)
                                )
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(colorLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(presetLabel)
                                .addComponent(presetPanel)
                        )
                        .addComponent(customLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(customPanel)
                                .addComponent(previewPanel)
                        )
        );

        layout.preferredLayoutSize(this);
    }
}
