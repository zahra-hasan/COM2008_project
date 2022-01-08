package team060.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** GUI for the home page
 * @author Zahra Hasan A Alhilal
 * @author Christopher Findon 
*/
public class HomePage extends MenuPanel implements ActionListener {
    public HomePage(MainFrame frame) {
        super(frame);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Home");
        title.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,24));
        topPanel.add(title);
        
        // Button panel
        JPanel buttonPanel = new JPanel();

        JButton registerButton = new JButton("Register");
        registerButton.setActionCommand("register");
        registerButton.addActionListener(this);
        buttonPanel.add(registerButton);
        JButton loginButton = new JButton("Login");
        loginButton.setActionCommand("login");
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);

        JButton enquirerButton = new JButton("View properties");
        enquirerButton.setActionCommand("properties");
        enquirerButton.addActionListener(this);
        buttonPanel.add(enquirerButton);

        JButton quitButton = new JButton("Quit");
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(this);
        buttonPanel.add(quitButton);

        add(topPanel);
        add(buttonPanel);
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("register")) {
            frame.switchPanel(new Register(frame));
        }
        else if (command.equals("login")) {
            frame.switchPanel(new Login(frame));
        }
        else if (command.equals("properties")) {
            try {
				frame.switchPanel(new PropertyBrowser(frame));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        else if (command.equals("quit")) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
 
}
