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

import java.sql.*;
import java.time.*;

import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.view.*;
import ca.mcgill.ecse.climbsafe.view.MemberFrame.Executable;
import ca.mcgill.ecse.climbsafe.controller.*;

public class MemberFrame2 extends JFrame{
	
	private String error;
	
	private String currentUser;
	
	private static final long serialVersionUID = -44263693998015542L;
	
	private JLabel errorMessage = new JLabel();
	  
	  // member
	  private JTextField memberNameTextField = new JTextField();
	  private JLabel memberNameLabel = new JLabel("New Name:");
	 
	  
	  private JTextField memberPasswordTextField = new JTextField();
	  private JLabel memberPasswordLabel = new JLabel("New Password:");
	  
	  private JTextField memberEmergencyContactTextField = new JTextField();
	  private JLabel memberEmergencyContactLabel = new JLabel("New Emergency Contact Number:");
	  
	  private JCheckBox memberGuideCheckBox = new JCheckBox("Guide", false);
	  private JCheckBox memberHotelCheckBox = new JCheckBox("Hotel", false);
	  
	  private JTextField memberWeekNumberTextField = new JTextField();
	  private JLabel memberWeekNumberLabel = new JLabel("New Number of Weeks");
	  
	  private JButton updateMemberButton = new JButton("Update Account");
	  
	  private JButton deleteMemberButton = new JButton("Delete Account");
	  
	  public MemberFrame2() {
		  refreshData();
		  initComponents();
	  }
	  
	  
	  private void initComponents() {
		  errorMessage.setForeground(Color.RED);
		  errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));
		  
		  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		  setTitle("ClimbSafe Application System");
		  
		  deleteMemberButton.addActionListener(this::deleteMemberButtonActionPerformed);
		  
		  updateMemberButton.addActionListener(this::updateMemberButtonActionPerformed);
	  
		  
		  JSeparator horizontalLineTop= new JSeparator();
		  JSeparator horizontalLineBottom = new JSeparator();
		  
		  GroupLayout layout = new GroupLayout(getContentPane());
		  getContentPane().setLayout(layout);
		  layout.setAutoCreateGaps(true);
		  layout.setAutoCreateContainerGaps(true);
		  layout.setHorizontalGroup(
		        layout.createParallelGroup()
		             .addComponent(errorMessage).addComponent(horizontalLineTop)
		             .addComponent(horizontalLineBottom)
		             .addGroup(layout.createSequentialGroup()
		                 
		            	 .addGroup(layout.createParallelGroup()
		                		 .addComponent(memberNameLabel).addComponent(memberPasswordLabel)
		                		 .addComponent(memberEmergencyContactLabel).addComponent(memberWeekNumberLabel).addComponent(memberGuideCheckBox)
		                		 .addComponent(memberHotelCheckBox))
		                 
		                 .addGroup(layout.createParallelGroup()
		                		 .addComponent(memberNameTextField, 200, 200, 400)
		                		 .addComponent(memberPasswordTextField, 200, 200, 400)
		                		 .addComponent(memberEmergencyContactTextField, 200, 200, 400)
		                		 .addComponent(memberWeekNumberTextField, 200, 200, 400)
		                		 .addComponent(updateMemberButton)
		                		 .addComponent(deleteMemberButton)
		               
		                		 )));
		    
		    layout.linkSize(SwingConstants.HORIZONTAL, updateMemberButton, deleteMemberButton,memberNameTextField,memberPasswordTextField, memberEmergencyContactTextField, memberWeekNumberTextField, memberGuideCheckBox, memberHotelCheckBox);
		    
		    layout.setVerticalGroup(
		    		layout.createSequentialGroup()
		            .addComponent(errorMessage)
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(memberNameLabel).addComponent(memberNameTextField)
		            		)
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(memberPasswordLabel).addComponent(memberPasswordTextField)
		            		)
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(memberEmergencyContactLabel).addComponent(memberEmergencyContactTextField)
		            		)
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(memberWeekNumberLabel).addComponent(memberWeekNumberTextField)
		            		)
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(memberGuideCheckBox)
		            		)
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(memberHotelCheckBox)
		            		)
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(updateMemberButton)
		            		)
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(horizontalLineTop)
		            		)
		            
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(deleteMemberButton)
		            		)
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(horizontalLineBottom)
		            		)
		        
		            
		            );

		    
		                 
		                 
		                		 
		                		 
		                		 
		    
		    pack();
		    setLocationRelativeTo(null); // set window location to be in the center of the screen
		    setResizable(false);
		    setVisible(true);                		 
	      
	      
		  
	  

}
	  
	  
	  private void refreshData() {
		  errorMessage.setText(error);
		    if (error == null || error.isEmpty()) {
		  memberNameTextField.setText("");
	      memberPasswordTextField.setText("");
	      memberEmergencyContactTextField.setText("");
	      memberWeekNumberTextField.setText("");
	      memberHotelCheckBox.setSelected(false);
	      memberGuideCheckBox.setSelected(false);
		  
	  }}
	  
	  
	  private void updateMemberButtonActionPerformed(ActionEvent evt) {
		  error = "";
		  
		  if(memberPasswordTextField.getText().equals("") || memberNameTextField.getText().equals("") || memberEmergencyContactTextField.getText().equals("") || memberWeekNumberTextField.getText().equals("")) {
		    	error = "Please fill all Fields! ";}
		    
		    
		    int weekNumber = getNumberFromField(memberWeekNumberTextField, "Must be a number!");
		    error.trim();
		    
		    if(error.isEmpty()) {
		    	callController(() -> ClimbSafeFeatureSet2Controller.updateMember(currentUser, memberPasswordTextField.getText(), memberNameTextField.getText(), memberEmergencyContactTextField.getText(), weekNumber, memberGuideCheckBox.isSelected(), memberHotelCheckBox.isSelected(), null, null));
		  
		  
	  }
	  }
		    
	  private int getNumberFromField(JTextField field, String errorMessage) {
		    try {
		      return Integer.parseInt(field.getText());
		    } catch (NumberFormatException e) {
		    	if(error.equals("")) {
		      error += errorMessage;}
		    }
		    return 0;
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
