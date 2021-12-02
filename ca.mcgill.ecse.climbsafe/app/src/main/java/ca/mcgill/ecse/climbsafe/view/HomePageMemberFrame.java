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

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addGap(25)
				.addComponent(updateOrModifyAccount, 500, 500, 1000)
				.addComponent(payTrip, 500, 500, 1000)
				.addComponent(previousPage, 500, 500, 1000).addGap(25));
		
		layout.setVerticalGroup(layout.createSequentialGroup().addGap(25)
				.addComponent(updateOrModifyAccount,  50, 50, 50)
				.addComponent(payTrip,  50, 50, 50)
				.addComponent(previousPage,  50, 50, 50).addGap(25));
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	private void updateOrModifyAccountActionPerformed(ActionEvent evt) {
        MemberFrame2 updateMemberFrame = new MemberFrame2();
        updateMemberFrame.setVisible(true);
        dispose();
	}
	
	private void payTripActionPerformed(ActionEvent evt) {
        PayTripFrame payTripFrame = new PayTripFrame();
        payTripFrame.setVisible(true);
        dispose();
	}
	
	private void backToPreviousPage(ActionEvent evt) {
        InitialHomePage initialPage = new InitialHomePage();
        initialPage.setVisible(true);
        dispose();
	}
}
