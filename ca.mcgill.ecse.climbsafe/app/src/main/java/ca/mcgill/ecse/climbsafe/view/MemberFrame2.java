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

public class MemberFrame2 extends JFrame{
	
	private String error;
	
	public String currentUser = "placeholder";
	
	List<String> selectedItemNames = new ArrayList<String>();
	
	List<Integer> selectedItemQuantities = new ArrayList<Integer>();
	
	List<Integer> selectedItemCost = new ArrayList<Integer>();
	
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
	  
	  private JComboBox<String> equipmentAvailableList = new JComboBox<>();
	  private JButton addEquipmentButton = new JButton("Add Selected Item");
	  private JLabel equipmentLabel = new JLabel("Select Equipment");
	  private JTextField equipmentNumberTextField = new JTextField();
	  private JLabel equipmentNumberLabel = new JLabel("Number of Selected Item");
	  
	  //viewing the selected equipment in table for registering member
	  
	  private JButton deleteItemButton = new JButton("Remove Selected Item");
	  //private JComboBox<String> selectedItemsList = new JComboBox<>();
	 
	  
	  
	  private JTable equipmentOverview = new JTable(new DefaultTableModel()) {
		  private static final long serialVersionUID = 99L;
	  };
	  
	  
	  private JScrollPane overviewScrollPane = new JScrollPane(equipmentOverview);
	  
	  private static final String[] OVERVIEW_COLUMN_NAMES = {"Booked Item", "Number Selected", "Individual Item Cost"};
	  private static final int HEIGHT_OVERVIEW_TABLE = 200;
	  
	  public MemberFrame2(String email) {
		  this.currentUser = email;
		  refreshData();
		  initComponents();
		  
	  }
	  
	  
	  private void initComponents() {
		  errorMessage.setForeground(Color.RED);
		  errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));
		  add(overviewScrollPane);
		    overviewScrollPane.setPreferredSize(new Dimension(equipmentOverview.getPreferredSize().width, HEIGHT_OVERVIEW_TABLE));
		    overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		  
		  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		  setTitle("ClimbSafe Application System");
		  

		  
		  
		  List<String>systemEquipment = AddtitionalController.getEquipmentStrings();
		  List<String>systemBundles = AddtitionalController.getBundleStrings();
		  
		  equipmentAvailableList.addItem("---------");
		  
		  for(String i : systemEquipment) {
		  equipmentAvailableList.addItem(i);}
		  
		  for(String j : systemBundles) {
			  equipmentAvailableList.addItem(j);
		  }
		  
		  
		  deleteMemberButton.addActionListener(this::deleteMemberButtonActionPerformed);
		  
		  updateMemberButton.addActionListener(this::updateMemberButtonActionPerformed);
		  
		  addEquipmentButton.addActionListener(this::addEquipmentButtonActionPerformed);
		  
		  deleteItemButton.addActionListener(this::deleteItemButtonActionPerformed);
		  
	  
		  
		  JSeparator horizontalLineTop= new JSeparator();
		  JSeparator midLine = new JSeparator();
		  JSeparator horizontalLineBottom = new JSeparator();
		  
		  GroupLayout layout = new GroupLayout(getContentPane());
		  getContentPane().setLayout(layout);
		  layout.setAutoCreateGaps(true);
		  layout.setAutoCreateContainerGaps(true);
		  layout.setHorizontalGroup(
		        layout.createParallelGroup()
		             .addComponent(errorMessage).addComponent(horizontalLineTop).addComponent(midLine)
		             .addComponent(horizontalLineBottom).addComponent(overviewScrollPane)
		             .addGroup(layout.createSequentialGroup()
		                 
		            	 .addGroup(layout.createParallelGroup()
		                		 .addComponent(memberNameLabel).addComponent(memberPasswordLabel)
		                		 .addComponent(memberEmergencyContactLabel).addComponent(memberWeekNumberLabel).addComponent(memberGuideCheckBox)
		                		 .addComponent(memberHotelCheckBox).addComponent(equipmentLabel).addComponent(equipmentNumberLabel).addComponent(updateMemberButton).addComponent(deleteMemberButton)
		                		 .addComponent(deleteItemButton)
		                		 )
		                 
		                 .addGroup(layout.createParallelGroup()
		                		 .addComponent(memberNameTextField, 200, 200, 400)
		                		 .addComponent(memberPasswordTextField, 200, 200, 400)
		                		 .addComponent(memberEmergencyContactTextField, 200, 200, 400)
		                		 .addComponent(memberWeekNumberTextField, 200, 200, 400)
		                		 .addComponent(equipmentNumberTextField, 200, 200, 400)
		                		 .addComponent(equipmentAvailableList)
		                		 .addComponent(addEquipmentButton)
		                		 
		               
		                		 )));
		    
		    layout.linkSize(SwingConstants.HORIZONTAL, addEquipmentButton, deleteItemButton,memberNameTextField,memberPasswordTextField, memberEmergencyContactTextField, memberWeekNumberTextField, memberGuideCheckBox, memberHotelCheckBox, equipmentAvailableList);
		    
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
		            		.addComponent(equipmentLabel).addComponent(equipmentAvailableList)
		            		)
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(equipmentNumberLabel).addComponent(equipmentNumberTextField)
		            		)
		            .addGroup(layout.createParallelGroup()
		            		.addComponent(addEquipmentButton).addComponent(deleteItemButton)
		            		
		            		
		            		)
		            .addComponent(midLine)
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
		            .addComponent(overviewScrollPane)
		        
		            
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