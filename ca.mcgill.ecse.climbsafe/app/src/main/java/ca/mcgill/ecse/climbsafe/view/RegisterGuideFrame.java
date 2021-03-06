package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.*;
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
import ca.mcgill.ecse.climbsafe.view.GuideFrame.Executable;

/**
 * This class represents the guide frame. More specifically, the part of Register Guide feature.
 * @author Anaëlle Drai Laguéns
 * 
 */


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

    private JButton previousPage = new JButton("Return to previous page");

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
        previousPage.addActionListener(this::backToPreviousPage);

        // listeners for updating guides

        GroupLayout layout = new GroupLayout(getContentPane());

        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup().addComponent(guideEmailLabel, 200, 200, 200)
                                .addComponent(guideNameLabel, 200, 200, 200)
                                .addComponent(guideEmergencyContactLabel, 200, 200, 400)
                                .addComponent(guidePasswordLabel, 200, 200, 200))
                        .addGroup(layout.createParallelGroup().addComponent(guideEmailTextField, 500, 500, 1000)
                                .addComponent(guideNameTextField, 500, 500, 1000)
                                .addComponent(guideEmergencyContactTextField, 500, 500, 1000)
                                .addComponent(guidePasswordTextField, 500, 500, 1000).addComponent(addGuideButton, 500, 500, 500)
                                .addComponent(previousPage, 500, 500, 500)))
                        );

        layout.linkSize(SwingConstants.HORIZONTAL, guideNameTextField, guidePasswordTextField,
                guideEmergencyContactTextField);

        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
                .addGroup(layout.createParallelGroup().addComponent(guideEmailLabel, 20, 20, 20)
                        .addComponent(guideEmailTextField, 20, 20, 20))
                .addGroup(layout.createParallelGroup().addComponent(guideNameLabel, 20, 20, 20)
                        .addComponent(guideNameTextField, 20, 20, 20))
                .addGroup(layout.createParallelGroup().addComponent(guideEmergencyContactLabel, 20, 20, 20)
                        .addComponent(guideEmergencyContactTextField, 20, 20, 20))
                .addGroup(layout.createParallelGroup().addComponent(guidePasswordLabel, 20, 20, 20)
                        .addComponent(guidePasswordTextField, 20, 20, 20))
                .addComponent(addGuideButton).addComponent(previousPage)
                );

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void refreshData() {
        errorMessage.setText(error);
        if (error == null || error.isEmpty()) {
            guideEmailTextField.setText("");
            guideNameTextField.setText("");
            guidePasswordTextField.setText("");
            guideEmergencyContactTextField.setText("");

        }
        pack();
    }
    /***
     * This method validates the guide has been added.
     * @author Anaëlle Drai Laguéns
     * @param evt
     */
    private void addGuideButtonActionPerformed(ActionEvent evt) {

        // clear error message
        error = "";

        callController(() -> ClimbSafeFeatureSet3Controller.registerGuide(guideEmailTextField.getText(),
                guidePasswordTextField.getText(), guideNameTextField.getText(),
                guideEmergencyContactTextField.getText()));

        // update visuals
        refreshData();

    }
    /***
     * This method validates the user is going back to the previous page.
     * @author Anaëlle Drai Laguéns
     * @param evt
     */
    private void backToPreviousPage(ActionEvent evt) {
        InitialHomePage initialPage = new InitialHomePage();
        initialPage.setVisible(true);
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
