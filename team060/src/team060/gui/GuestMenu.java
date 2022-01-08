package team060.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** GUI class for the guest menu
 * @author Zahra Hasan A Alhilal 
 * @author Christopher Findon 
*/
public class GuestMenu extends MenuPanel implements ActionListener {
	private int guestID;

    public GuestMenu(MainFrame frame,int guestID) {
        super(frame);

        this.guestID = guestID;
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Guest Menu");
        title.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,24));
        topPanel.add(title);

        //Panel for host options
        JPanel buttonPanel = new JPanel();
        JButton propertyButton = new JButton("View properties");
        propertyButton.setActionCommand("properties");
        propertyButton.addActionListener(this);
        buttonPanel.add(propertyButton);

        JButton backButton = new JButton("Logout");
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(topPanel);
        add(buttonPanel);
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("properties")) {
        	try {
				frame.switchPanel(new PropertyBrowser(frame, guestID, "guest"));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        else if (command.equals("back")) {
            frame.switchPanel(new HomePage(frame));
        }
        
    }
}
