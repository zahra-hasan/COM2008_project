package team060.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import team060.core.Address;
import team060.core.Guest;
import team060.core.Host;
import team060.dao.GuestDao;
import team060.dao.HostDao;

/** GUI class for registering new accounts (host/guest)
 * @author Zahra Hasan A Alhilal 
 * @author Christopher Findon 
*/
public class Register extends MenuPanel implements ActionListener {
    
	private static final long serialVersionUID = 1L;
    private enum Role {Host,Guest};
    private enum Title {Mr, Mrs, Miss, Ms};
    private enum Place {village, city, town}
    private Form registerForm;
    private FormField emailField;
    private FormField passwordField;
    private FormField phoneNumField;
    private FormField displayNameField;
    private FormField forenameField;
    private FormField surnameField;
    private FormField houseNameOrNumberField;
    private FormField streetNameField;
    private FormField postcodeField;
    private JComboBox<Role> roleComboBox;
    private JComboBox<Place> placeNameComboBox;
    private JComboBox<Title> titleComboBox;
    private Host host;
    private Guest guest;
    private Address add;
    
    public Register(MainFrame frame) {
        super(frame);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Register");
        title.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,24));
        topPanel.add(title);

        // Panel for the form
        JPanel formPanel = new JPanel();
        registerForm = new Form(formPanel);
        
        // Account panel
        JPanel accountPanel = new JPanel();
        registerForm.addSectionPanel(accountPanel, "Enter your account details:");

        roleComboBox = new JComboBox<Role>(Role.values());
        registerForm.addComboBox(roleComboBox, "Enter your role:", accountPanel);
	    emailField = new FormField("Email",VerifyType.email);
        registerForm.addFormField(emailField, accountPanel);
	    passwordField = new FormField("Password",VerifyType.password);
        registerForm.addFormField(passwordField, accountPanel);
        displayNameField = new FormField("Display Name",VerifyType.notEmpty);
	    registerForm.addFormField(displayNameField, accountPanel);

	    // Personal information Panel
        JPanel personalPanel = new JPanel();
        registerForm.addSectionPanel(personalPanel, "Enter your personal information:");

        titleComboBox = new JComboBox<Title>(Title.values());
        registerForm.addComboBox(titleComboBox, "Enter your title:", personalPanel);
        phoneNumField = new FormField("Phone Number",VerifyType.phoneNumber);
        registerForm.addFormField(phoneNumField, personalPanel);
        forenameField = new FormField("Forename",VerifyType.notEmpty);
        registerForm.addFormField(forenameField, personalPanel);
        surnameField = new FormField("Surname",VerifyType.notEmpty);
        registerForm.addFormField(surnameField, personalPanel);
       
        // Address panel
        JPanel addressPanel = new JPanel();
        registerForm.addSectionPanel(addressPanel, "Enter your personal address:");

        houseNameOrNumberField = new FormField("House Name or Number",VerifyType.notEmpty);
        registerForm.addFormField(houseNameOrNumberField, addressPanel);
        streetNameField = new FormField("Street Name",VerifyType.notEmpty);
        registerForm.addFormField(streetNameField, addressPanel);
        placeNameComboBox = new JComboBox<Place>(Place.values());
        registerForm.addComboBox(placeNameComboBox, "Enter your place name:",  addressPanel);
        postcodeField = new FormField("Postcode",VerifyType.notEmpty);
        registerForm.addFormField(postcodeField, addressPanel);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();

        JButton registerButton = new JButton("Register");
        registerButton.setActionCommand("register");
        registerButton.addActionListener(this);
        buttonPanel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(topPanel);
        add(formPanel);
        add(buttonPanel);
    }

    //method to create address object from user inputs
    public Address createAddress() {
    	String house = houseNameOrNumberField.getText();
    	String street = streetNameField.getText();
    	String place =  String.valueOf(placeNameComboBox.getSelectedItem());
    	String postCode = postcodeField.getText();
    	add = new Address(house,street,place,postCode);
    	System.out.println("calling createAddress()...address created= "+ add);
    	return add;
    }
    
    //method to create Host object from the user inputs
    public Host createHost() {
    	String email = emailField.getText();
    	String password = passwordField.getText();
    	String phoneNum = phoneNumField.getText();
    	String title = String.valueOf(titleComboBox.getSelectedItem());
    	String displayName = displayNameField.getText();
    	String forename = forenameField.getText();
    	String surname = surnameField.getText();
    	Address add = createAddress();
    	//create the Host object
    	host = new Host(email,password,phoneNum,title, forename, surname, add, displayName, false);
    	System.out.println("calling createHost()...host created= "+ host);
    	return host;
    }

    //method to create Guest object from the user inputs
    public Guest createGuest() {
    	String email = emailField.getText();
    	String password = passwordField.getText();
    	String phoneNum = phoneNumField.getText();
    	String title = String.valueOf(titleComboBox.getSelectedItem());
    	String displayName = displayNameField.getText();
    	String forename = forenameField.getText();
    	String surname = surnameField.getText();
    	Address add = this.createAddress();
    	//create the Guest object
    	guest = new Guest(email,password,phoneNum,title, forename, surname,add ,displayName);
    	System.out.println("calling createGuest()...guest created= "+ guest);
    	return guest;
    }
    
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("back")) {
            frame.switchPanel(new HomePage(frame));
        }
        else if (command.equals("register")) {
            if (registerForm.verifyFields()) {
                //Registering host
                if (roleComboBox.getSelectedItem() == Role.Host) {
                    System.out.println("creating a host instance using user inputs..");
                    Host temp = createHost();
                    try {
                        HostDao hostDao = new HostDao(temp);
                        System.out.println("declaring HostDao using the host instance..");
                        if (hostDao.registerHost()== true) {//check registration process OK
                            JOptionPane.showMessageDialog(frame, "register host successful!");
                        frame.switchPanel(new HomePage(frame));
                        }
                        else if (hostDao.registerHost()== false) {
                            JOptionPane.showMessageDialog(frame, "A host with same email or address "
                                    + "already registered, please try different email "
                                    + "or address or register as a guest");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(frame, "error");
                        e.printStackTrace();
                    }	
                }
                //Registering guest
                else {
                    Guest temp = createGuest();
                    System.out.println("creating a guest instance using user inputs..");
                    try {
                        GuestDao guestDao = new GuestDao(temp);
                        System.out.println("declaring GuestDao using the guest instance..");
                        if (guestDao.registerGuest()== true) {//check registration process OK
                            JOptionPane.showMessageDialog(frame, "register guest successful!");
                        frame.switchPanel(new HomePage(frame));
                        }
                        else if (guestDao.registerGuest()== false) {
                            JOptionPane.showMessageDialog(frame, "A guest with the same email or address "
                                    + "already registered, please try different email "
                                    + "or address or register as a host");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(frame, "error");
                        e.printStackTrace();
                    }	
                }
            }
        }
    }
}
