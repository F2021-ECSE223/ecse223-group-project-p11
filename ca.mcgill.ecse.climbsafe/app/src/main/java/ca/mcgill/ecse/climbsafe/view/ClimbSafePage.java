package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.*;


import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.view.ClimbSafePage.Executable;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;


public class ClimbSafePage extends JFrame{
	private String error;
	private int weekCounter;
	
	
	// UI elements
	  // element for error message
	  private JLabel errorMessage = new JLabel();
	  
	  // member
	  private JTextField memberNameTextField = new JTextField();
	  private JLabel memberNameLabel = new JLabel("Name:");
	  private JTextField memberEmailTextField = new JTextField();
	  private JLabel memberEmailLabel = new JLabel("Email:");
	  private JTextField memberPasswordTextField = new JTextField();
	  private JLabel memberPasswordLabel = new JLabel("Password:");
	  private JTextField memberEmergencyContactTextField = new JTextField();
	  private JLabel memberEmergencyContactLabel = new JLabel("Emergency Contact Number:");
	  private JCheckBox memberGuideCheckBox = new JCheckBox("Guide", false);
	  private JCheckBox memberHotelCheckBox = new JCheckBox("Hotel", false);
	 
	  //increments week numbers by one, replace the 50 with whatever the max num of weeks is when NMC is setup
	  SpinnerModel model = new SpinnerNumberModel(1, 1, 50, 1); 
	  private JSpinner memberWeekNumberSpinner = new JSpinner(model);
	  private JLabel memberWeekNumberLabel = new JLabel("Number of weeks");
	  
	  private JButton addMemberButton = new JButton("Add Member");
	  
	  // idk about TOMember TA said we only needed TOAssignment
	  private JComboBox<TOAssignment> memberToggleList = new JComboBox<>();
	  private JLabel memberToggleLabel = new JLabel("Select Member:");
	  private JButton banButton = new JButton("Toggle Banned");
	  private JButton deleteMemberButton = new JButton("Delete");
	  
	  
	  //guide
	  private JTextField guideNameTextField = new JTextField();
	  private JLabel guideNameLabel = new JLabel("Name:");
	 
	  private JTextField guideEmailTextField = new JTextField();
	  private JLabel guideEmailLabel = new JLabel("Email:");
	  private JTextField guidePasswordTextField = new JTextField();
	  private JLabel guidePasswordLabel = new JLabel("Password:");
	  private JTextField guideEmergencyContactTextField = new JTextField();
	  private JLabel guideEmergencyContactLabel = new JLabel("Emergency Contact Number:");
	  private JButton addGuideButton = new JButton("Add Guide");
	 // private JComboBox<TOAssignment> memberToggleList = new JComboBox<>();
	  private JLabel guideToggleLabel = new JLabel("Select Guide:");
	  private JButton deleteGuideButton = new JButton("Delete");
	  
	  
	  
	  //equipment
	  
	  private JTextField equipmentNameTextField = new JTextField();
	  private JLabel equipmentNameLabel = new JLabel("Equipment Name:");
	  //equipment weekly cost
	  
	  
	  //equipment Bundle
	  
	  private JTextField equipmentBundleTextField = new JTextField();
	  private JLabel equipmentBundleLabel = new JLabel("Equipment Bundle Name:");
	  //bundle discount
	  
	  
	  
	  
	  
	  private void initComponents() {
		  errorMessage.setForeground(Color.RED);
		  errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));
		  
		  
		  
		  
		  //listeners for member
		  addMemberButton.addActionListener(this::addMemberButtonActionPerformed); // Respond to Enter/Return key
		  //memberEmailTextField.addActionListener(this::addDriverButtonActionPerformed);
		 // addDriverButton.addActionListener(this::addDriverButtonActionPerformed);
		  //sickButton.addActionListener(this::sickButtonActionPerformed);
		  //deleteDriverButton.addActionListener(this::deleteDriverButtonActionPerformed);
	  }
	  
	  private void refreshData() {
		    // error
		    errorMessage.setText(error);
		    if (error == null || error.isEmpty()) {
		      // populate page with data
		      
		      memberNameTextField.setText("");
		      memberPasswordTextField.setText("");
		      memberEmailTextField.setText("");
		      memberEmergencyContactTextField.setText("");
		      

		   
		  }
	  }
	  private void addMemberButtonActionPerformed(ActionEvent evt) {
		    // clear error message
		    error = "";
		    
		    //change week counter to proper format

		    callController(() -> ClimbSafeFeatureSet2Controller.registerMember(memberEmailTextField.getText(), memberPasswordTextField.getText(), memberNameTextField.getText(), memberEmergencyContactTextField.getText(), weekCounter, false, false, null, null));

		    // update visuals
		    refreshData();
		  }
	  
	  
	  private void addGuideActionPerformed(ActionEvent evt) {
		  
		  
		  
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
