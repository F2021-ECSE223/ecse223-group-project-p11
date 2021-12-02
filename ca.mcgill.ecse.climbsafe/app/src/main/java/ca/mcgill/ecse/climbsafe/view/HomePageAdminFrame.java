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

/***
 * 
 * @author Oliver Cafferty, Can Akin, Maxime Drouin, Anaëlle Drai Laguéns, Sam Snodgrass, Lee Brickman
 *
 *
 */

public class HomePageAdminFrame extends JFrame {

	private JLabel errorMessage = new JLabel(); // element for error message


	private JButton startCancelFinishTrips = new JButton("Start, Finish or Cancel Trips");

	private JButton addUpdateEquipment = new JButton("Add, Update or Delete Equipment");

	private JButton addUpdateEquipmentBundle = new JButton("Add, Update or Delete Equipment Bundles");

	private JButton setUpNMCInfo = new JButton("Set up NMC information");
	
	private JButton initiateAssignments = new JButton("Initiate Assignments");
	private JButton viewAssignments = new JButton("View Assignments");
	private JButton previousPage = new JButton("Return to previous page");

	private String error = "";

	public HomePageAdminFrame() {
		initComponents();
	}
	
	private void initComponents() {
		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Climb Safe Application System");
		setSize(500, 500);

		startCancelFinishTrips.addActionListener(this::startCancelFinishTripsActionPerformed);
		addUpdateEquipment.addActionListener(this::addUpdateEquipmentActionPerformed);
		addUpdateEquipmentBundle.addActionListener(this::addUpdateEquipmentBundleActionPerformed);
		setUpNMCInfo.addActionListener(this::setUpNMCInfoActionPerformed);
		initiateAssignments.addActionListener(this::initiateAssignmentsActionPerformed);
		previousPage.addActionListener(this::backToPreviousPage);
		viewAssignments.addActionListener(this::ViewAssignmentActionPerformed);

		

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addGap(25)
				.addComponent(errorMessage, 500, 500, 1000)
				.addComponent(startCancelFinishTrips, 500, 500, 1000)
				.addComponent(addUpdateEquipment, 500, 500, 1000)
				.addComponent(addUpdateEquipmentBundle, 500, 500, 1000)
				.addComponent(setUpNMCInfo, 500, 500, 1000)
				.addComponent(initiateAssignments, 500, 500, 1000)
				.addComponent(viewAssignments, 500, 500, 1000)
				.addComponent(previousPage, 500, 500, 1000).addGap(25)
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup().addGap(25)
				.addComponent(errorMessage)
				.addComponent(startCancelFinishTrips, 50, 50, 50)
				.addComponent(addUpdateEquipment, 50, 50, 50)
				.addComponent(addUpdateEquipmentBundle, 50, 50, 50)
				.addComponent(setUpNMCInfo, 50, 50, 50)
				.addComponent(initiateAssignments, 50, 50, 50)
				.addComponent(viewAssignments, 50, 50, 50)
				.addComponent(previousPage, 50, 50, 50).addGap(25)
				);
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	/**
	 * This method brings the user to the ManageTrips frame.
	 * @author Oliver Cafferty 
	 * @param evt
	 */
	private void startCancelFinishTripsActionPerformed(ActionEvent evt) {
        ManageTripsFrame manageTripsFrame = new ManageTripsFrame();
        manageTripsFrame.setVisible(true);
        dispose();
	}
	/**
	 * This method brings the user to the EquipmentFrame frame.
	 * @author Can Akin
	 * @param evt
	 */
	private void addUpdateEquipmentActionPerformed(ActionEvent evt) {
        EquipmentFrame updateEquipmentFrame = new EquipmentFrame();
        updateEquipmentFrame.setVisible(true);
        dispose();
	}
	/**
     * This method brings the user to the ViewAssignment frame.
     * @author Oliver Cafferty
     * @param evt
     */
	private void ViewAssignmentActionPerformed(ActionEvent evt) {
        ViewAssignmentFrame viewAssignmentFrame = new ViewAssignmentFrame();
        viewAssignmentFrame.setVisible(true);
        dispose();
	}
	/**
     * This method brings the user to the ViewAssignment frame.
     * @author Maxime Drouin
     * @param evt
     */
	private void addUpdateEquipmentBundleActionPerformed(ActionEvent evt) {
        BundleFrame bundleFrame = new BundleFrame();
        bundleFrame.setVisible(true);
        dispose();
	}
	/**
     * This method brings the user to the NMCSetUpInfoFrame frame.
     * @author Lee Brickman
     * @param evt
     */
	private void setUpNMCInfoActionPerformed(ActionEvent evt) {
        NMCSetUpInfoFrame nmcInfoFrame = new NMCSetUpInfoFrame();
        nmcInfoFrame.setVisible(true);
        dispose();
	}
	/**
     * This method validates that assignments are initiated.
     * @author Anaëlle Drai Laguéns
     * @param evt
     */
	private void initiateAssignmentsActionPerformed(ActionEvent evt) {
		callController(() -> AssignmentController.initiateAssignment());
	}
	
	/***
     * This method validates the user is going back to the previous page.
     * @author Sam Snodgrass
     * @param evt
     */
	private void backToPreviousPage(ActionEvent evt) {
        InitialHomePage initialPage = new InitialHomePage();
        initialPage.setVisible(true);
        dispose();
	}
	
	/**
	 * Calls the controller and sets the error message.
	 * @author Sam Snodgrass
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
