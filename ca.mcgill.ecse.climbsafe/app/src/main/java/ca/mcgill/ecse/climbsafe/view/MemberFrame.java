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


import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.view.*;
import ca.mcgill.ecse.climbsafe.controller.*;


public class MemberFrame extends JFrame{
	private String error;
	private int weekCounter;
	private int maxNumOfWeeks = ClimbSafeApplication.getClimbSafe().getNrWeeks();
	
	private static final long serialVersionUID = -4426369335015542L;
	
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
	  SpinnerModel model = new SpinnerNumberModel(1, 1, maxNumOfWeeks, 1); 
	  private JSpinner memberWeekNumberSpinner = new JSpinner(model);
	  private JLabel memberWeekNumberLabel = new JLabel("Number of weeks");
	  
	  private JButton addMemberButton = new JButton("Add Member");
	  
	 
	  private JComboBox<TOMember> memberToggleList = new JComboBox<>();
	  private JLabel memberToggleLabel = new JLabel("Select Member:");
	  
	  private JButton banButton = new JButton("Toggle Banned");
	  private JButton deleteMemberButton = new JButton("Delete");
	  
	  private JTextField newMemberNameTextField = new JTextField();
	  private JLabel newMemberNameLabel = new JLabel("New Name:");

	  private JTextField newMemberPasswordTextField = new JTextField();
	  private JLabel newMemberPasswordLabel = new JLabel("New Password:");

	  private JTextField newMemberEmergencyContactTextField = new JTextField();
	  private JLabel newMemberEmergencyContactLabel = new JLabel("New Emergency Contact:");
	 
	  
	  public MemberFrame() {
		  refreshData();
		  initComponents();
	  }
	  
	  
	  private void initComponents() {
		  errorMessage.setForeground(Color.RED);
		  errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));
		  
		  
		  
		  
		  //listeners for member
		  addMemberButton.addActionListener(this::addMemberButtonActionPerformed); // Respond to Enter/Return key
		  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		  setTitle("ClimbSafe Application System");
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
		      //reset week counter to 1
		      //reset guide and hotel to false
		      //reset all selected equipment
		      
		      newMemberNameTextField.setText("");
		      newMemberPasswordTextField.setText("");
		      newMemberEmergencyContactTextField.setText("");
		      
		      

		   
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
	  
	  
	  private void deleteMemberButtonActionPerformed(ActionEvent evt) {
		  
		  error = "";
		  
		  
		  
		  
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
