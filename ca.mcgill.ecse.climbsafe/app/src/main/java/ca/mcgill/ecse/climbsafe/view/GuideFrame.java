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
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOGuide;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.view.EquipmentFrame.Executable;

public class GuideFrame extends JFrame {

	private static final long serialVersionUID = 1;

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

	private JTextField newGuidePasswordTextField = new JTextField();
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

		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Climb Safe Application System");

		// listeners for adding guides
		guideNameTextField.addActionListener(this::addGuideButtonActionPerformed);
		guideEmailTextField.addActionListener(this::addGuideButtonActionPerformed);
		guidePasswordTextField.addActionListener(this::addGuideButtonActionPerformed);
		guideEmergencyContactTextField.addActionListener(this::addGuideButtonActionPerformed);
		addGuideButton.addActionListener(this::addGuideButtonActionPerformed);

		// listeners for updating guides
		newGuideNameTextField.addActionListener(this::addGuideButtonActionPerformed);
		newGuidePasswordTextField.addActionListener(this::addGuideButtonActionPerformed);
		newGuideEmergencyContactTextField.addActionListener(this::addGuideButtonActionPerformed);
		updateGuideButton.addActionListener(this::addGuideButtonActionPerformed);

		deleteGuideButton.addActionListener(this::deleteGuideButtonActionPerformed);

		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
				.addComponent(horizontalLineTop).addComponent(horizontalLineBottom)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(guideEmailLabel)
								.addComponent(guideNameLabel).addComponent(guideEmergencyContactLabel)
								.addComponent(guidePasswordLabel).addComponent(guideLabel)
								.addComponent(newGuideNameLabel).addComponent(newGuidePasswordLabel)
								.addComponent(newGuideEmergencyContactLabel)
								.addGroup(layout.createParallelGroup().addComponent(guideEmailTextField, 200, 200, 400)
										.addComponent(guideNameTextField, 200, 200, 400)
										.addComponent(guideEmergencyContactTextField).addComponent(guidePasswordTextField, 200, 200, 400)
										.addComponent(addGuideButton)
										.addComponent(newGuideNameTextField, 200, 200, 400)
										.addComponent(newGuidePasswordTextField, 200, 200, 400)
										.addComponent(newGuideEmergencyContactTextField, 200, 200, 400)
										.addComponent(updateGuideButton).addComponent(deleteGuideButton)))));

		layout.linkSize(SwingConstants.HORIZONTAL, addGuideButton, guideNameTextField, guidePasswordTextField,
				guideEmergencyContactTextField);
		layout.linkSize(SwingConstants.HORIZONTAL, updateGuideButton, newGuideNameTextField, newGuidePasswordTextField,
				newGuideEmergencyContactTextField);
		layout.linkSize(SwingConstants.HORIZONTAL, deleteGuideButton);
		//.addComponent(errorMessage)
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(guideEmailLabel).addComponent(guideEmailTextField))
				.addGroup(layout.createParallelGroup().addComponent(guideNameLabel).addComponent(guideNameTextField))
				.addGroup(layout.createParallelGroup().addComponent(guideEmergencyContactLabel).addComponent(guideEmergencyContactTextField))
				.addGroup(layout.createParallelGroup().addComponent(guidePasswordLabel).addComponent(guidePasswordTextField))
				.addGroup(layout.createParallelGroup().addComponent(addGuideButton))
				.addGroup(layout.createParallelGroup().addComponent(guideLabel))
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineTop))
				.addGroup(layout.createParallelGroup().addComponent(newGuideNameLabel)
						.addComponent(newGuideNameTextField))
				.addGroup(layout.createParallelGroup().addComponent(newGuidePasswordLabel)
						.addComponent(newGuidePasswordTextField))
				.addGroup(layout.createParallelGroup().addComponent(newGuideEmergencyContactLabel)
						.addComponent(newGuideEmergencyContactTextField))
				.addGroup(layout.createParallelGroup().addComponent(updateGuideButton))
				.addGroup(layout.createParallelGroup().addComponent(deleteGuideButton))
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineBottom))
				);

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

			// update equipment
			newGuideNameTextField.setText("");
			newGuidePasswordTextField.setText("");
			newGuideEmergencyContactTextField.setText("");

			// delete equipment

			// var lists = List.of(equipmentToAddList, equipmentToUpdateList,
			// equipmentToDeleteList);

			// lists.forEach(JComboBox:: removeAllItems);

			var lists = List.of(guideList);
			lists.forEach(JComboBox::removeAllItems);

			ClimbSafe climbSafeApp = ClimbSafeApplication.getClimbSafe();

			String eq = climbSafeApp.getEquipment().toString();

		    AddtitionalController.getGuides().forEach(guideList::addItem);

			lists.forEach(list -> list.setSelectedIndex(-1));

			pack();

		}

	}
	
	
	private void addGuideButtonActionPerformed(ActionEvent evt) {
	    
//	    // clear error message
//	    error = "";
//	    
//	    callController(() -> ClimbSafeFeatureSet4Controller.addEquipment(equipmentNameTextField.getText(),
//	       getNumberFromField(equipmentWeightTextField, error) ,  getNumberFromField(equipmentPricePerWeekTextField,error)));
//	    
//	    // update visuals 
//	    refreshData();
	    
	  }
	  
	  private void updateGuuideButtonActionPerformed(ActionEvent evt) {
	    
	    error = "";
	    
	    var selectedGuide = (TOGuide) guideList.getSelectedItem();
	    if(selectedGuide == null) {
	      error = "Guide needs to be selected to update it!";
	    } else if (error.isEmpty()) {
	      callController(() -> ClimbSafeFeatureSet3Controller.updateGuide(selectedGuide.getEmail(), 
	          newGuidePasswordTextField.getText(), newGuideNameTextField.getText(), newGuideEmergencyContactTextField.getText()));
	    }
	    refreshData();
	  }
	  
	  private void deleteGuideButtonActionPerformed(ActionEvent evt) {
	    
	    error = ""; 
	    
	    var selectedGuide = (TOGuide) guideList.getSelectedItem();
	    if(selectedGuide == null) {
	      error = "Guide has to be selected for deletion!";
	    } else if (error.isEmpty()){
	      callController(() -> ClimbSafeFeatureSet1Controller.deleteGuide(selectedGuide.getEmail()));
	    }
	  }

	/**
	 * Calls the controller and sets the error message.
	 * 
	 * @param executable a controller call preceded by "() -> ", eg,<br>
	 * @return
	 */
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
