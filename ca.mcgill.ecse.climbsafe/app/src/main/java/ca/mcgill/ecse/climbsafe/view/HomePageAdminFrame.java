package ca.mcgill.ecse.climbsafe.view;

import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOGuide;
import ca.mcgill.ecse.climbsafe.view.GuideFrame.Executable;

public class HomePageAdminFrame extends JFrame {

	private JLabel errorMessage = new JLabel(); // element for error message

	private JButton banMember = new JButton("Ban Member");

	private JButton startCancelFinishTrips = new JButton("Start, Finish or Cancel Trips");

	private JButton addUpdateEquipment = new JButton("Add, Update or Delete Equipment");

	private JButton addUpdateEquipmentBundle = new JButton("Add, Update or Delete Equipment Bundles");

	private JButton setUpNMCInfo = new JButton("Set up NMC information");
	
	private JButton initiateAssignments = new JButton("Initiate Assignments");
	private JButton viewAssignments = new JButton("View Assignments");
	private String error = "";

	public HomePageAdminFrame() {
		initComponents();
	}
	
	private void initComponents() {
		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Climb Safe Application System");
		setSize(500, 500);

		banMember.addActionListener(this::banMemberActionPerformed);
		startCancelFinishTrips.addActionListener(this::startCancelFinishTripsActionPerformed);
		addUpdateEquipment.addActionListener(this::addUpdateEquipmentActionPerformed);
		addUpdateEquipmentBundle.addActionListener(this::addUpdateEquipmentBundleActionPerformed);
		setUpNMCInfo.addActionListener(this::setUpNMCInfoActionPerformed);
		initiateAssignments.addActionListener(this::initiateAssignmentsActionPerformed);

		
		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
				.addComponent(horizontalLineTop)
				.addComponent(banMember)
				.addComponent(startCancelFinishTrips)
				.addComponent(addUpdateEquipment)
				.addComponent(addUpdateEquipmentBundle)
				.addComponent(setUpNMCInfo)
				.addComponent(initiateAssignments)
				.addComponent(viewAssignments)
				.addComponent(horizontalLineBottom));
		
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage).addComponent(horizontalLineTop)
				.addComponent(banMember)
				.addComponent(startCancelFinishTrips)
				.addComponent(addUpdateEquipment)
				.addComponent(addUpdateEquipmentBundle)
				.addComponent(setUpNMCInfo)
				.addComponent(initiateAssignments)
				.addComponent(viewAssignments)
				.addComponent(horizontalLineBottom));
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	private void banMemberActionPerformed(ActionEvent evt) {

	}
	
	private void startCancelFinishTripsActionPerformed(ActionEvent evt) {
        ManageTripsFrame manageTripsFrame = new ManageTripsFrame();
        manageTripsFrame.setVisible(true);
        dispose();
	}
	
	private void addUpdateEquipmentActionPerformed(ActionEvent evt) {
        EquipmentFrame updateEquipmentFrame = new EquipmentFrame();
        updateEquipmentFrame.setVisible(true);
        dispose();
	}
	
	private void addUpdateEquipmentBundleActionPerformed(ActionEvent evt) {

	}
	
	private void setUpNMCInfoActionPerformed(ActionEvent evt) {

	}
	
	private void initiateAssignmentsActionPerformed(ActionEvent evt) {
		callController(() -> AssignmentController.initiateAssignment());
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
