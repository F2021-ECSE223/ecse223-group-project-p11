package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.AddtitionalController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.view.GuideFrame.Executable;

public class RegisterGuideFrame extends JFrame {

	private static final long serialVersionUID = 5L;
	
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
	
	private String error = "";	

	public RegisterGuideFrame() {
		initComponents();
		refreshData();
	}
	private void initComponents() {
		errorMessage.setForeground(Color.RED);
		errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));

		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Climb Safe Application System");
		setSize(500, 500);

		// listeners for adding guides
		guideNameTextField.addActionListener(this::addGuideButtonActionPerformed);
		guideEmailTextField.addActionListener(this::addGuideButtonActionPerformed);
		guidePasswordTextField.addActionListener(this::addGuideButtonActionPerformed);
		guideEmergencyContactTextField.addActionListener(this::addGuideButtonActionPerformed);
		addGuideButton.addActionListener(this::addGuideButtonActionPerformed);

		// listeners for updating guides

		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
				.addComponent(horizontalLineBottom)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(guideEmailLabel, 200, 200, 400)
								.addComponent(guideNameLabel).addComponent(guideEmergencyContactLabel)
								.addComponent(guidePasswordLabel))
						.addGroup(layout.createParallelGroup().addComponent(guideEmailTextField, 500, 500, 1000)
								.addComponent(guideNameTextField, 500, 500, 1000)
								.addComponent(guideEmergencyContactTextField)
								.addComponent(guidePasswordTextField, 500, 500, 1000).addComponent(addGuideButton))));

		layout.linkSize(SwingConstants.HORIZONTAL, guideNameTextField, guidePasswordTextField,
				guideEmergencyContactTextField);

		layout.linkSize(SwingConstants.HORIZONTAL, addGuideButton);

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(guideEmailLabel).addComponent(guideEmailTextField))
				.addGroup(layout.createParallelGroup().addComponent(guideNameLabel).addComponent(guideNameTextField))
				.addGroup(layout.createParallelGroup().addComponent(guideEmergencyContactLabel)
						.addComponent(guideEmergencyContactTextField))
				.addGroup(layout.createParallelGroup().addComponent(guidePasswordLabel)
						.addComponent(guidePasswordTextField))
				.addComponent(addGuideButton)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineBottom)));

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	private void refreshData() {
		errorMessage.setText(error);
		if (error == null || error.isEmpty()) {
			// populate page with data

			// add equipment
			guideEmailTextField.setText("");
			guideNameTextField.setText("");
			guidePasswordTextField.setText("");
			guideEmergencyContactTextField.setText("");

			

			ClimbSafe climbSafeApp = ClimbSafeApplication.getClimbSafe();

			String eq = climbSafeApp.getEquipment().toString();

			pack();

		}

	}
	
	private void addGuideButtonActionPerformed(ActionEvent evt) {

		// clear error message
		error = "";
		
	    callController(() -> ClimbSafeFeatureSet3Controller.registerGuide(guideEmailTextField.getText(),
	    		guidePasswordTextField.getText(),  guideNameTextField.getText(), guideEmergencyContactTextField.getText()));
	    
	    // update visuals 
	    refreshData();

	}

	private String callController(Executable executable) {
		try {
			executable.execute();
		} catch (InvalidInputException e) {
			error = e.getMessage();
			return error;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return "";
	}

	@FunctionalInterface
	interface Executable {
		public void execute() throws Throwable;
	}
	
}
