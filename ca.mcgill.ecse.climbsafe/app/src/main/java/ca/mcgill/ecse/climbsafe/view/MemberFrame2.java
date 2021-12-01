package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.time.*;

import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.view.*;
import ca.mcgill.ecse.climbsafe.view.MemberFrame.Executable;
import ca.mcgill.ecse.climbsafe.controller.*;

public class MemberFrame2 extends JFrame {

	private String error;

	public String currentUser = "placeholder";

	List<String> selectedItemNames = new ArrayList<String>();

	List<Integer> selectedItemQuantities = new ArrayList<Integer>();

	List<Integer> selectedItemCost = new ArrayList<Integer>();

	private static final long serialVersionUID = -44263693998015542L;

	private JLabel errorMessage = new JLabel();

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
	  
	  private void refreshEquipment() {
		  equipmentNumberTextField.setText("");
		  
	  }
	  

	  private void refreshOverview() {
		  errorMessage.setText(error);
		    if (error == null || error.isEmpty()) {
		  var overviewDtm = new DefaultTableModel(0, 0);
		    overviewDtm.setColumnIdentifiers(OVERVIEW_COLUMN_NAMES);
		    equipmentOverview.setModel(overviewDtm);
		    if(equipmentAvailableList.getSelectedItem()!=null) {
		    //String equipmentNameText = equipmentAvailableList.getSelectedItem().toString();
		    DefaultTableModel model = (DefaultTableModel) equipmentOverview.getModel();
		    for(int i = 0 ; i < selectedItemNames.size(); i++) {
		    model.addRow(new Object[] {selectedItemNames.get(i),selectedItemQuantities.get(i) , selectedItemCost.get(i)});
		    }}
		  equipmentAvailableList.setSelectedIndex(0);
		    }
		  
	  }
	  
	  
	  private void updateMemberButtonActionPerformed(ActionEvent evt) {
		  error = "";
		  
		  if(memberPasswordTextField.getText().equals("") || memberNameTextField.getText().equals("") || memberEmergencyContactTextField.getText().equals("") || memberWeekNumberTextField.getText().equals("")) {
		    	error = "Please fill all Fields! ";}
		    
		    
		    int weekNumber = getNumberFromField(memberWeekNumberTextField, "Must be a number!");
		    error.trim();
		    
		    if(error.isEmpty()) {
		    	callController(() -> ClimbSafeFeatureSet2Controller.updateMember(currentUser, memberPasswordTextField.getText(), memberNameTextField.getText(), memberEmergencyContactTextField.getText(), weekNumber, memberGuideCheckBox.isSelected(), memberHotelCheckBox.isSelected(), selectedItemNames, selectedItemQuantities));
		    	
		    	selectedItemNames.clear();
				selectedItemQuantities.clear();
				selectedItemCost.clear();
				refreshOverview();
		  
		  
	  }
		    refreshData();
		    refreshEquipment();
	  }
	  
	  private void deleteItemButtonActionPerformed(ActionEvent evt) {
		  error = "";
		  String equipmentNameText2 = equipmentAvailableList.getSelectedItem().toString();
		  if(equipmentNameText2.equals("---------")) {
			  error = "Select an Item to remove it";
		  }
		  
		  if(!selectedItemNames.contains(equipmentNameText2)) {
			  error = "Add an Item before Removing";
		  }
		  
		  errorMessage.setText(error);
		  if(error.isEmpty()) {
			  for(int j = 0; j < selectedItemNames.size(); j++) {
				  if(selectedItemNames.get(j).equals(equipmentNameText2)) {
					  selectedItemNames.remove(j);
					  selectedItemQuantities.remove(j);
					  selectedItemCost.remove(j);
				  }
				  
			  }
			  
			  refreshOverview();
			  refreshEquipment();
		  }
		  
		  
	  }
	  
	  private void addEquipmentButtonActionPerformed(ActionEvent evt) {
		  
			 
		  error = "";
		  String equipmentNameText2 = equipmentAvailableList.getSelectedItem().toString();
		  
		  int equipmentNumber = getNumberFromField(equipmentNumberTextField, "Must be a number!");
		    error.trim();
		  
		  
		  
		  if(equipmentNumberTextField.getText().equals("")) {
			  error = "Fill Number Field before adding";
		  }
		  
		  if(equipmentNameText2.equals("---------")) {
			  error = "Select an Item in order to add it to your booking";
		  }
		  
		  errorMessage.setText(error);
		  
		  if(error.isEmpty()) {
			  
			  if(!selectedItemNames.contains(equipmentNameText2)) {
			  selectedItemNames.add(equipmentNameText2);
			  selectedItemQuantities.add(equipmentNumber);
			  selectedItemCost.add(AddtitionalController.getItemCost(equipmentNameText2));
			  refreshOverview();
			  refreshEquipment();
			  
			  
	  }
			  else{
				  error = "Item already selected";
				  refreshData();}}
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
		  
		  callController(() -> ClimbSafeFeatureSet1Controller.deleteMember(currentUser));
		  
		  
		  
		  LoginFrame loginFrame = new LoginFrame();
	      loginFrame.setVisible(true);
	      dispose();
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
