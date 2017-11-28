package Settings;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PaintPanel extends JPanel {
    private Settings settings;

    private JColorChooser presets;
    private JColorChooser custom;


    public PaintPanel(Settings settings){
        this.settings = settings;

        presets = new JColorChooser();
        presets.getSelectionModel().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                settings.paintColor = presets.getColor();
            }
        });
        addPresetPanel();


    }

    private void addPresetPanel(){
        AbstractColorChooserPanel panel[] = {new PresetColorPanel()};
        presets.setChooserPanels(panel);

        add(presets, BorderLayout.PAGE_END);
    }
}
