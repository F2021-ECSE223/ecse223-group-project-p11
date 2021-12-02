package ca.mcgill.ecse.climbsafe.view;

import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.TOGuide;

/***
 * 
 * @author Anaëlle Drai Laguéns, Lee Brickman
 *
 */
public class HomePageGuideFrame extends JFrame {

	private JButton updateOrModifyAccount = new JButton("Update Information or Delete Account");

	private JLabel information = new JLabel("information");

	private String email;

	private JButton previousPage = new JButton("Return to previous page");

	public HomePageGuideFrame() {
		this.email = "test";
		initComponents();
	}

	private void initComponents() {
		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Climb Safe Application System");
		setSize(500, 500);

		updateOrModifyAccount.addActionListener(this::updateOrModifyAccountActionPerformed);
		previousPage.addActionListener(this::backToPreviousPage);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addGap(25)
				.addComponent(updateOrModifyAccount, 500, 500, 1000)
				.addComponent(previousPage, 500, 500, 1000).addGap(25)
				);

		layout.setVerticalGroup(layout.createSequentialGroup().addGap(25)
				.addComponent(updateOrModifyAccount,  50, 50, 50)
				.addComponent(previousPage,  50, 50, 50).addGap(25));

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
    /***
     * This method validates that the account has been updated/modified.
     * @author Anaëlle Drai Laguéns
     * @param evt
     */
	private void updateOrModifyAccountActionPerformed(ActionEvent evt) {
		GuideFrame updateGuideFrame = new GuideFrame();
		updateGuideFrame.setVisible(true);
		dispose();
	}
	/***
     * This method validates that the user is going back to the previous page.
     * @author Lee Brickman
     * @param evt
     */
	private void backToPreviousPage(ActionEvent evt) {
		InitialHomePage initialPage = new InitialHomePage();
		initialPage.setVisible(true);
		dispose();
	}
}
