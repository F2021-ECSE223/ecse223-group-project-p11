package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.TOGuide;

public class InitialHomePage extends JFrame {

	private JButton adminHomePage = new JButton("Administrator Home Page");

	private JButton memberHomePage = new JButton("Member Home Page");

	private JButton guideHomePage = new JButton("Guide Home Page");

	private JButton registerGuideButton = new JButton("Register as Guide");
	
	private JButton registerMemberButton = new JButton("Register as Member");
		

	public InitialHomePage() {
		initComponents();
	}

	
	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Climb Safe Application System");
		setSize(500, 500);

		// listeners for logging in
		adminHomePage.addActionListener(this::adminHomePageActionPerformed);
		memberHomePage.addActionListener(this::memberHomePageActionPerformed);
		guideHomePage.addActionListener(this::guideHomePageActionPerformed);

		registerGuideButton.addActionListener(this::registerGuideActionPerformed);
		registerMemberButton.addActionListener(this::registerMemberActionPerformed);

		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(horizontalLineBottom)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(adminHomePage).addComponent(memberHomePage).addComponent(guideHomePage).addComponent(registerGuideButton)
								.addComponent(registerMemberButton))));

		// .addComponent(errorMessage)
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(adminHomePage).addComponent(memberHomePage).addComponent(guideHomePage)
				.addComponent(registerGuideButton)
				.addComponent(registerMemberButton)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineBottom)));

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	

	private void adminHomePageActionPerformed(ActionEvent evt) {
        HomePageAdminFrame homepageAdmin = new HomePageAdminFrame();
        homepageAdmin.setVisible(true);
        dispose();
	}

	private void memberHomePageActionPerformed(ActionEvent evt) {
        HomePageMemberFrame homePageMember = new HomePageMemberFrame();
        homePageMember.setVisible(true);
        dispose();
	}

	private void guideHomePageActionPerformed(ActionEvent evt) {
        HomePageGuideFrame homePageGuide = new HomePageGuideFrame();
        homePageGuide.setVisible(true);
        dispose();
	}
	private void registerGuideActionPerformed(ActionEvent evt) {
        RegisterGuideFrame registerGuideFrame = new RegisterGuideFrame();
        registerGuideFrame.setVisible(true);
        dispose();
	}

	private void registerMemberActionPerformed(ActionEvent evt) {
		MemberFrame registerMemberFrame = new MemberFrame();
        registerMemberFrame.setVisible(true);
        dispose();
	}
	
}
