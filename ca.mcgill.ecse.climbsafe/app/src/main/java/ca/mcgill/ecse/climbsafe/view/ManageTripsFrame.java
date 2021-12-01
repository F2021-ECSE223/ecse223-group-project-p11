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
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOGuide;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.view.GuideFrame.Executable;

public class ManageTripsFrame extends JFrame {

	private JLabel errorMessage = new JLabel(); // element for error message

	// Select Guide for update
	private JComboBox<String> memberList = new JComboBox<>();
	private JLabel memberLabel = new JLabel("Select Member:");

	private JTextField weekNumber = new JTextField();
	private JLabel weekNumberLabel = new JLabel("Week Number:");

	private JButton startTripsButton = new JButton("Start Trips");
	private JButton finishTripButton = new JButton("Finish Trips");
	private JButton cancelTripButton = new JButton("Cancel Trip");
	private JButton previousPage = new JButton("Return to previous page");

	private String error = "";

	public ManageTripsFrame() {
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
		startTripsButton.addActionListener(this::startTripsActionPerformed);
		finishTripButton.addActionListener(this::finishTripsActionPerformed);
		cancelTripButton.addActionListener(this::cancelTripActionPerformed);
		previousPage.addActionListener(this::backToPreviousPage);

		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
				.addComponent(horizontalLineTop).addComponent(horizontalLineBottom)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(weekNumberLabel).addComponent(memberLabel))
						.addGroup(layout.createParallelGroup().addComponent(weekNumber, 500, 500, 1000)
								.addComponent(startTripsButton, 500, 500, 1000)
								.addComponent(memberList, 500, 500, 1000)
								.addComponent(finishTripButton, 500, 500, 1000)
								.addComponent(cancelTripButton, 500, 500, 1000).addComponent(previousPage, 500, 500, 1000))));

		layout.linkSize(SwingConstants.HORIZONTAL, weekNumber, memberList);
		layout.linkSize(SwingConstants.HORIZONTAL, startTripsButton, finishTripButton, cancelTripButton);
		// .addComponent(errorMessage)
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(weekNumber).addComponent(weekNumberLabel))
				.addComponent(startTripsButton)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineTop))
				.addGroup(layout.createParallelGroup().addComponent(memberLabel)
						.addComponent(memberList))
				.addComponent(finishTripButton)
				.addComponent(cancelTripButton).addComponent(previousPage)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineBottom)));

		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}

	private void refreshData() {
		errorMessage.setText(error);
		if (error == null || error.isEmpty()) {
			// populate page with data

			// update equipment
			weekNumber.setText("");


			var lists = List.of(memberList);
			lists.forEach(JComboBox::removeAllItems);

			ClimbSafe climbSafeApp = ClimbSafeApplication.getClimbSafe();

			String eq = climbSafeApp.getEquipment().toString();

			AddtitionalController.getMemberEmails().forEach(memberList::addItem);

			lists.forEach(list -> list.setSelectedIndex(-1));

			pack();

		}

	}

	private void cancelTripActionPerformed(ActionEvent evt) {

		error = "";

		var selectedMemberEmail = (String) memberList.getSelectedItem();
		if (selectedMemberEmail == null) {
			error = "Member needs to be selected to cancel its trip!";
		} else if (error.isEmpty()) {
			callController(() -> AssignmentController.cancelTrip(selectedMemberEmail));
		}
		refreshData();
	}

	private void finishTripsActionPerformed(ActionEvent evt) {

		error = "";

		var selectedMemberEmail = (String) memberList.getSelectedItem();
		if (selectedMemberEmail == null) {
			error = "Member needs to be selected to cancel its trip!";
		} else if (error.isEmpty()) {
			callController(() -> AssignmentController.finishTrip(selectedMemberEmail));
		}
		refreshData();
	}
	
	  private int getNumberFromField(JTextField field, String errorMessage) {
		    try {
		      return Integer.parseInt(field.getText());
		    } catch (NumberFormatException e) {
		      error += errorMessage;
		    }
		    return 0;
		  }

	private void startTripsActionPerformed(ActionEvent evt) {

		error = "";

		var enteredWeekNumber =  getNumberFromField(weekNumber,error);
		if (error.isEmpty()) {
			callController(() -> AssignmentController.startTrips(enteredWeekNumber));
		}
		refreshData();
	}

	private void backToPreviousPage(ActionEvent evt) {
        HomePageAdminFrame homepage = new HomePageAdminFrame();
        homepage.setVisible(true);
        dispose();
	}
	
	/**
	 * Calls the controller and sets the error message.
	 * 
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
