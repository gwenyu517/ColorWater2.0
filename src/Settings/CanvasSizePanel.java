package Settings;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class CanvasSizePanel extends JPanel {
    CanvasDimension dimension;

    private GroupLayout layout;

    protected int chosenWidth = 1000;
    protected int chosenHeight = 1000;

    // Preset radio buttons
    private final JLabel presetLabel = new JLabel("Presets:");
    JRadioButton preset1;
    JRadioButton preset2;
    JRadioButton preset3;

    ButtonGroup group;

    // Custom input fields
    private final JLabel customLabel = new JLabel("Custom:");
    private final JLabel customWLabel = new JLabel("Width:");
    private final JLabel customHLabel = new JLabel("Height:");

    private JFormattedTextField customWidthField;
    private JFormattedTextField customHeightField;

    private final JButton button = new JButton("Resize");

    public CanvasSizePanel(CanvasDimension cd){
        this.dimension = cd;

        createButtons();
        groupButtons();


        createTextFields();

        createResizeButton();


        createLayout();
}

    private void createButtons(){
        preset1 = new JRadioButton("1000 x 1000");
        preset1.setActionCommand("Default");
        preset1.setSelected(true);
        preset1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                chosenWidth = 1000;
                chosenHeight = 1000;
                dimension.setDimension(1000, 1000);
            }
        });

        preset2 = new JRadioButton("800 x 800");
        preset2.setActionCommand("preset2");
        preset2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                chosenWidth = 800;
                chosenHeight = 800;
                dimension.setDimension(800, 800);
            }
        });

        preset3 = new JRadioButton("600 x 800");
        preset3.setActionCommand("preset3");
        preset3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                chosenWidth = 600;
                chosenHeight = 800;
                dimension.setDimension(600, 800);
            }
        });
    }

    private void groupButtons(){
        group = new ButtonGroup();
        group.add(preset1);
        group.add(preset2);
        group.add(preset3);
    }

    private void createTextFields(){
        customWidthField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        customWidthField.setValue(chosenWidth);
        customWidthField.setColumns(20);

        customWidthField.addPropertyChangeListener("canvasWidth", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
               // chosenWidth = ((Number)customWidthField.getValue()).intValue();
            }
        });

        customHeightField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        customHeightField.setValue(chosenHeight);
        customHeightField.setColumns(5);
        customHeightField.addPropertyChangeListener("canvasHeight", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
           //     chosenHeight = ((Number)customHeightField.getValue()).intValue();
            }
        });
    }

    private void createResizeButton(){
        button.setVerticalAlignment(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.TRAILING);
        button.setMnemonic(KeyEvent.VK_1);
        button.setActionCommand("resize");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chosenWidth = ((Number)customWidthField.getValue()).intValue();
                chosenHeight = ((Number)customHeightField.getValue()).intValue();
                customWidthField.setValue(chosenWidth);
                customHeightField.setValue(chosenHeight);
                dimension.setDimension(chosenWidth, chosenHeight);
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
                                .addComponent(presetLabel)
                                .addComponent(customLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(preset1)
                                .addComponent(preset2)
                                .addComponent(preset3)
                                .addComponent(customWidthField)
                                .addComponent(customHeightField)
                                .addComponent(button))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(presetLabel)
                                .addComponent(preset1))
                        .addComponent(preset2)
                        .addComponent(preset3)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(customLabel)
                                .addComponent(customWidthField))
                        .addComponent(customHeightField)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(button))
        );

        layout.preferredLayoutSize(this);
    }
}
