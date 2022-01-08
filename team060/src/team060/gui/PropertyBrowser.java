package team060.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;

import team060.core.Property;
import team060.dao.PropertyDao;

/** GUI class to browse all properties.
 * @author Christopher Findon
 * @author Zahra Hasan A Alhilal
*/

public class PropertyBrowser extends MenuPanel implements ActionListener {
	private int hostID;
	private int guestID;
    private PropertyDao propertyDao;
    private List<Property> properties;
    private String role;
    private JTable table;
   
    //for enquirer role
    //method to access all available properties 
    public PropertyBrowser(MainFrame frame) throws Exception {
        super(frame);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Property Browser");
        title.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,24));
        topPanel.add(title);
        
        // Table panel
        JPanel tablePanel = new JPanel();
        propertyDao = new PropertyDao(null);
        properties = propertyDao.getAllProperties();
        createTable(tablePanel);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);
        
        add(topPanel);
        add(tablePanel);
        add(buttonPanel);
    }

    //for host and guest role
    //method to access all available properties 
    public PropertyBrowser(MainFrame frame, int id, String role) throws Exception {
        super(frame);
        
        this.hostID = id;
        this.role = role;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Property Browser");
        title.setFont(new Font(title.getFont().getFontName(),Font.PLAIN,24));
        topPanel.add(title);

        JPanel tablePanel = new JPanel();
        if (role == "host") {
	         propertyDao = new PropertyDao(id);
	         properties = propertyDao.getAllPropertiesForHost();
	         createTable(tablePanel);	
        }
        else if (role == "guest") {
	    	 propertyDao = new PropertyDao(null);
	         properties  = propertyDao.getAllProperties();
	         createTable(tablePanel); 
        }

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.setActionCommand("back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(topPanel);
        add(tablePanel);
        add(buttonPanel);
    }

    //method to create table of all properties 
    public void createTable(JPanel panelToAddTo) throws Exception {
    	System.out.println("start createTable()...");
    	String columns[] = {"name", "description", "location", "breakfast"};
    	System.out.println("setting columns");
    	int rowCounts = properties.size();
    	System.out.println("row counts = "+ rowCounts);
    	String[][] rows =  new String[rowCounts][4];
    	System.out.println("setting up string 2x2array.."+ rows.length);
    	//getting the rows details
    	for (int i=0; i<rowCounts; i++) {
    		rows[i][0] = properties.get(i).getName();
    		System.out.println("setting the property name = ..."+ properties.get(i).getName() );
    		rows [i][1] = properties.get(i).getDescription();
    		rows [i][2] = properties.get(i).getLocation();
    		if (properties.get(i).getBreakfast() == true) {
    		rows [i][3] = "yes";
    		}
    		else if (properties.get(i).getBreakfast() == false) {
    			rows [i][3] = "no";
    		}
    	}

    	//setting up the table
        table = new JTable();
        TableModel model = new DefaultTableModel(rows,columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
    	table.setModel(model);
    	TableColumnModel columnModel = table.getColumnModel();
    	columnModel.getColumn(0).setPreferredWidth(200);
    	columnModel.getColumn(1).setPreferredWidth(300);
    	columnModel.getColumn(2).setPreferredWidth(200);
    	columnModel.getColumn(3).setPreferredWidth(200);
    	JScrollPane jes = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
    			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	panelToAddTo.add(jes,BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
       
        if (command.equals("back")) {
            if (role == "host") {
        	    frame.switchPanel(new HostMenu(frame, hostID));
            }
            else if (role == "guest") {
                frame.switchPanel(new GuestMenu(frame, guestID));
            }
            else {
                frame.switchPanel(new HomePage(frame));
            }
        }
    }
  
}
