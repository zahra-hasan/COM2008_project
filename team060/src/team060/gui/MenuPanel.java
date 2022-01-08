package team060.gui;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/** Helper class inherited by all other GUI classes
 * @author Christopher Findon 
*/
public class MenuPanel extends JPanel {
    protected static final long serialVersionUID = 1L;
	protected MainFrame frame;
    public enum VerifyType {email,password,phoneNumber,notEmpty};
    protected ArrayList<FormField> fieldsToVerify;

    public MenuPanel(MainFrame frame) {
        this.frame = frame;
    }
}
