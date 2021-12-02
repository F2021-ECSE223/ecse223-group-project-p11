package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.sql.*;
import java.time.*;

import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.view.*;
import ca.mcgill.ecse.climbsafe.controller.*;

public class MemberFrame extends JFrame {
	private String error;
	private String currentAccountEmail = "";
	List<String> selectedItemNames = new ArrayList<String>();
	List<Integer> selectedItemQuantities = new ArrayList<Integer>();
	List<Integer> selectedItemCost = new ArrayList<Integer>();

	// private int maxNumOfWeeks = ClimbSafeApplication.getClimbSafe().getNrWeeks();

	private static final long serialVersionUID = -4426369335015542L;

	// UI elements
	// element for error message
	private JLabel errorMessage = new JLabel();

	// member
	private JTextField memberNameTextField = new JTextField();
	private JLabel memberNameLabel = new JLabel("Name:");

	private JTextField memberEmailTextField = new JTextField();
	private JLabel memberEmailLabel = new JLabel("Email:");

	private JTextField memberPasswordTextField = new JTextField();
	private JLabel memberPasswordLabel = new JLabel("Password:");

	private JTextField memberEmergencyContactTextField = new JTextField();
	private JLabel memberEmergencyContactLabel = new JLabel("Emergency Contact Number:");

	private JCheckBox memberGuideCheckBox = new JCheckBox("Guide", false);
	private JCheckBox memberHotelCheckBox = new JCheckBox("Hotel", false);

	// increments week numbers by one, replace the 50 with whatever the max num of
	// weeks is when NMC is setup

	private JTextField memberWeekNumberTextField = new JTextField();
	private JLabel memberWeekNumberLabel = new JLabel("Number of weeks");

	private JButton addMemberButton = new JButton("Create Account and Book Trip");

	// private JButton deleteMemberButton = new JButton("Delete Account");

	private JComboBox<String> equipmentAvailableList = new JComboBox<>();
	private JButton addEquipmentButton = new JButton("Add Selected Item");
	private JLabel equipmentLabel = new JLabel("Select Item");
	private JTextField equipmentNumberTextField = new JTextField();
	private JLabel equipmentNumberLabel = new JLabel("Number of Selected Item");

	// viewing the selected equipment in table for registering member

	private JButton deleteItemButton = new JButton("Remove Selected Item");
	// private JComboBox<String> selectedItemsList = new JComboBox<>();

	private JButton previousPage = new JButton("Return to previous page");

	private JTable equipmentOverview = new JTable(new DefaultTableModel()) {
		private static final long serialVersionUID = 99L;
	}; /*
		 * {
		 * 
		 * private static final long serialVersionUID = 99L;
		 * 
		 * @Override public Component prepareRenderer(TableCellRenderer renderer, int
		 * row, int column) { Component c = super.prepareRenderer(renderer, row,
		 * column); if (!c.getBackground().equals(getSelectionBackground())) {
		 * 
		 * c.setBackground(Color.WHITE);
		 * 
		 * } return c; } };
		 */

	private JScrollPane overviewScrollPane = new JScrollPane(equipmentOverview);

	private static final String[] OVERVIEW_COLUMN_NAMES = { "Booked Item", "Number Selected", "Individual Item Cost" };
	private static final int HEIGHT_OVERVIEW_TABLE = 200;

	/*
	 * 
	 * 
	 * 
	 * private JComboBox<TOMember> memberToggleList = new JComboBox<>(); private
	 * JLabel memberToggleLabel = new JLabel("Select Member:");
	 * 
	 * 
	 */
	// private JButton updateMemberButton = new JButton("Update Account");

	public MemberFrame() {
		refreshData();
		initComponents();
	}

