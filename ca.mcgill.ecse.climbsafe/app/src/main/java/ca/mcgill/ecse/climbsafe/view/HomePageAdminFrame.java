package ca.mcgill.ecse.climbsafe.view;

import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

public class HomePageAdminFrame extends JFrame {

	
	private JButton banMember = new JButton("Ban Member");

	private JButton startCancelFinishTrips = new JButton("Start, Finish or Cancel Trips");

	private JButton addUpdateEquipment = new JButton("Add, Update or Delete Equipment");

	private JButton addUpdateEquipmentBundle = new JButton("Add, Update or Delete Equipment Bundles");

	private JButton setUpNMCInfo = new JButton("Set up NMC information");
	
	private JButton initiateAssignments = new JButton("Initiate Assignments");

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
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(horizontalLineTop)
				.addComponent(banMember)
				.addComponent(startCancelFinishTrips)
				.addComponent(addUpdateEquipment)
				.addComponent(addUpdateEquipmentBundle)
				.addComponent(setUpNMCInfo)
				.addComponent(initiateAssignments)
				.addComponent(horizontalLineBottom));
		
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(horizontalLineTop)
				.addComponent(banMember)
				.addComponent(startCancelFinishTrips)
				.addComponent(addUpdateEquipment)
				.addComponent(addUpdateEquipmentBundle)
				.addComponent(setUpNMCInfo)
				.addComponent(initiateAssignments)
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

	}
	
}
