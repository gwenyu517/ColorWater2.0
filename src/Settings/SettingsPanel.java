package Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SettingsPanel extends JPanel{
    Settings settings;


    public SettingsPanel(Settings settings){
        super(new GridLayout(1,1));

   /*     Dimension size = new Dimension(500, 100);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);*/

        this.settings = settings;

        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent brushTab = new BrushTab(settings);
        tabbedPane.addTab("Brush", brushTab);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent paintTab = new PaintTab(settings);
        tabbedPane.addTab("Paint", paintTab);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


        JComponent canvasTab = new CanvasTab(settings);
        tabbedPane.addTab("Canvas", canvasTab);
        //   canvasPanel.setPreferredSize(new Dimension(500, 1000));
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);


        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

}
