package ca.mcgill.ecse.climbsafe.view;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.awt.Font;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet.ColorAttribute;
import ca.mcgill.ecse.climbsafe.application.*;

import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.model.Equipment;

import java.sql.*;
import java.time.*;

import java.awt.event.ActionEvent;

public class BundleFrame extends JFrame {

	private static final long serialVersionUID = -4426310869335015542L;

	List<String> selectedEquipmentNames = new ArrayList<String>();
	List<Integer> selectedEquipmentQuantities = new ArrayList<Integer>();
	List<String> selectedNewEquipmentNames = new ArrayList<String>();
	List<Integer> selectedNewEquipmentQuantities = new ArrayList<Integer>();

	private JLabel errorMessage = new JLabel(); // element for error message

	// to edit/remove bundle bundles

	private JTextField bundleNameTextField = new JTextField();
	private JLabel bundleNameLabel = new JLabel("Bundle:");

	private JComboBox<String> itemBox = new JComboBox<>();
	private JLabel itemNameLabel = new JLabel("Item");

	private JTextField itemQTextField = new JTextField();
	private JLabel itemQLabel = new JLabel("Quantities:");

	private JButton addItemButton = new JButton("Add Item");

	private JTextField bundleDiscountTextField = new JTextField();
	private JLabel bundleDiscountLabel = new JLabel("Discount:");

	private JButton addBundleButton = new JButton("Add");

	// to update Bundles
	// private JTextField bundleOldNameTextField = new JTextField();
	private JComboBox<String> oldNameList = new JComboBox<>();
	private JLabel bundleOldNameLabel = new JLabel("Old Name:");

	private JTextField bundleNewNameTextField = new JTextField();
	private JLabel bundleNewNameLabel = new JLabel("New Name:");

	private JComboBox<String> itemNewBox = new JComboBox<>();
	private JLabel itemNewNameLabel = new JLabel("New Equipment Names:");

	private JTextField itemNewQTextField = new JTextField();
	private JLabel itemNewQLabel = new JLabel("New Equipment Quantities:");

	private JButton addUpdatedItemButton = new JButton("Add Updated Item");

	private JTextField bundleNewDiscounttTextField = new JTextField();
	private JLabel bundleNewDiscountLabel = new JLabel("New Discount:");

	private JButton updatebundleButton = new JButton("Update bundle");

	// delete
	private JComboBox<String> nameToDeleteList = new JComboBox<>();
	private JLabel bundleNameToDeleteLabel = new JLabel("Name:");

	private JButton deletebundleButton = new JButton("Delete bundle");

	private JButton previousPage = new JButton("Return to previous page");

	private JTable addEquipmentBundleOverview = new JTable(new DefaultTableModel()) {
		private static final long serialVersionUID = 91L;
	};

	private JScrollPane overviewScrollPane = new JScrollPane(addEquipmentBundleOverview);
	
	private JTable updateEquipmentBundleOverview = new JTable(new DefaultTableModel()) {
		private static final long serialVersionUID = 91L;
	};

	private JScrollPane updateOverviewScrollPane = new JScrollPane(updateEquipmentBundleOverview);
	private static final String[] OVERVIEW_COLUMN_NAMES = { "Booked Item", "Number selected" };

	private String error = "";

	public BundleFrame() {
		initComponents();
		refreshData();
		refreshAddOverview();
		refreshUpdateOverview();
	}

	private void setComboBoxes() {
		for (Equipment e : AddtitionalController.getAllEquipment()) {
			itemNewBox.addItem(e.getName());
			itemBox.addItem(e.getName());
		}
	}

