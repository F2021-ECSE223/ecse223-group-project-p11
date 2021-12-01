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

import java.sql.*;
import java.time.*;

import java.awt.event.ActionEvent;

public class BundleFrame extends JFrame {

	private static final long serialVersionUID = -4426310869335015542L;

	List<String> selectedEquipmentNames = new ArrayList<String>();
	List<Integer> selectedEquipmentQuantities = new ArrayList<Integer>();

	private JLabel errorMessage = new JLabel(); // element for error message

	// to edit/remove bundle bundles

	private JTextField bundleNameTextField = new JTextField();
	private JLabel bundleNameLabel = new JLabel("Bundle:");

	private JTextField bundleDiscountTextField = new JTextField();
	private JLabel bundleDiscountLabel = new JLabel("Discount:");

	private JButton addBundleButton = new JButton("Add:");

	// to update Bundles
	// private JTextField bundleOldNameTextField = new JTextField();
	private JComboBox<String> oldNameList = new JComboBox<>();
	private JLabel bundleOldNameLabel = new JLabel("Old Name:");

	private JTextField bundleNewNameTextField = new JTextField();
	private JLabel bundleNewNameLabel = new JLabel("New Name:");

	private JTextField bundleNewDiscounttTextField = new JTextField();
	private JLabel bundleNewDiscountLabel = new JLabel("New Discount:");

	private JButton updatebundleButton = new JButton("Update bundle");

	// delete
	private JComboBox<String> nameToDeleteList = new JComboBox<>();
	private JLabel bundleNameToDeleteLabel = new JLabel("Name:");

	private JButton deletebundleButton = new JButton("Delete bundle");

	private JButton previousPage = new JButton("Return to previous page");

	private JTable equipmentBundleOverview = new JTable(new DefaultTableModel()) {
		private static final long serialVersionUID = 91L;
	};

	private JScrollPane overviewScrollPane = new JScrollPane(equipmentBundleOverview);

	private static final String[] OVERVIEW_COLUMN_NAMES = { "Booked Item", "Number selected" };

	private String error = "";

