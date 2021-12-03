package ca.mcgill.ecse.climbsafe.view;

import java.util.List;


import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;
import ca.mcgill.ecse.climbsafe.application.*;

import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOEquipment;

import java.sql.*;
import java.time.*;

import java.awt.event.ActionEvent;

/**
 * This class represents the equipment frame. More specifically, the Add/Update/Delete equipment feature.
 * @author Can Akin
 * 
 */

public class EquipmentFrame extends JFrame {

    private static final long serialVersionUID = -4426310869335015542L;

    private JLabel errorMessage = new JLabel(); // element for error message

    // to add equipment
    private JTextField equipmentNameTextField = new JTextField();
    private JLabel equipmentNameLabel = new JLabel("Name:");

    private JTextField equipmentWeightTextField = new JTextField();
    private JLabel equipmentWeightLabel = new JLabel("Weight:");

    private JTextField equipmentPricePerWeekTextField = new JTextField();
    private JLabel equipmentPricePerWeekLabel = new JLabel("Price Per Week:");

    private JButton addEquipmentButton = new JButton("Add Equipment");

    /////////////////////////////

    private JButton previousPage = new JButton("Return to previous page");

    // to update equipment
    private JComboBox<String> oldNameList = new JComboBox<>();
    private JLabel equipmentOldNameLabel = new JLabel("Old Name:");

    private JTextField equipmentNewNameTextField = new JTextField();
    private JLabel equipmentNewNameLabel = new JLabel("New Name:");

    private JTextField equipmentNewWeightTextField = new JTextField();
    private JLabel equipmentNewWeightLabel = new JLabel("New Weight:");

    private JTextField equipmentNewPricePerWeekTextField = new JTextField();
    private JLabel equipmentNewPricePerWeekLabel = new JLabel("New Price Per Week:");

    private JButton updateEquipmentButton = new JButton("Update Equipment");
    ////////////////////////

    // to delete equipment
    private JComboBox<String> nameToDeleteList = new JComboBox<>();
    private JLabel equipmentNameToDeleteLabel = new JLabel("Name:");

    private JButton deleteEquipmentButton = new JButton("Delete Equipment");
    /////////////////////////////////////

    private String error = "";

