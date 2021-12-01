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
import ca.mcgill.ecse.climbsafe.view.EquipmentFrame.Executable;
import java.awt.event.ActionEvent;

public class PayTripFrame extends JFrame {

	private static final long serialVersionUID = -4426310869334015542L;

	private JLabel errorMessage = new JLabel();

	private JTextField autCodeTextField = new JTextField();
	private JLabel autCodeLabel = new JLabel("Authorization code:");

	private JButton payTripButton = new JButton("Pay Trip");

	private String error = "";

	private String email;

	private JButton previousPage = new JButton("Return to previous page");

	public PayTripFrame(String email) {
		this.email = email;
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
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage).addGroup(
				layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(autCodeLabel))
						.addGroup(layout.createParallelGroup().addComponent(autCodeTextField, 400, 400, 800)
								.addComponent(payTripButton).addComponent(previousPage)))

		);
		layout.linkSize(SwingConstants.HORIZONTAL, payTripButton, autCodeTextField);

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
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

		}
		pack();
	}

	private void payTripButtonActionPerformed(ActionEvent evt) {
		error = "";

		if (autCodeTextField.getText().equals("")) {
			error = "Pkease fill the field";
		}

		callController(() -> AssignmentController.payTrip(email, autCodeTextField.getText()));

		refreshData();
	}

	private void backToPreviousPage(ActionEvent evt) {
		HomePageMemberFrame homepage = new HomePageMemberFrame();
		homepage.setVisible(true);
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
