package ca.mcgill.ecse.climbsafe.view;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import java.sql.*;
import java.time.*;

import java.awt.event.ActionEvent;

public class EquipmentFrame extends JFrame {
  
  private static final long serialVersionUID = -4426310869335015542L;
  
  private JLabel errorMessage = new JLabel();  // element for error message 
  
  // to add equipment
  private JTextField equipmentNameTextField = new JTextField();
  private JLabel equipmentNameLabel = new JLabel("Name:");
  
  private JTextField equipmentWeightTextField = new JTextField();
  private JLabel equipmentWeightLabel = new JLabel("Weight:");
  
  private JTextField equipmentPricePerWeekTextField = new JTextField();
  private JLabel equipmentPricePerWeekLabel = new JLabel("Price Per Week:");
  
  private JButton addEquipmentButton = new JButton("Add Equipment");
  
  /////////////////////////////
  
  
  
  // to update equipment
  //private JTextField equipmentOldNameTextField = new JTextField();
  private JComboBox<Equipment> oldNameList = new JComboBox<>();
  private JLabel equipmentOldNameLabel = new JLabel("Old Name:");
  
  private JTextField equipmentNewNameTextField = new JTextField();
  private JLabel equipmentNewNameLabel = new JLabel("New Name:");
  
  private JTextField equipmentNewWeightTextField = new JTextField();
  private JLabel equipmentNewWeightLabel = new JLabel("New Weight:");
  
  private JTextField equipmentNewPricePerWeekTextField = new JTextField();
  private JLabel equipmentNewPricePerWeekLabel = new JLabel("New Price Per Week:");
  
  private JButton updateEquipmentButton = new JButton("Update Equipment");
  
  //private JComboBox<String> equipmentToUpdateList = new JComboBox<>();
  ////////////////////////
  
  
  // to delete equipment
  //private JTextField equipmentNameToDeleteTextField = new JTextField();
  private JComboBox<Equipment> nameToDeleteList = new JComboBox<>();
  private JLabel equipmentNameToDeleteLabel = new JLabel("Name:");
  
  private JButton deleteEquipmentButton = new JButton("Delete Equipment");
  
  //private JComboBox<String> equipmentToDeleteList = new JComboBox<>();
  /////////////////////////////////////
  
  
  
  private String error = "";
  
  public EquipmentFrame() {
    initComponents();
    refreshData();
  }
  