	public BundleFrame() {
		initComponents();
		refreshData();
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
		bundleDiscountTextField.addActionListener(this::addBundleButtonActionPerformed);

		addBundleButton.addActionListener(this::addBundleButtonActionPerformed);
		////////////////////////////////////

		// listeners for updating bundle
		// bundleOldNameTextField.addActionListener(this::updatebundleButtonActionPerformed);
		// oldNameList.addActionListener(this::updatebundleButtonActionPerformed); //
		// not sure to do this
		bundleNewNameTextField.addActionListener(this::updatebundleButtonActionPerformed);
		bundleNewDiscounttTextField.addActionListener(this::updatebundleButtonActionPerformed);
		updatebundleButton.addActionListener(this::updatebundleButtonActionPerformed);
		previousPage.addActionListener(this::backToPreviousPage);

		//////////////////////////////////////

		// listeners for deleting bundle
		// bundleNameToDeleteTextField.addActionListener(this::
		// deletebundleButtonActionPerformed);
		// nameToDeleteList.addActionListener(this::deletebundleButtonActionPerformed);
		// //not sure to do this
		deletebundleButton.addActionListener(this::deletebundleButtonActionPerformed);
		//////////////////////////////////////

		// lists

		// horizontal line elements
		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage)
				.addComponent(horizontalLineTop).addComponent(horizontalLineBottom)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup().addComponent(bundleNameLabel)
								.addComponent(bundleDiscountLabel).addComponent(bundleOldNameLabel)
								.addComponent(bundleNewNameLabel).addComponent(bundleNewDiscountLabel)
								.addComponent(bundleNameToDeleteLabel))
						.addGroup(layout.createParallelGroup().addComponent(bundleNameTextField, 200, 200, 400)
								.addComponent(bundleDiscountTextField, 200, 200, 400).addComponent(addBundleButton)
								// .addComponent(bundleToAddList)
								// .addComponent(bundleOldNameTextField,200,200,400)
								.addComponent(oldNameList).addComponent(bundleNewNameTextField, 200, 200, 400)
								.addComponent(bundleNewDiscounttTextField, 200, 200, 400)
								.addComponent(updatebundleButton)
								// .addComponent(bundleToUpdateList)
								// .addComponent(bundleNameToDeleteTextField,200,200,400)
								.addComponent(deletebundleButton).addComponent(nameToDeleteList).addComponent(previousPage)

						)
				// .addComponent(overviewScrollPane)
				));

		layout.linkSize(SwingConstants.HORIZONTAL, addBundleButton, bundleNameTextField, bundleDiscountTextField);
		layout.linkSize(SwingConstants.HORIZONTAL, updatebundleButton,
				/* bundleOldNameTextField, */ bundleNewNameTextField, bundleNewDiscounttTextField);
		layout.linkSize(SwingConstants.HORIZONTAL, deletebundleButton /* ,bundleNameToDeleteTextField */);

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(bundleNameLabel).addComponent(bundleNameTextField))
				.addGroup(layout.createParallelGroup().addComponent(bundleDiscountLabel)
						.addComponent(bundleDiscountTextField))
				.addGroup(layout.createParallelGroup().addComponent(addBundleButton))
				/*
				 * .addGroup(layout.createParallelGroup() .addComponent(bundleToAddList) )
				 */
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineTop))
				.addGroup(layout.createParallelGroup().addComponent(bundleOldNameLabel).addComponent(oldNameList))
				.addGroup(layout.createParallelGroup().addComponent(bundleNewNameLabel)
						.addComponent(bundleNewNameTextField))
				.addGroup(layout.createParallelGroup().addComponent(bundleNewDiscountLabel)
						.addComponent(bundleNewDiscounttTextField))

				.addGroup(layout.createParallelGroup().addComponent(updatebundleButton))
				/*
				 * .addGroup(layout.createParallelGroup() .addComponent(bundleToUpdateList))
				 */
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineBottom))
				.addGroup(layout.createParallelGroup().addComponent(bundleNameToDeleteLabel)
						.addComponent(nameToDeleteList))
				.addGroup(layout.createParallelGroup().addComponent(deletebundleButton))
				.addComponent(previousPage)
		// .addComponent(overviewScrollPane)
		/*
		 * .addGroup(layout.createParallelGroup() .addComponent(bundleToDeleteList))
		 */
		);

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	private void refreshData() {
		errorMessage.setText(error);
		if (error == null || error.isEmpty()) {
			// populate page with data

			// add bundle
			bundleNameTextField.setText("");
			bundleDiscountTextField.setText("");

			// update bundle
			// bundleOldNameTextField.setText("");
			bundleNewNameTextField.setText("");
			bundleNewDiscounttTextField.setText("");

			var lists = List.of(oldNameList, nameToDeleteList);
			lists.forEach(JComboBox::removeAllItems);

			AddtitionalController.getBundleStrings().forEach(oldNameList::addItem);
			AddtitionalController.getBundleStrings().forEach(nameToDeleteList::addItem);

			lists.forEach(list -> list.setSelectedIndex(-1));

		}
		pack();
	}
	/*
	 * private void refreshOverview() { errorMessage.setText(error); if(error ==
	 * null || error.isEmpty()) { var overviewDtm = new DefaultTableModel(0,0);
	 * overviewDtm.setColumnIdentifiers(OVERVIEW_COLUMN_NAMES);
	 * equipmentBundleOverview.setModel(overviewDtm);
	 * if(oldNameList.getSelectedItem()!= null) { DefaultTableModel model =
	 * (DefaultTableModel) equipmentBundleOverview.getModel(); for(int i = 0; i <
	 * selectedEquipmentNames.size();i++) { model.addRow(new Object[]
	 * {selectedEquipmentNames.get(i),selectedEquipmentQuantities.get(i)}); } }
	 * oldNameList.setSelectedIndex(0); } }
	 */

	private void addBundleButtonActionPerformed(ActionEvent evt) {

		// clear error message
		error = "";

		if (bundleNameTextField.getText().equals("") || bundleDiscountTextField.getText().equals("")) {
			error = "Please fill all fields!";
		}

		int discount = getNumberFromField(bundleDiscountTextField, "must be a number");
		error.trim();

		if (error.isEmpty()) {
			callController(() -> ClimbSafeFeatureSet5Controller.addEquipmentBundle(bundleNameTextField.getText(),
					discount, selectedEquipmentNames, selectedEquipmentQuantities));
			// selectedEquipmentNames.clear();
			// selectedEquipmentQuantities.clear();

		}

		refreshData();

	}

	private void updatebundleButtonActionPerformed(ActionEvent evt) {

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
					selectedEquipmentNames, selectedEquipmentQuantities));
			// selectedEquipmentNames.clear();
			// selectedEquipmentQuantities.clear();
		}
		refreshData();
	}

	private void deletebundleButtonActionPerformed(ActionEvent evt) {

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