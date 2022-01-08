package team060.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** GUI class for the host menu
 * @author Zahra Hasan A Alhilal 
 * @author  Christopher Findon 
*/
public class HostMenu extends MenuPanel implements ActionListener {
	private int hostID;

    public HostMenu(MainFrame frame, int hostID) {
    	super(frame);

        this.hostID = hostID;
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Host Menu");
        title.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,24));
        topPanel.add(title);

        //Panel for host options
        JPanel buttonPanel = new JPanel();
        
        JButton myProperties = new JButton("View my properties");
        myProperties.setActionCommand("viewMyPs");
        myProperties.addActionListener(this);
        buttonPanel.add(myProperties);
        
        JButton propertyButton = new JButton("Add new property");
        propertyButton.setActionCommand("addProperty");
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
        if (command.equals("back")) {
            frame.switchPanel(new HomePage(frame));
        }
        else if (command.equals("addProperty")) {
        	frame.switchPanel(new PropertyGui(frame, hostID));
        }
        else if (command.equals("viewMyPs")) {
        	try {
				frame.switchPanel(new PropertyBrowser(frame, hostID, "host"));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }

	public int getHostID() {
		return hostID;
	}	
}
