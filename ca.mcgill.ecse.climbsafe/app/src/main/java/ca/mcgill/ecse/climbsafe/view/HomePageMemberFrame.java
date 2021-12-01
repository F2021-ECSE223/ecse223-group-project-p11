package ca.mcgill.ecse.climbsafe.view;

import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

public class HomePageMemberFrame extends JFrame {
	private JButton updateOrModifyAccount = new JButton("Update Information or Delete Account");

	private JButton payTrip = new JButton("Pay trip");
	
	private JLabel information = new JLabel("information");
	
	private JButton previousPage = new JButton("Return to previous page");

	private String email;

	public HomePageMemberFrame() {
		this.email = "test";
		initComponents();
	}

	
	private void initComponents() {
		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Climb Safe Application System");
		setSize(500, 500);

		updateOrModifyAccount.addActionListener(this::updateOrModifyAccountActionPerformed);
		payTrip.addActionListener(this::payTripActionPerformed);
		previousPage.addActionListener(this::backToPreviousPage);

		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(horizontalLineTop).addComponent(updateOrModifyAccount).addComponent(payTrip).addComponent(previousPage).addComponent(horizontalLineBottom));
		
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(horizontalLineTop).addComponent(updateOrModifyAccount).addComponent(payTrip).addComponent(previousPage).addComponent(horizontalLineBottom));
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	private void updateOrModifyAccountActionPerformed(ActionEvent evt) {
        MemberFrame2 updateMemberFrame = new MemberFrame2(this.email);
        updateMemberFrame.setVisible(true);
        dispose();
	}
	
	private void payTripActionPerformed(ActionEvent evt) {
        PayTripFrame payTripFrame = new PayTripFrame(email);
        payTripFrame.setVisible(true);
        dispose();
	}
	
	private void backToPreviousPage(ActionEvent evt) {
        InitialHomePage initialPage = new InitialHomePage();
        initialPage.setVisible(true);
        dispose();
	}
}