	/** This method is called from within the constructor to initialise the form. */
	private void initComponents() {

		errorMessage.setForeground(Color.RED);
		errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));

		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Climb Safe Application System");

		// listeners for adding bundle
		bundleNameTextField.addActionListener(this::addBundleButtonActionPerformed);
		// itemQTextField.addActionListener(this::addItemButtonActionPerformed);
		// itemBox.addActionListener(this::addItemButtonActionPerformed);
		addItemButton.addActionListener(this::addItemButtonActionPerformed);

		bundleDiscountTextField.addActionListener(this::addBundleButtonActionPerformed);
		addBundleButton.addActionListener(this::addBundleButtonActionPerformed);
		addUpdatedItemButton.addActionListener(this::addUpdatedItemActionPerformed);

		////////////////////////////////////

		// listeners for updating bundle
		// bundleOldNameTextField.addActionListener(this::updatebundleButtonActionPerformed);
		// oldNameList.addActionListener(this::updatebundleButtonActionPerformed); //
		// not sure to do this
		bundleNewNameTextField.addActionListener(this::updateBundleButtonActionPerformed);
		itemNewQTextField.addActionListener(this::addUpdatedItemActionPerformed);

		bundleNewDiscounttTextField.addActionListener(this::updateBundleButtonActionPerformed);
		updatebundleButton.addActionListener(this::updateBundleButtonActionPerformed);
		previousPage.addActionListener(this::backToPreviousPage);
		//////////////////////////////////////

		// listeners for deleting bundle
		// bundleNameToDeleteTextField.addActionListener(this::
		// deletebundleButtonActionPerformed);
		// nameToDeleteList.addActionListener(this::deletebundleButtonActionPerformed);
		// //not sure to do this
		deletebundleButton.addActionListener(this::deleteBundleButtonActionPerformed);
		//////////////////////////////////////

		// lists

		// horizontal line elements
		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage).addComponent(overviewScrollPane, 400, 400, 400)
				.addComponent(updateOverviewScrollPane, 400, 400, 400)
				.addComponent(horizontalLineTop).addComponent(horizontalLineBottom)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(bundleNameLabel).addComponent(itemNameLabel)
								.addComponent(itemQLabel).addComponent(bundleDiscountLabel)
								.addComponent(bundleOldNameLabel).addComponent(bundleNewNameLabel)
								.addComponent(itemNewNameLabel).addComponent(itemNewQLabel)
								.addComponent(bundleNewDiscountLabel).addComponent(bundleNameToDeleteLabel))
						.addGroup(layout.createParallelGroup().addComponent(bundleNameTextField, 200, 200, 400)
								.addComponent(itemBox).addComponent(itemQTextField, 200, 200, 400)
								.addComponent(addItemButton).addComponent(bundleDiscountTextField, 200, 200, 400)
								.addComponent(addBundleButton)
								.addComponent(oldNameList).addComponent(bundleNewNameTextField, 200, 200, 400)
								.addComponent(itemNewBox).addComponent(itemNewQTextField, 200, 200, 400)
								.addComponent(addUpdatedItemButton)
								.addComponent(bundleNewDiscounttTextField, 200, 200, 400)
								.addComponent(updatebundleButton)
								.addComponent(deletebundleButton).addComponent(nameToDeleteList)
								.addComponent(previousPage)

						)
				// .addComponent(overviewScrollPane)
				));

		layout.linkSize(SwingConstants.HORIZONTAL, addBundleButton, bundleNameTextField, itemQTextField,
				bundleDiscountTextField);
		layout.linkSize(SwingConstants.HORIZONTAL, updatebundleButton,
				/* bundleOldNameTextField, */ bundleNewNameTextField, itemNewQTextField, bundleNewDiscounttTextField);
		layout.linkSize(SwingConstants.HORIZONTAL, deletebundleButton /* ,bundleNameToDeleteTextField */);

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(bundleNameLabel).addComponent(bundleNameTextField))
				.addGroup(layout.createParallelGroup().addComponent(itemNameLabel).addComponent(itemBox))
				.addGroup(layout.createParallelGroup().addComponent(itemQLabel).addComponent(itemQTextField))
				.addGroup(layout.createParallelGroup().addComponent(addItemButton))
				.addGroup(layout.createParallelGroup().addComponent(bundleDiscountLabel)
						.addComponent(bundleDiscountTextField))
				.addComponent(overviewScrollPane, 100, 100, 100)
				.addGroup(layout.createParallelGroup().addComponent(addBundleButton))
				
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineTop))
				.addGroup(layout.createParallelGroup().addComponent(bundleOldNameLabel).addComponent(oldNameList))
				.addGroup(layout.createParallelGroup().addComponent(bundleNewNameLabel)
						.addComponent(bundleNewNameTextField))
				.addGroup(layout.createParallelGroup().addComponent(itemNewNameLabel).addComponent(itemNewBox))
				.addGroup(layout.createParallelGroup().addComponent(itemNewQLabel).addComponent(itemNewQTextField))
				.addGroup(layout.createParallelGroup().addComponent(addUpdatedItemButton))
				.addGroup(layout.createParallelGroup().addComponent(bundleNewDiscountLabel)
						.addComponent(bundleNewDiscounttTextField))
				.addComponent(updateOverviewScrollPane, 100, 100, 100)
				.addGroup(layout.createParallelGroup().addComponent(updatebundleButton))
	
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineBottom))
				.addGroup(layout.createParallelGroup().addComponent(bundleNameToDeleteLabel)
						.addComponent(nameToDeleteList))
				.addGroup(layout.createParallelGroup().addComponent(deletebundleButton)).addComponent(previousPage)

		);
		setComboBoxes();
		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}

	private void refreshData() {
		errorMessage.setText(error);
		if (error == null || error.isEmpty()) {
			// populate page with data

			// add bundle
			bundleNameTextField.setText("");
			bundleDiscountTextField.setText("");
			itemQTextField.setText("");

			// update bundle
			// bundleOldNameTextField.setText("");
			bundleNewNameTextField.setText("");
			bundleNewDiscounttTextField.setText("");
			itemNewQLabel.setText("");

			var lists = List.of(oldNameList, nameToDeleteList);
			lists.forEach(JComboBox::removeAllItems);

			AddtitionalController.getBundleStrings().forEach(oldNameList::addItem);
			AddtitionalController.getBundleStrings().forEach(nameToDeleteList::addItem);

			lists.forEach(list -> list.setSelectedIndex(-1));

		}
		pack();
	}

	private void refreshAddOverview() {
		errorMessage.setText(error);
		if (error == null || error.isEmpty()) {
			var overviewDtm = new DefaultTableModel(0, 0);
			overviewDtm.setColumnIdentifiers(OVERVIEW_COLUMN_NAMES);
			addEquipmentBundleOverview.setModel(overviewDtm);
			if (oldNameList.getSelectedItem() != null) {
				DefaultTableModel model = (DefaultTableModel) addEquipmentBundleOverview.getModel();
				for (int i = 0; i < selectedEquipmentNames.size(); i++) {
					model.addRow(new Object[] { selectedEquipmentNames.get(i), selectedEquipmentQuantities.get(i) });
				}
			}
			itemBox.setSelectedIndex(0);
		}
	}
	
	private void refreshUpdateOverview() {
		errorMessage.setText(error);
		if (error == null || error.isEmpty()) {
			var overviewDtm = new DefaultTableModel(0, 0);
			overviewDtm.setColumnIdentifiers(OVERVIEW_COLUMN_NAMES);
			updateEquipmentBundleOverview.setModel(overviewDtm);
			if (oldNameList.getSelectedItem() != null) {
				DefaultTableModel model = (DefaultTableModel) updateEquipmentBundleOverview.getModel();
				for (int i = 0; i < selectedNewEquipmentNames.size(); i++) {
					model.addRow(new Object[] { selectedNewEquipmentNames.get(i), selectedNewEquipmentQuantities.get(i) });
				}
			}
			oldNameList.setSelectedIndex(0);
		}
	}

	private void addItemButtonActionPerformed(ActionEvent evt) {
		error = "";
		Integer amt = Integer.valueOf(itemQTextField.getText());
		String name = (String) itemBox.getSelectedItem();

		if (name.equals("") || itemQTextField.getText().equals("")) {
			error = "Please fill all fields!";
		}
		if (selectedEquipmentNames.contains(name)) {
			error += " " + name + " is already added!";
		}
		if (error.isEmpty()) {
			System.out.println("added");
			selectedEquipmentNames.add(name);
			selectedEquipmentQuantities.add(amt);
			refreshAddOverview();

		}

	}

	private void addBundleButtonActionPerformed(ActionEvent evt) {

		// clear error message
		error = "";

		if (bundleNameTextField.getText().equals("") || bundleDiscountTextField.getText().equals("")) {
			error = "Please fill all fields!";
		}

		int discount = getNumberFromField(bundleDiscountTextField, "must be a number");
		error.trim();

		if (error.isEmpty()) {
			System.out.println(selectedEquipmentNames.toString());
			callController(() -> ClimbSafeFeatureSet5Controller.addEquipmentBundle(bundleNameTextField.getText(),
					discount, selectedEquipmentNames, selectedEquipmentQuantities));
			selectedEquipmentNames.clear();
			selectedEquipmentQuantities.clear();
			refreshAddOverview();
			refreshUpdateOverview();
		}

		refreshData();

	}

	private void addUpdatedItemActionPerformed(ActionEvent evt) {
		error = "";
		
		String name = (String) itemNewBox.getSelectedItem();
		Integer amt = 0;
		if (name.equals("") || itemNewQTextField.getText().equals("")) {
			error = "Please fill all fields!";
		} else {
			amt = Integer.valueOf(itemNewQTextField.getText());

		}
		
		if (selectedNewEquipmentNames.contains(name)) {
			error += " " + name + " is already added!";
		}
		
		if (error.isEmpty()) {
//			if (selectedEquipmentNames.contains(name)) {
//				AddtitionalController.updateBundleItem(name, bName, amt);
//			} else {
			selectedNewEquipmentNames.add(name);
			selectedNewEquipmentQuantities.add(amt);
			refreshUpdateOverview();
//			}
		}
		
	}

	private void updateBundleButtonActionPerformed(ActionEvent evt) {

		error = "";
		var selectedBundle = (String) oldNameList.getSelectedItem();
		if (selectedBundle == null) {
			error = "bundle needs to be selected to update it!";
		}
		error = error.trim();

		if (error.isEmpty()) {
			var updatedBundle = selectedBundle;
			callController(() -> ClimbSafeFeatureSet5Controller.updateEquipmentBundle(selectedBundle,
					bundleNewNameTextField.getText(), getNumberFromField(bundleNewDiscounttTextField, error),
					selectedNewEquipmentNames, selectedNewEquipmentQuantities));
			// selectedEquipmentNames.clear();
			// selectedEquipmentQuantities.clear();
		}
		refreshData();
	}

	private void deleteBundleButtonActionPerformed(ActionEvent evt) {

		error = "";

		var selectedBundle = (String) nameToDeleteList.getSelectedItem();
		if (selectedBundle == null) {
			error = "bundle has to be selected for deletion!";
		} else {
			callController(() -> ClimbSafeFeatureSet6Controller.deleteEquipmentBundle(selectedBundle));
		}

		refreshData();
	}

	/**
	 * Returns the number from the given text field if present, otherwise appends
	 * error string to the given message.
	 */
	private int getNumberFromField(JTextField field, String errorMessage) {
		try {
			return Integer.parseInt(field.getText());
		} catch (NumberFormatException e) {
			error += errorMessage;
		}
		return 0;
	}

	private void backToPreviousPage(ActionEvent evt) {
		HomePageAdminFrame homepage = new HomePageAdminFrame();
		homepage.setVisible(true);
		dispose();
	}

	/**
	 * Calls the controller and sets the error message.
	 * 
	 * @param executable a controller call preceded by "() -> ", ,<br>
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