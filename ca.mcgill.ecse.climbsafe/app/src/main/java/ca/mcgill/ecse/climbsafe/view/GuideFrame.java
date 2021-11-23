package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse.climbsafe.controller.TOGuide;

public class GuideFrame extends JFrame {

	private static final long serialVersionUID = -4426310869335015542L;

	private JLabel errorMessage = new JLabel(); // element for error message

	// Guide Addition labels

	private JTextField guideEmailTextField = new JTextField();
	private JLabel guideEmailLabel = new JLabel("Email:");

	private JTextField guideNameTextField = new JTextField();
	private JLabel guideNameLabel = new JLabel("Name:");

	private JTextField guidePasswordTextField = new JTextField();
	private JLabel guidePasswordLabel = new JLabel("Password:");

	private JTextField guideEmergencyContactTextField = new JTextField();
	private JLabel guideEmergencyContactLabel = new JLabel("Emergency Contact:");

	private JButton addGuideButton = new JButton("Add Guide");

	// Select Guide for update
	private JComboBox<TOGuide> guideList = new JComboBox<>();
	private JLabel guideLabel = new JLabel("Select Guide:");

	private JTextField newGuideNameTextField = new JTextField();
	private JLabel newGuideNameLabel = new JLabel("New Name:");

	private JTextField newGuidPasswordTextField = new JTextField();
	private JLabel newGuidePasswordLabel = new JLabel("New Password:");

	private JTextField newGuideEmergencyContactTextField = new JTextField();
	private JLabel newGuideEmergencyContactLabel = new JLabel("New Emergency Contact:");

	private JButton updateGuideButton = new JButton("Update Guide");

	// Select Guide for Deletion

	private JButton deleteGuideButton = new JButton("Delete Guide");

	private String error = "";

	public GuideFrame() {
		initComponents();
		refreshData();
	}
	
	private void initComponents() {
	    errorMessage.setForeground(Color.RED);
	    errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));
	    
	    //global settings
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Climb Safe Application System");
		
	    //listeners for adding guides
//	    guideNameTextField.addActionListener(this::addGuideButtonActionPerformed);
//	    guideEmailTextField.addActionListener(this::addGuideButtonActionPerformed);
//	    guidePasswordTextField.addActionListener(this::addGuideButtonActionPerformed);
//	    guideEmergencyContactTextField.addActionListener(this::addGuideButtonActionPerformed);
//	    addGuideButton.addActionListener(this::addGuideButtonActionPerformed);
	}
	
	private void refreshData() {
		
		
	}

	// @Override public Component prepareRenderer(TableCellRenderer renderer, int
	// row, int column) {
	// Component c = super.prepareRenderer(renderer, row, column);
	// if (!c.getBackground().equals(getSelectionBackground())) {
	// if (getModel().getValueAt(row, column) instanceof String str) {
//	     c.setBackground(str.endsWith("sick)") ? Color.RED : str.endsWith("repair)") ? Color.YELLOW : Color.WHITE);
	// } else {
//	     c.setBackground(Color.WHITE);
	// }
	// }
	// return c;
	// }
	// };
	//
	// private JScrollPane overviewScrollPane = new JScrollPane(overviewTable);
	//
	// private static final String[] OVERVIEW_COLUMN_NAMES = {"Route", "Bus",
	// "Shift", "Driver"};
	// private static final int HEIGHT_OVERVIEW_TABLE = 200;

}
