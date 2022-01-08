package team060.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import team060.core.Address;
import team060.core.Property;
import team060.dao.AddressDao;
import team060.dao.PropertyDao;

/** GUI class for adding new properties
 * @author Christopher Findon
 * @author Zahra Hasan A Alhilal
*/
public class PropertyGui extends MenuPanel implements ActionListener {
	private Form addPropertyForm; 
	private int hostID;
	private Address add;
	private enum Place {village, city, town};
	private enum Option {YES, NO};
	private JComboBox<Place> placeNameComboBox;
	private JComboBox<Option> breakfastOptions;
	private FormField houseNameOrNumberField;
	private FormField streetNameField;
	private FormField postcodeField;
	private FormField propertyName;
	private FormField propertyDescription;
	private FormField propertyLocation;
	
	public PropertyGui(MainFrame frame, int hostID) {
		super(frame);

		this.hostID = hostID;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Add Property");
        title.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,24));
        topPanel.add(title);

		// Form Panel
		JPanel formPanel = new JPanel();
		addPropertyForm = new Form(formPanel);
			
		// Property's address
        JPanel addressPanel = new JPanel();
        addPropertyForm.addSectionPanel(addressPanel, "Enter the property's address:");
        houseNameOrNumberField = new FormField("House Name or Number",VerifyType.notEmpty);
        addPropertyForm.addFormField(houseNameOrNumberField, addressPanel);
        streetNameField = new FormField("Street Name",VerifyType.notEmpty);
        addPropertyForm.addFormField(streetNameField, addressPanel);
        placeNameComboBox = new JComboBox<Place>(Place.values());
        addPropertyForm.addComboBox(placeNameComboBox, "Enter your place name: ",  addressPanel);
        postcodeField = new FormField("Postcode",VerifyType.notEmpty);
        addPropertyForm.addFormField(postcodeField, addressPanel);
		
		// Basic property information
		JPanel propertyPanel  = new JPanel();
		addPropertyForm.addSectionPanel(propertyPanel, "Enter information about your property:");
		propertyName = new FormField("Property Name", VerifyType.notEmpty);
		addPropertyForm.addFormField(propertyName, propertyPanel);
		propertyDescription = new FormField("Property Description", VerifyType.notEmpty);
		addPropertyForm.addFormField(propertyDescription, propertyPanel);
		propertyLocation = new FormField("Property Location", VerifyType.notEmpty);
		addPropertyForm.addFormField(propertyLocation, propertyPanel);
		breakfastOptions = new JComboBox<Option>(Option.values());
		addPropertyForm.addComboBox(breakfastOptions, "Is breakfast provided?", propertyPanel);
		
		// Button panel
        JPanel buttonPanel = new JPanel();

        JButton loginButton = new JButton("Add Property");
        loginButton.setActionCommand("addProperty");
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

	//method to create the address object from host inputs 
	public Address createAddress() {
		String house = houseNameOrNumberField.getText();
		String street = streetNameField.getText();
		String place =  String.valueOf(placeNameComboBox.getSelectedItem());
		String postCode = postcodeField.getText();
		add = new Address(house,street,place,postCode);
		System.out.println("calling createAddress()...address created= "+ add);
		return add;
	}

	//method to register the address and return the addressID 
	public int getAddressID() throws Exception {
		Address add = createAddress();
		int addID = 0;
		AddressDao adddao = new AddressDao(add);
		//check if id already registered in Property table or not
		if (adddao.addressInProperty() == false) {
			adddao.addAddress();
			addID = adddao.getAddressId(); 
		}
		else if (adddao.addressInProperty() == true) {
			//if the address already registered, return 0 because 0 can never be an id in the DB table
			//auto increment starts from 1
			addID = 0;
		}
		System.out.println("addressID already registered with another property");
		return addID;
	}

	//method to create the property object using hostID and addressID
	public Property createProperty() throws Exception {
		int addID = getAddressID();
		String name = propertyName.getText();
		String description = propertyDescription.getText();
		String location = propertyLocation.getText();
		Boolean breakfast = (breakfastOptions.getSelectedItem() == Option.YES);
		Property p = new Property(this.hostID, addID, name, description, location, breakfast);
		return p;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command == "addProperty") {
			if (addPropertyForm.verifyFields()) {
				try {
					//create the property object using host input
					Property p = createProperty();
					PropertyDao propertyDao = new PropertyDao(p);
					if (p.getAddID() != 0 ) { //check addressID valid
							if (propertyDao.addProperty() == true) { //check registration process OK.
								JOptionPane.showMessageDialog(frame, 
										"adding property successful!");
								frame.switchPanel(new HostMenu(frame,hostID));
							}		
					}
					else if (p.getAddID() == 0 ) { //Registration process not OK
						JOptionPane.showMessageDialog(frame, 
								"adding property failed! try different address.");
					}
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else if (command == "back") {
			frame.switchPanel(new HostMenu(frame, hostID));
		}
	}
	
	public int getHostID() {
		return hostID;
	}

	public void setHostID(int hostID) {
		this.hostID = hostID;
	}

}
