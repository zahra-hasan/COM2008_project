package team060.gui;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.*;
import javax.swing.*;

import team060.gui.MenuPanel.VerifyType;

/** Helper class for forms
 * @author Christopher Findon 
*/
public class Form {
    private JPanel panel;
    private ArrayList<FormField> fieldList = new ArrayList<>();

    public Form(JPanel panelToAddTo) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 32, 2));

        panelToAddTo.add(panel);
    }

    // Returns the JPanel everything in the form is inside
    public JPanel getPanel() {
        return panel;
    }

    // Adds a section that is spaced from other sections in the form
    public void addSectionPanel(JPanel sectionPanel, String panelLabelText) {
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        // Creates a JLabel to display for the section
        JLabel panelLabel = new JLabel(panelLabelText);
        panelLabel.setFont(new Font(panelLabel.getFont().getFontName(),Font.PLAIN,16));
        sectionPanel.add(panelLabel);

        panel.add(sectionPanel);
    }

    // Adds a combo box to a section panel
    public void addComboBox(JComboBox comboBox, String comboBoxLabelText, JPanel sectionPanel) {
        // Creates a panel and a JLabel to display the combo box in
        JPanel comboBoxPanel = new JPanel();
        JLabel comboBoxLabel = new JLabel(comboBoxLabelText);
        comboBoxPanel.add(comboBoxLabel);
        comboBoxPanel.add(comboBox);

        sectionPanel.add(comboBoxPanel);
    }

    // Adds a FormField to a section panel
    public void addFormField(FormField formField, JPanel sectionPanel) {
        fieldList.add(formField);
        sectionPanel.add(formField.getPanel());
    }

    // Verify the input fields in the form
    public Boolean verifyFields() {
        Boolean valid = true;
        for (FormField field : fieldList) {
            if (!field.verifyField()) {
                valid = false;
            }
        }

        return valid;
    }
}