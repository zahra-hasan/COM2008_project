package team060.gui;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    /**
	 * @author Christopher Findon 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel currentPanel;
    private Container contentPane = getContentPane();

    public MainFrame() {
        super("HomeBreaks");

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setMinimumSize(new Dimension(screenSize.height,screenSize.width/3));
        setMaximumSize(new Dimension(screenSize.height,screenSize.width/3));
        setLocation(screenSize.width/4, screenSize.height/4);

        currentPanel = new HomePage(this);
        contentPane.add(currentPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        setVisible(true);
    }

    public void switchPanel(JPanel newPanel) {
        contentPane.add(newPanel);
        contentPane.remove(currentPanel);
        revalidate();
        repaint();
        currentPanel = newPanel;
    }

    public static void main(String args[]) {
        new MainFrame();
    }
}
