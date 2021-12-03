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
import ca.mcgill.ecse.climbsafe.view.EquipmentFrame.Executable;

/***
 * This class represents the guide frame. 
 * More specifically, the Register/Update/Delete guide feature.
 * @author Anaëlle Drai Laguéns
 *
 */

public class GuideFrame extends JFrame {

    private static final long serialVersionUID = 5L;

    private JLabel errorMessage = new JLabel(); // element for error message

    // Select Guide for update
    private JComboBox<String> guideList = new JComboBox<>();
    private JLabel guideLabel = new JLabel("Select Guide:");

    private JTextField newGuideNameTextField = new JTextField();
    private JLabel newGuideNameLabel = new JLabel("New Name:");

    private JTextField newGuidePasswordTextField = new JTextField();
    private JLabel newGuidePasswordLabel = new JLabel("New Password:");

    private JTextField newGuideEmergencyContactTextField = new JTextField();
    private JLabel newGuideEmergencyContactLabel = new JLabel("New Emergency Contact:");

    private JButton updateGuideButton = new JButton("Update Guide");

    // Select Guide for Deletion
    private JButton previousPage = new JButton("Return to previous page");

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
        setSize(500, 500);

        // listeners for updating guides
        newGuideNameTextField.addActionListener(this::updateGuideButtonActionPerformed);
        newGuidePasswordTextField.addActionListener(this::updateGuideButtonActionPerformed);
        newGuideEmergencyContactTextField.addActionListener(this::updateGuideButtonActionPerformed);
        updateGuideButton.addActionListener(this::updateGuideButtonActionPerformed);

        deleteGuideButton.addActionListener(this::deleteGuideButtonActionPerformed);
        previousPage.addActionListener(this::backToPreviousPage);

        JSeparator horizontalLineBottom = new JSeparator();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
                .addComponent(horizontalLineBottom)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup().addComponent(guideLabel).addComponent(newGuideNameLabel)
                                .addComponent(newGuidePasswordLabel).addComponent(newGuideEmergencyContactLabel))
                        .addGroup(layout.createParallelGroup().addComponent(guideList)
                                .addComponent(newGuideNameTextField, 500, 500, 1000)
                                .addComponent(newGuidePasswordTextField, 500, 500, 1000)
                                .addComponent(newGuideEmergencyContactTextField, 500, 500, 1000)
                                .addComponent(updateGuideButton, 500, 500, 1000).addComponent(deleteGuideButton, 500, 500, 1000)
                                .addComponent(previousPage, 500, 500, 1000))));

        layout.linkSize(SwingConstants.HORIZONTAL, guideList, newGuideNameTextField, newGuidePasswordTextField,
                newGuideEmergencyContactTextField);
        layout.linkSize(SwingConstants.HORIZONTAL, deleteGuideButton, updateGuideButton);
        // .addComponent(errorMessage)
        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
                .addGroup(layout.createParallelGroup().addComponent(guideLabel).addComponent(guideList))
                .addGroup(layout.createParallelGroup().addComponent(newGuideNameLabel)
                        .addComponent(newGuideNameTextField))
                .addGroup(layout.createParallelGroup().addComponent(newGuidePasswordLabel)
                        .addComponent(newGuidePasswordTextField))
                .addGroup(layout.createParallelGroup().addComponent(newGuideEmergencyContactLabel)
                        .addComponent(newGuideEmergencyContactTextField))
                .addGroup(layout.createParallelGroup().addComponent(updateGuideButton))
                .addGroup(layout.createParallelGroup().addComponent(deleteGuideButton))
                .addGroup(layout.createParallelGroup().addComponent(horizontalLineBottom))
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

            // update equipment
            newGuideNameTextField.setText("");
            newGuidePasswordTextField.setText("");
            newGuideEmergencyContactTextField.setText("");

            var lists = List.of(guideList);
            lists.forEach(JComboBox::removeAllItems);

            AddtitionalController.getGuideEmails().forEach(guideList::addItem);

            lists.forEach(list -> list.setSelectedIndex(-1));

            pack();

        }

    }
    /***
     * This method validates the guide has been updated.
     * @author Anaëlle Drai Laguéns
     * @param evt
     */
    private void updateGuideButtonActionPerformed(ActionEvent evt) {

        error = "";

        var selectedGuide = (String) guideList.getSelectedItem();
        if (selectedGuide == null) {
            error = "Guide needs to be selected to update it!";
        } else if (error.isEmpty()) {
            callController(() -> ClimbSafeFeatureSet3Controller.updateGuide(selectedGuide, newGuidePasswordTextField.getText(),
                    newGuideNameTextField.getText(), newGuideEmergencyContactTextField.getText()));
        }
        refreshData();
    }
    /***
     * This method validates the guide has been deleted.
     * @author Anaëlle Drai Laguéns
     * @param evt
     */
    private void deleteGuideButtonActionPerformed(ActionEvent evt) {

        error = "";

        var selectedGuide = (String) guideList.getSelectedItem();
        if (selectedGuide == null) {
            error = "Guide has to be selected for deletion!";
        } else if (error.isEmpty()) {
        callController(() -> ClimbSafeFeatureSet1Controller.deleteGuide(selectedGuide));
        }
        refreshData();

    }
    /***
     * This method validates the user is going back to the previous page.
     * @author Anaëlle Drai Laguéns
     * @param evt
     */
    private void backToPreviousPage(ActionEvent evt) {
        HomePageGuideFrame homepage = new HomePageGuideFrame();
        homepage.setVisible(true);
        dispose();
    }

    /**
     * Calls the controller and sets the error message.
     * @author Anaëlle Drai Laguéns
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