  /** This method is called from within the constructor to initialize the form. */
  private void initComponents() {
    errorMessage.setForeground(Color.RED);
    errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));
    
    //global settings
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Climb Safe Application System");
    
    //listeners for adding equipment
    equipmentNameTextField.addActionListener(this::addEquipmentButtonActionPerformed);
    equipmentWeightTextField.addActionListener(this::addEquipmentButtonActionPerformed);
    equipmentPricePerWeekTextField.addActionListener(this::addEquipmentButtonActionPerformed);
    addEquipmentButton.addActionListener(this::addEquipmentButtonActionPerformed);
    ////////////////////////////////////
    
    
    // listeners for updating equipment
    //equipmentOldNameTextField.addActionListener(this::updateEquipmentButtonActionPerformed);
    equipmentNewNameTextField.addActionListener(this::updateEquipmentButtonActionPerformed);
    equipmentNewWeightTextField.addActionListener(this::updateEquipmentButtonActionPerformed);
    equipmentNewPricePerWeekTextField.addActionListener(this::updateEquipmentButtonActionPerformed);
    updateEquipmentButton.addActionListener(this::updateEquipmentButtonActionPerformed);
    //////////////////////////////////////
    
    
    // listeners for deleting equipment
    //equipmentNameToDeleteTextField.addActionListener(this:: deleteEquipmentButtonActionPerformed);
    deleteEquipmentButton.addActionListener(this:: deleteEquipmentButtonActionPerformed);
    //////////////////////////////////////
    
    
    //lists
    
    
    
    
    
    // horizontal line elements
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
                     .addComponent(equipmentNameLabel).addComponent(equipmentWeightLabel)
                     .addComponent(equipmentPricePerWeekLabel)
                     .addComponent(equipmentOldNameLabel).addComponent(equipmentNewNameLabel)
                     .addComponent(equipmentNewWeightLabel).addComponent(equipmentNewPricePerWeekLabel)
                     .addComponent(equipmentNameToDeleteLabel)
                     )
                 .addGroup(layout.createParallelGroup()
                     .addComponent(equipmentNameTextField,200,200,400)
                     .addComponent(equipmentWeightTextField,200,200,400)
                     .addComponent(equipmentPricePerWeekTextField,200,200,400)
                     .addComponent(addEquipmentButton)
                     //.addComponent(equipmentToAddList)
                     //.addComponent(equipmentOldNameTextField,200,200,400)
                     .addComponent(oldNameList)
                     .addComponent(equipmentNewNameTextField,200,200,400)
                     .addComponent(equipmentNewWeightTextField,200,200,400)
                     .addComponent(equipmentNewPricePerWeekTextField,200,200,400)
                     .addComponent(updateEquipmentButton)
                     //.addComponent(equipmentToUpdateList)
                     //.addComponent(equipmentNameToDeleteTextField,200,200,400)
                     .addComponent(deleteEquipmentButton)
                     .addComponent(nameToDeleteList)
                     
                     )    
             )    
    );
    
    layout.linkSize(SwingConstants.HORIZONTAL, addEquipmentButton, equipmentNameTextField, equipmentWeightTextField, equipmentPricePerWeekTextField);
    layout.linkSize(SwingConstants.HORIZONTAL, updateEquipmentButton, /*equipmentOldNameTextField,*/ equipmentNewNameTextField, equipmentNewWeightTextField, equipmentNewPricePerWeekTextField);
    layout.linkSize(SwingConstants.HORIZONTAL, deleteEquipmentButton /*,equipmentNameToDeleteTextField*/);
    
    
    layout.setVerticalGroup(
        layout.createSequentialGroup()
           .addComponent(errorMessage)
           .addGroup(layout.createParallelGroup()
               .addComponent(equipmentNameLabel).addComponent(equipmentNameTextField)
               )
           .addGroup(layout.createParallelGroup()
               .addComponent(equipmentWeightLabel).addComponent(equipmentWeightTextField)
               )
           .addGroup(layout.createParallelGroup()
               .addComponent(equipmentPricePerWeekLabel).addComponent(equipmentPricePerWeekTextField)
               )
           .addGroup(layout.createParallelGroup()
               .addComponent(addEquipmentButton)
               )
           /*.addGroup(layout.createParallelGroup()
               .addComponent(equipmentToAddList)
               )*/
           .addGroup(layout.createParallelGroup()
               .addComponent(horizontalLineTop)
               )
           .addGroup(layout.createParallelGroup()
               .addComponent(equipmentOldNameLabel).addComponent(oldNameList)
               )
           .addGroup(layout.createParallelGroup()
               .addComponent(equipmentNewNameLabel).addComponent(equipmentNewNameTextField)
               )
           .addGroup(layout.createParallelGroup()
               .addComponent(equipmentNewWeightLabel).addComponent(equipmentNewWeightTextField)
               )
           .addGroup(layout.createParallelGroup()
               .addComponent(equipmentNewPricePerWeekLabel).addComponent(equipmentNewPricePerWeekTextField)
               )
           .addGroup(layout.createParallelGroup()
               .addComponent(updateEquipmentButton)
               )
           /*.addGroup(layout.createParallelGroup()
               .addComponent(equipmentToUpdateList))*/
           .addGroup(layout.createParallelGroup()
               .addComponent(horizontalLineBottom)
               )
           .addGroup(layout.createParallelGroup()
               .addComponent(equipmentNameToDeleteLabel).addComponent(nameToDeleteList)
               )
           .addGroup(layout.createParallelGroup()
               .addComponent(deleteEquipmentButton)
               )
           /*.addGroup(layout.createParallelGroup()
               .addComponent(equipmentToDeleteList))*/
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
      
      //add equipment
      equipmentNameTextField.setText("");
      equipmentWeightTextField.setText("");
      equipmentPricePerWeekTextField.setText("");
      
      
      // update equipment
      //equipmentOldNameTextField.setText("");
      equipmentNewNameTextField.setText("");
      equipmentNewWeightTextField.setText("");
      equipmentNewPricePerWeekTextField.setText("");
      
      // delete equipment
      //equipmentNameToDeleteTextField.setText("");
      
      //var lists = List.of(equipmentToAddList, equipmentToUpdateList, equipmentToDeleteList);
      
     // lists.forEach(JComboBox:: removeAllItems);    
      
      var lists = List.of(oldNameList,nameToDeleteList);
      lists.forEach(JComboBox::removeAllItems);
      
      ClimbSafe climbSafeApp = ClimbSafeApplication.getClimbSafe();
      
      String eq = climbSafeApp.getEquipment().toString();
      
      climbSafeApp.getEquipment().forEach(oldNameList::addItem);
      
      climbSafeApp.getEquipment().forEach(nameToDeleteList::addItem);
      
      lists.forEach(list -> list.setSelectedIndex(-1));
      
     
     pack(); 
      
    }
  }
  
  private void addEquipmentButtonActionPerformed(ActionEvent evt) {
    
    // clear error message
    error = "";
    
    callController(() -> ClimbSafeFeatureSet4Controller.addEquipment(equipmentNameTextField.getText(),
       getNumberFromField(equipmentWeightTextField, error) ,  getNumberFromField(equipmentPricePerWeekTextField,error)));
    
    // update visuals 
    refreshData();
    
  }
  
  private void updateEquipmentButtonActionPerformed(ActionEvent evt) {
    
    error = "";
    
    var selectedEquipment = (Equipment) oldNameList.getSelectedItem();
    if(selectedEquipment == null) {
      error = "Equipment needs to be selected to update it!";
    }else {
      callController(() -> ClimbSafeFeatureSet4Controller.updateEquipment(selectedEquipment.getName(), 
          equipmentNewNameTextField.getText(), getNumberFromField(equipmentNewWeightTextField,error), 
          getNumberFromField(equipmentNewPricePerWeekTextField,error)));
    }
    refreshData();
   /* 
    var equipmentToBeUpdate = (Equipment) equipmentToUpdateList.getSelectedItem();
    
    if(equipmentToBeUpdate == null) {
      error = "An equipment has to be selected to update it!";
    }*/
   /* callController(()  -> ClimbSafeFeatureSet4Controller
        .updateEquipment(equipmentOldNameTextField.getText(), equipmentNewNameTextField.getText(),
            getNumberFromField(equipmentNewWeightTextField,error), getNumberFromField(equipmentNewPricePerWeekTextField,error)));*/
  }
  
  private void deleteEquipmentButtonActionPerformed(ActionEvent evt) {
    
    error = "";
    
    
    var selectedEquipment = (Equipment) nameToDeleteList.getSelectedItem();
    if(selectedEquipment == null) {
      error = "Equipment has to be selected for deletion!";
    } else {
      callController(() -> ClimbSafeFeatureSet6Controller.deleteEquipment(selectedEquipment.getName()));
    }
    /*
    callController(() -> ClimbSafeFeatureSet6Controller.deleteEquipment(equipmentNameToDeleteTextField.getText()));
    */
    //refreshData();
    /*var deletedEquipment = (Equipment) equipmentToDeleteList.getSelectedItem();
    if (deletedEquipment == null) {
      error = "An equipment has to be selected for deletion!";
    }else {
      callController(()  -> ClimbSafeFeatureSet6Controller.deleteEquipment(equipmentNameToDeleteTextField.getText()));
    }*/
  }
  
  /** Returns the number from the given text field if present, otherwise appends error string to the given message. */
  private int getNumberFromField(JTextField field, String errorMessage) {
    try {
      return Integer.parseInt(field.getText());
    } catch (NumberFormatException e) {
      error += errorMessage;
    }
    return 0;
  }
  
  
  /**
   * Calls the controller and sets the error message.
   * @param executable a controller call preceded by "() -> ", eg,<br>
   * @return
   */
  private String callController(Executable executable) {
    try {
      executable.execute();
    } catch (InvalidInputException e) {
      error = e .getMessage();
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