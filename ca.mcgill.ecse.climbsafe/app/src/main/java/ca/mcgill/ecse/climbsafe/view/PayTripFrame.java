package ca.mcgill.ecse.climbsafe.view;

import java.util.List;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;
import ca.mcgill.ecse.climbsafe.application.*;

import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.view.EquipmentFrame.Executable;
import java.awt.event.ActionEvent;


/***
 * This class represents the Member2 frame (the continuation of Member frame). 
 * More specifically the Add/Update/Delete member feature.
 * @authors  Can Akin, Oliver Cafferty
 *
 */

public class PayTripFrame extends JFrame {

    private static final long serialVersionUID = -4426310869334015542L;

    private JLabel errorMessage = new JLabel();

    private JComboBox<String> memberList = new JComboBox<>();
    private JLabel memberLabel = new JLabel("Select Member:");

    private JTextField autCodeTextField = new JTextField();
    private JLabel autCodeLabel = new JLabel("Authorization code:");

    private JButton payTripButton = new JButton("Pay Trip");

    private String error = "";

    private JButton previousPage = new JButton("Return to previous page");

    public PayTripFrame() {
        initComponents();
        refreshData();

    }

    private void initComponents() {

        errorMessage.setForeground(Color.RED);
        errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Climb Safe Application System");

        // listener for authorization code
        autCodeTextField.addActionListener(this::payTripButtonActionPerformed);
        payTripButton.addActionListener(this::payTripButtonActionPerformed);
        previousPage.addActionListener(this::backToPreviousPage);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup().addComponent(memberLabel).addComponent(autCodeLabel))
                        .addGroup(layout.createParallelGroup().addComponent(memberList)
                                .addComponent(autCodeTextField, 400, 400, 800).addComponent(payTripButton)
                                .addComponent(previousPage)))

        );
        layout.linkSize(SwingConstants.HORIZONTAL, payTripButton, autCodeTextField);

        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
                .addGroup(layout.createParallelGroup().addComponent(memberLabel).addComponent(memberList))
                .addGroup(layout.createParallelGroup().addComponent(autCodeLabel).addComponent(autCodeTextField))
                .addGroup(layout.createParallelGroup().addComponent(payTripButton).addComponent(previousPage)));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    private void refreshData() {
        errorMessage.setText(error);
        if (error == null || error.isEmpty()) {
            autCodeTextField.setText("");

            var lists = List.of(memberList);
            lists.forEach(JComboBox::removeAllItems);

            AddtitionalController.getMemberEmails().forEach(memberList::addItem);

            lists.forEach(list -> list.setSelectedIndex(-1));

            pack();

        }
        pack();
    }
    /***
     * This method validates the trip has been paid.
     * @author Can Akin
     * @param evt
     */
    private void payTripButtonActionPerformed(ActionEvent evt) {
        error = "";

        if (autCodeTextField.getText().equals("")) {
            error = "Please fill the field";
        }

        var selectedMember = (String) memberList.getSelectedItem();
        if (selectedMember == null) {
            error = "Member needs to be selected to update it!";
        } else if (error.isEmpty()) {
            System.out.println(autCodeTextField.getText());
            callController(() -> AssignmentController.payTrip(selectedMember, autCodeTextField.getText()));
        }

        refreshData();
    }
    /***
     * This method validates the user is going back to the previous page.
     * @author Oliver Cafferty
     * @param evt
     */
    private void backToPreviousPage(ActionEvent evt) {
        HomePageMemberFrame homepage = new HomePageMemberFrame();
        homepage.setVisible(true);
        dispose();
    }
    /***
     * 
     * @author Oliver Cafferty
     * @param executable
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
