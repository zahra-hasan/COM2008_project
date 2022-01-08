package team060.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import team060.dao.GuestDao;
import team060.dao.HostDao;

/** GUI class for logging in
 * @author Zahra Hasan A Alhilal
 * @author Christopher Findon 
 */
public class Login extends MenuPanel implements ActionListener {
    private Form loginForm;
    private enum Role {Host,Guest};
    private JComboBox<Role> roleComboBox;
    private FormField emailField;
    private FormField passwordField;
    
    public Login(MainFrame frame) {
        super(frame);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Login");
        title.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,24));
        topPanel.add(title);

        // Login form panel
        JPanel formPanel = new JPanel();
        loginForm = new Form(formPanel);
        JPanel loginPanel = new JPanel();
        loginForm.addSectionPanel(loginPanel, "Login:");

        roleComboBox = new JComboBox<Role>(Role.values());
        loginForm.addComboBox(roleComboBox, "Choose your role:", loginPanel);
        emailField = new FormField("Email",VerifyType.email);
        loginForm.addFormField(emailField, loginPanel);
        passwordField = new FormField("Password",VerifyType.notEmpty);
        loginForm.addFormField(passwordField, loginPanel);

        // Button panel
        JPanel buttonPanel = new JPanel();

        JButton loginButton = new JButton("Login");
        loginButton.setActionCommand("login");
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);
        
        JButton backButton = new JButton("Back");
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(topPanel);
        add(formPanel);
        add(buttonPanel);
    }
    

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("back")) {
            frame.switchPanel(new HomePage(frame));
        }
        else if (command.equals("login")) {
            if (loginForm.verifyFields()) {
                try {
                    //log in host
                    if (roleComboBox.getSelectedItem() == Role.Host) {
                        HostDao hostDao = new HostDao(emailField.getText(),passwordField.getText());
                        System.out.println("declaring HostDao with email and password input...");
                        if (hostDao.loginHost() == true) {//check credentials OK
                            JOptionPane.showMessageDialog(frame, "login host successful");
                            frame.switchPanel(new HostMenu(frame, hostDao.getHostID()));
                        }
                        else if (hostDao.loginHost()== false) {
                            JOptionPane.showMessageDialog(frame, "email or password wrong");
                        }
                    }
                    //log in guest
                    else if (roleComboBox.getSelectedItem() == Role.Guest) {
                        GuestDao guestDao = new GuestDao(emailField.getText(), passwordField.getText());
                        System.out.println("declaring GuestDao with email and password input...");
                        if (guestDao.loginGuest() == true) {//check credentials OK
                            JOptionPane.showMessageDialog(frame, "login guest successful");
                            frame.switchPanel(new GuestMenu(frame, guestDao.getGuestID()));
                        }
                        else if (guestDao.loginGuest() == false) {
                            JOptionPane.showMessageDialog(frame, "email or password wrong");
                        }
                    }
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "error");
                    e.printStackTrace();
                    
                }
            }
        }
    }
 }

