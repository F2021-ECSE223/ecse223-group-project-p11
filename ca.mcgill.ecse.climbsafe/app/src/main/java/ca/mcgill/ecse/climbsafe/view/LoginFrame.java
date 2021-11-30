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
import ca.mcgill.ecse.climbsafe.controller.TOGuide;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

public class LoginFrame extends JFrame {

	private JLabel errorMessage = new JLabel(); // element for error message

	// Guide Addition labels

	private JTextField emailTextField = new JTextField();
	private JLabel emailLabel = new JLabel("Email:");

	private JTextField passwordTextField = new JTextField();
	private JLabel passwordLabel = new JLabel("Password:");

	private JButton loginButton = new JButton("Add Guide");
	private JButton registerGuideButton = new JButton("Register as Guide");
	private JButton registerMemberButton = new JButton("Register as Member");

	private String error = "";

	public LoginFrame() {
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

		// listeners for logging in
		emailTextField.addActionListener(this::LoginActionPerformed);
		passwordTextField.addActionListener(this::LoginActionPerformed);
		loginButton.addActionListener(this::LoginActionPerformed);

		registerGuideButton.addActionListener(this::registerGuideActionPerformed);
		registerMemberButton.addActionListener(this::registerMemberActionPerformed);

		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
				.addComponent(horizontalLineBottom)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(emailLabel, 200, 200, 400)
								.addComponent(passwordLabel))
						.addGroup(layout.createParallelGroup().addComponent(emailTextField, 500, 500, 1000)
								.addComponent(passwordTextField, 500, 500, 1000).addComponent(loginButton).addComponent(registerGuideButton)
								.addComponent(registerMemberButton))));

		layout.linkSize(SwingConstants.HORIZONTAL, emailTextField, passwordTextField);
		layout.linkSize(SwingConstants.HORIZONTAL, loginButton, registerGuideButton, registerMemberButton);
		// .addComponent(errorMessage)
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(emailLabel).addComponent(emailTextField))
				.addGroup(layout.createParallelGroup().addComponent(passwordLabel).addComponent(passwordTextField))
				.addComponent(loginButton)
				.addComponent(registerGuideButton)
				.addComponent(registerMemberButton)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineBottom)));

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	private void refreshData() {
		errorMessage.setText(error);
		if (error == null || error.isEmpty()) {
			// populate page with data

			emailTextField.setText("");
			passwordTextField.setText("");
			
			pack();

		}

	}

	private void LoginActionPerformed(ActionEvent evt) {

	}

	private void registerGuideActionPerformed(ActionEvent evt) {
        RegisterGuideFrame registerGuideFrame = new RegisterGuideFrame();
        registerGuideFrame.setVisible(true);
        dispose();
	}

	private void registerMemberActionPerformed(ActionEvent evt) {

	}
}
