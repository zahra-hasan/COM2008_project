package team060.gui;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.*;
import javax.swing.*;

import team060.gui.MenuPanel.VerifyType;

/** Helper class for text input fields in forms
 * @author Christopher Findon 
 * @author Zahra Hasan A Alhilal
*/
public class FormField {
    private JPanel panel;
    private JLabel label;
    private JLabel errorLabel;
    private JTextField field;
    private final int FIELD_LENGTH = 15;
    private VerifyType verifyType;

    public FormField(String labelText, VerifyType verifyType) {
        this.verifyType = verifyType;

        panel = new JPanel();
        panel.setLayout(new GridLayout(3,1,1,1));

        label = new JLabel(labelText);
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        field = new JTextField(FIELD_LENGTH);

        panel.add(label);
        panel.add(field);
        panel.add(errorLabel);
    }

    // Returns the JPanel this FormField is a part of
    public JPanel getPanel() {
        return panel;
    }

    // Returns the text written in the field
    public String getText() {
    	return field.getText();
    }

    // Displays an error next to the field
    public void displayError(String errorText) {
        errorLabel.setText(errorText);
    }

    // Verifies the input
    public Boolean verifyField() {
        field.setText(getText().trim());
        errorLabel.setText("");
        Boolean valid = true;
        if (verifyType == VerifyType.email) {
            Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
            Matcher matcher = pattern.matcher(getText());

            if (!matcher.matches()) {
                valid = false;
                displayError("This email address is invalid.");
            }
        }
        else if (verifyType == VerifyType.notEmpty) {
            if (getText().isEmpty()) {
                valid = false;
                displayError("This field cannot be empty.");
            }
        }
        else if (verifyType == VerifyType.password) {
            if (getText().length() < 3) {
                valid = false;
                displayError("Your password cannot be less than 3 characters.");
            }
        }
        else if (verifyType == VerifyType.phoneNumber) {
            if (!getText().matches("[0-9]+")) {
                valid = false;
                displayError("Your phone number should only include numbers, with no spaces.");
            }
        }

        return valid;
    }
}