	private void initComponents() {
		errorMessage.setForeground(Color.RED);
		errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));

		add(overviewScrollPane);
		overviewScrollPane
				.setPreferredSize(new Dimension(equipmentOverview.getPreferredSize().width, HEIGHT_OVERVIEW_TABLE));
		overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("ClimbSafe Application System");

		List<String> systemEquipment = AddtitionalController.getEquipmentStrings();
		List<String> systemBundles = AddtitionalController.getBundleStrings();

		equipmentAvailableList.addItem("---------");

		for (String i : systemEquipment) {
			equipmentAvailableList.addItem(i);
		}

		for (String j : systemBundles) {
			equipmentAvailableList.addItem(j);
		}
		// listeners for member
		addMemberButton.addActionListener(this::addMemberButtonActionPerformed); // Respond to Enter/Return key

		// deleteMemberButton.addActionListener(this::deleteMemberButtonActionPerformed);

		// updateMemberButton.addActionListener(this::updateMemberButtonActionPerformed);

		addEquipmentButton.addActionListener(this::addEquipmentButtonActionPerformed);

		deleteItemButton.addActionListener(this::deleteItemButtonActionPerformed);

		previousPage.addActionListener(this::backToPreviousPage);

		JSeparator horizontalLine1 = new JSeparator();
		JSeparator horizontalLine2 = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage).addComponent(horizontalLine1)
				.addComponent(horizontalLine2).addComponent(overviewScrollPane).addGroup(layout.createSequentialGroup()

						.addGroup(layout.createParallelGroup().addComponent(memberNameLabel)
								.addComponent(memberEmailLabel).addComponent(memberPasswordLabel)
								.addComponent(memberEmergencyContactLabel).addComponent(memberWeekNumberLabel)
								.addComponent(memberGuideCheckBox).addComponent(memberHotelCheckBox)
								.addComponent(equipmentLabel).addComponent(equipmentNumberLabel)
								.addComponent(addMemberButton).addComponent(deleteItemButton).addComponent(previousPage))

						.addGroup(layout.createParallelGroup().addComponent(memberNameTextField, 200, 200, 400)
								.addComponent(memberEmailTextField, 200, 200, 400)
								.addComponent(memberPasswordTextField, 200, 200, 400)
								.addComponent(memberEmergencyContactTextField, 200, 200, 400)
								.addComponent(memberWeekNumberTextField, 200, 200, 400)
								.addComponent(equipmentNumberTextField, 200, 200, 400)
								.addComponent(equipmentAvailableList).addComponent(addEquipmentButton))
						));

		layout.linkSize(SwingConstants.HORIZONTAL, addEquipmentButton, deleteItemButton, memberNameTextField,
				memberEmailTextField, memberPasswordTextField, memberEmergencyContactTextField,
				memberWeekNumberTextField, memberGuideCheckBox, memberHotelCheckBox, equipmentAvailableList);

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(memberNameLabel).addComponent(memberNameTextField))
				.addGroup(
						layout.createParallelGroup().addComponent(memberEmailLabel).addComponent(memberEmailTextField))
				.addGroup(layout.createParallelGroup().addComponent(memberPasswordLabel)
						.addComponent(memberPasswordTextField))
				.addGroup(layout.createParallelGroup().addComponent(memberEmergencyContactLabel)
						.addComponent(memberEmergencyContactTextField))
				.addGroup(layout.createParallelGroup().addComponent(memberWeekNumberLabel)
						.addComponent(memberWeekNumberTextField))
				.addGroup(layout.createParallelGroup().addComponent(memberGuideCheckBox))
				.addGroup(layout.createParallelGroup().addComponent(memberHotelCheckBox))
				.addGroup(
						layout.createParallelGroup().addComponent(equipmentLabel).addComponent(equipmentAvailableList))
				.addGroup(layout.createParallelGroup().addComponent(equipmentNumberLabel)
						.addComponent(equipmentNumberTextField))
				.addGroup(layout.createParallelGroup().addComponent(addEquipmentButton).addComponent(deleteItemButton))
				.addGroup(layout.createParallelGroup().addComponent(horizontalLine1))
				.addGroup(layout.createParallelGroup().addComponent(addMemberButton))
				.addComponent(previousPage)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLine2))
				/*
				 * .addGroup(layout.createParallelGroup() .addComponent(updateMemberButton) )
				 */
				/*
				 * .addGroup(layout.createParallelGroup() .addComponent(horizontalLineBottom) )
				 * 
				 * /*.addGroup(layout.createParallelGroup() .addComponent(deleteMemberButton) )
				 */
				.addComponent(overviewScrollPane)

		);

		pack();
		setLocationRelativeTo(null); // set window location to be in the center of the screen
		setResizable(false);
		setVisible(true);
	}

	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.isEmpty()) {
			// populate page with data

			memberNameTextField.setText("");
			memberPasswordTextField.setText("");
			memberEmailTextField.setText("");
			memberEmergencyContactTextField.setText("");
			memberWeekNumberTextField.setText("");
			memberHotelCheckBox.setSelected(false);
			memberGuideCheckBox.setSelected(false);

		}
	}

	private void refreshEquipment() {
		equipmentNumberTextField.setText("");

	}

	private void refreshOverview() {
		errorMessage.setText(error);
		if (error == null || error.isEmpty()) {
			var overviewDtm = new DefaultTableModel(0, 0);
			overviewDtm.setColumnIdentifiers(OVERVIEW_COLUMN_NAMES);
			equipmentOverview.setModel(overviewDtm);
			if (equipmentAvailableList.getSelectedItem() != null) {
				// String equipmentNameText =
				// equipmentAvailableList.getSelectedItem().toString();
				DefaultTableModel model = (DefaultTableModel) equipmentOverview.getModel();
				for (int i = 0; i < selectedItemNames.size(); i++) {
					model.addRow(new Object[] { selectedItemNames.get(i), selectedItemQuantities.get(i),
							selectedItemCost.get(i) });
				}
			}
			equipmentAvailableList.setSelectedIndex(0);
		}

	}

	private void addMemberButtonActionPerformed(ActionEvent evt) {
		// clear error message
		error = "";

		if (memberEmailTextField.getText().equals("") || memberPasswordTextField.getText().equals("")
				|| memberNameTextField.getText().equals("") || memberEmergencyContactTextField.getText().equals("")
				|| memberWeekNumberTextField.getText().equals("")) {
			error = "Please fill all Fields! ";
		}

		int weekNumber = getNumberFromField(memberWeekNumberTextField, "Must be a number!");
		error.trim();

		if (error.isEmpty()) {

			// temporary until other frames finished

			callController(() -> ClimbSafeFeatureSet2Controller.registerMember(memberEmailTextField.getText(),
					memberPasswordTextField.getText(), memberNameTextField.getText(),
					memberEmergencyContactTextField.getText(), weekNumber, memberGuideCheckBox.isSelected(),
					memberHotelCheckBox.isSelected(), selectedItemNames, selectedItemQuantities));

			if (error.isEmpty()) {
				HomePageMemberFrame homepage = new HomePageMemberFrame();
				homepage.setVisible(true);
				dispose();
				selectedItemNames.clear();
				selectedItemQuantities.clear();
				selectedItemCost.clear();
				refreshOverview();
			}
		}
		// update visuals
		refreshData();
		refreshEquipment();
	}

	private void deleteItemButtonActionPerformed(ActionEvent evt) {
		error = "";
		String equipmentNameText2 = equipmentAvailableList.getSelectedItem().toString();
		if (equipmentNameText2.equals("---------")) {
			error = "Select an Item to remove it";
		}

		if (!selectedItemNames.contains(equipmentNameText2)) {
			error = "Add an Item before Removing";
		}

		errorMessage.setText(error);
		if (error.isEmpty()) {
			for (int j = 0; j < selectedItemNames.size(); j++) {
				if (selectedItemNames.get(j).equals(equipmentNameText2)) {
					selectedItemNames.remove(j);
					selectedItemQuantities.remove(j);
					selectedItemCost.remove(j);
				}

			}

			refreshOverview();
			refreshEquipment();
		}

	}

	private void addEquipmentButtonActionPerformed(ActionEvent evt) {

		error = "";
		String equipmentNameText2 = equipmentAvailableList.getSelectedItem().toString();

		int equipmentNumber = getNumberFromField(equipmentNumberTextField, "Must be a number!");
		error.trim();

		if (equipmentNumberTextField.getText().equals("")) {
			error = "Fill Number Field before adding";
		}

		if (equipmentNameText2.equals("---------")) {
			error = "Select an Item in order to add it to your booking";
		}

		errorMessage.setText(error);

		if (error.isEmpty()) {

			if (!selectedItemNames.contains(equipmentNameText2)) {
				selectedItemNames.add(equipmentNameText2);
				selectedItemQuantities.add(equipmentNumber);
				selectedItemCost.add(AddtitionalController.getItemCost(equipmentNameText2));
				refreshOverview();
				refreshEquipment();
			} else {
				error = "Item already selected";
				refreshData();
			}
		}
	}

	private int getNumberFromField(JTextField field, String errorMessage) {
		try {
			return Integer.parseInt(field.getText());
		} catch (NumberFormatException e) {
			if (error.equals("")) {
				error += errorMessage;
			}
		}
		return 0;
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

	private void backToPreviousPage(ActionEvent evt) {
        InitialHomePage initialPage = new InitialHomePage();
        initialPage.setVisible(true);
        dispose();
	}

	@FunctionalInterface
	interface Executable {
		public void execute() throws Throwable;
	}

}