    /** Creates new form EquipmentFrame */
    public EquipmentFrame() {
        initComponents();
        refreshData();
    }
    private void initComponents() {

        errorMessage.setForeground(Color.RED);
        errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));

        // global settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Climb Safe Application System");


        // listeners for adding equipment
        equipmentNameTextField.addActionListener(this::addEquipmentButtonActionPerformed);
        equipmentWeightTextField.addActionListener(this::addEquipmentButtonActionPerformed);
        equipmentPricePerWeekTextField.addActionListener(this::addEquipmentButtonActionPerformed);
        addEquipmentButton.addActionListener(this::addEquipmentButtonActionPerformed);
        previousPage.addActionListener(this::backToPreviousPage);


        // listeners for updating equipment
        equipmentNewNameTextField.addActionListener(this::updateEquipmentButtonActionPerformed);
        equipmentNewWeightTextField.addActionListener(this::updateEquipmentButtonActionPerformed);
        equipmentNewPricePerWeekTextField.addActionListener(this::updateEquipmentButtonActionPerformed);
        updateEquipmentButton.addActionListener(this::updateEquipmentButtonActionPerformed);

        // listeners for deleting equipment
        deleteEquipmentButton.addActionListener(this::deleteEquipmentButtonActionPerformed);

        // horizontal line elements
        JSeparator horizontalLineTop = new JSeparator();
        JSeparator horizontalLineBottom = new JSeparator();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
                .addComponent(horizontalLineTop).addComponent(horizontalLineBottom)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup().addComponent(equipmentNameLabel)
                                .addComponent(equipmentWeightLabel).addComponent(equipmentPricePerWeekLabel)
                                .addComponent(equipmentOldNameLabel).addComponent(equipmentNewNameLabel)
                                .addComponent(equipmentNewWeightLabel).addComponent(equipmentNewPricePerWeekLabel)
                                .addComponent(equipmentNameToDeleteLabel))
                        .addGroup(layout.createParallelGroup().addComponent(equipmentNameTextField, 200, 200, 400)
                                .addComponent(equipmentWeightTextField, 200, 200, 400)
                                .addComponent(equipmentPricePerWeekTextField, 200, 200, 400)
                                .addComponent(addEquipmentButton)
                                .addComponent(oldNameList).addComponent(equipmentNewNameTextField, 200, 200, 400)
                                .addComponent(equipmentNewWeightTextField, 200, 200, 400)
                                .addComponent(equipmentNewPricePerWeekTextField, 200, 200, 400)
                                .addComponent(updateEquipmentButton)
                                .addComponent(deleteEquipmentButton).addComponent(nameToDeleteList)
                                .addComponent(previousPage))));

        layout.linkSize(SwingConstants.HORIZONTAL, addEquipmentButton, equipmentNameTextField, equipmentWeightTextField,
                equipmentPricePerWeekTextField);
        layout.linkSize(SwingConstants.HORIZONTAL, updateEquipmentButton,
                 equipmentNewNameTextField, equipmentNewWeightTextField,
                equipmentNewPricePerWeekTextField);
        layout.linkSize(SwingConstants.HORIZONTAL, deleteEquipmentButton );

        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
                .addGroup(layout.createParallelGroup().addComponent(equipmentNameLabel)
                        .addComponent(equipmentNameTextField))
                .addGroup(layout.createParallelGroup().addComponent(equipmentWeightLabel)
                        .addComponent(equipmentWeightTextField))
                .addGroup(layout.createParallelGroup().addComponent(equipmentPricePerWeekLabel)
                        .addComponent(equipmentPricePerWeekTextField))
                .addGroup(layout.createParallelGroup().addComponent(addEquipmentButton))
                .addGroup(layout.createParallelGroup().addComponent(horizontalLineTop))
                .addGroup(layout.createParallelGroup().addComponent(equipmentOldNameLabel).addComponent(oldNameList))
                .addGroup(layout.createParallelGroup().addComponent(equipmentNewNameLabel)
                        .addComponent(equipmentNewNameTextField))
                .addGroup(layout.createParallelGroup().addComponent(equipmentNewWeightLabel)
                        .addComponent(equipmentNewWeightTextField))
                .addGroup(layout.createParallelGroup().addComponent(equipmentNewPricePerWeekLabel)
                        .addComponent(equipmentNewPricePerWeekTextField))
                .addGroup(layout.createParallelGroup().addComponent(updateEquipmentButton))
                .addGroup(layout.createParallelGroup().addComponent(horizontalLineBottom))
                .addGroup(layout.createParallelGroup().addComponent(equipmentNameToDeleteLabel)
                        .addComponent(nameToDeleteList))
                .addGroup(layout.createParallelGroup().addComponent(deleteEquipmentButton))
                .addComponent(previousPage)
        
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
            equipmentNameTextField.setText("");
            equipmentWeightTextField.setText("");
            equipmentPricePerWeekTextField.setText("");

            // update equipment
            equipmentNewNameTextField.setText("");
            equipmentNewWeightTextField.setText("");
            equipmentNewPricePerWeekTextField.setText("");

            var lists = List.of(oldNameList, nameToDeleteList);
            lists.forEach(JComboBox::removeAllItems);

            AddtitionalController.getEquipmentStrings().forEach(oldNameList::addItem);
            AddtitionalController.getEquipmentStrings().forEach(nameToDeleteList::addItem);

            lists.forEach(list -> list.setSelectedIndex(-1));

        }
        pack();
    }
    /***
     * This method validates the equipment has been added.
     * @author Can Akin
     * @param evt
     */
    private void addEquipmentButtonActionPerformed(ActionEvent evt) {

        // clear error message
        error = "";

        if (equipmentNameTextField.getText().equals("") || equipmentWeightTextField.getText().equals("")
                || equipmentPricePerWeekTextField.getText().equals("")) {
            error = "Please fill all fields!";
        }

        callController(() -> ClimbSafeFeatureSet4Controller.addEquipment(equipmentNameTextField.getText(),
                getNumberFromField(equipmentWeightTextField, error),
                getNumberFromField(equipmentPricePerWeekTextField, error)));

        refreshData();

    }
    /***
     * This method validates the equipment has been updated.
     * @author Can Akin
     * @param evt
     */
    private void updateEquipmentButtonActionPerformed(ActionEvent evt) {

        error = "";
        var selectedEquipment = (String) oldNameList.getSelectedItem();
        if (selectedEquipment == null) {
            error = "Equipment needs to be selected to update it!";
        }
        if(equipmentNewNameTextField.getText().equals("") || equipmentNewWeightTextField.getText().equals("")
            || equipmentNewPricePerWeekTextField.getText().equals("")) {
          error = "Please all the fields";
        }
        error = error.trim();

        if (error.isEmpty()) {
            var updatedEquipment = selectedEquipment;
            callController(() -> ClimbSafeFeatureSet4Controller.updateEquipment(selectedEquipment,
                    equipmentNewNameTextField.getText(), getNumberFromField(equipmentNewWeightTextField, error),
                    getNumberFromField(equipmentNewPricePerWeekTextField, error)));
        }
        refreshData();
    }
    /***
     * This method validates the equipment has been deleted.
     * @author Can Akin
     * @param evt
     */
    private void deleteEquipmentButtonActionPerformed(ActionEvent evt) {

        error = "";

        var selectedEquipment = (String) nameToDeleteList.getSelectedItem();
        if (selectedEquipment == null) {
            error = "Equipment has to be selected for deletion!";
        } else {
            callController(() -> ClimbSafeFeatureSet6Controller.deleteEquipment(selectedEquipment));
        }

        refreshData();

    }
    /***
     * This method validates the user goes back to the previous page.
     * @author Can Akin
     * @param evt
     */
    private void backToPreviousPage(ActionEvent evt) {
        HomePageAdminFrame homepage = new HomePageAdminFrame();
        homepage.setVisible(true);
        dispose();
    }
    /***
     * This method returns the number from the given text field if present, otherwise appends
     * error string to the given message.
     * @author Can Akin
     * @param field
     * @param errorMessage
     * @return
     */
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
     * @author Can Akin
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