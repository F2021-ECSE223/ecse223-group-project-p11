package ca.mcgill.ecse.climbsafe.view;

import java.awt.*;
import java.util.*;
import java.util.List;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.*;

import ca.mcgill.ecse.climbsafe.view.*;
import ca.mcgill.ecse.climbsafe.controller.*;

public class NMCSetUpInfoFrame extends JFrame {

	private static final long serialVersionUID = 2;
	private JLabel errorMessage = new JLabel();
	private String error = "";
	// Set date format of input box to;"year-month-day"
	private DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	//private JFormattedTextField startDate = new JFormattedTextField(format);
	private JTextField startDate = new JTextField();
	private JLabel startDateLabel = new JLabel("Start Date (yyyy-mm-dd)");

	private JTextField numberOfWeeks = new JTextField();
	private JLabel numberOfWeeksLabel = new JLabel("Number of Weeks:");

	private JTextField weeklyGuidePrice = new JTextField();
	private JLabel weeklyGuidePriceLabel = new JLabel("Weekly Price of Guides:");

	private JButton applyAllButton = new JButton("Apply All");

	private JButton previousPage = new JButton("Return to previous page");

	private Date today = new Date(System.currentTimeMillis());

	// get instance of climbsafe

	public NMCSetUpInfoFrame() {
		refreshData();
		initComponents();
	}

	/**
	 * @author Lee Brickman Create formatting for error message and initialize
	 *         components IFF "Apply All" button is selected
	 */
	private void initComponents() {
		errorMessage.setForeground(Color.RED);
		errorMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessage.getFont().getSize()));
		applyAllButton.addActionListener(this::applyAllActionPerformed);
		previousPage.addActionListener(this::backToPreviousPage);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Climb Safe Application System");
		setSize(500, 500);

		JSeparator horizontalLineTop = new JSeparator();
		JSeparator horizontalLineBottom = new JSeparator();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup().addComponent(errorMessage).addComponent(horizontalLineBottom)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup().addComponent(startDateLabel, 200, 200, 400)
										.addComponent(numberOfWeeksLabel).addComponent(weeklyGuidePriceLabel))
								.addGroup(layout.createParallelGroup().addComponent(startDate, 500, 500, 1000)
										.addComponent(numberOfWeeks, 500, 500, 1000)
										.addComponent(weeklyGuidePrice, 500, 500, 1000)
										.addComponent(applyAllButton, 500, 500, 1000).addComponent(previousPage))));

		layout.linkSize(SwingConstants.HORIZONTAL, startDate, numberOfWeeks, weeklyGuidePrice);
		layout.linkSize(SwingConstants.HORIZONTAL, applyAllButton);

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(errorMessage)
				.addGroup(layout.createParallelGroup().addComponent(startDateLabel).addComponent(startDate))
				.addGroup(layout.createParallelGroup().addComponent(numberOfWeeksLabel).addComponent(numberOfWeeks))
				.addGroup(
						layout.createParallelGroup().addComponent(weeklyGuidePriceLabel).addComponent(weeklyGuidePrice))
				.addComponent(applyAllButton).addComponent(previousPage)
				.addGroup(layout.createParallelGroup().addComponent(horizontalLineBottom)));

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * @author Lee Brickman set text boxes to empty
	 */
	private void refreshData() {
		errorMessage.setText(error);
		if (error == null || error.isEmpty()) {
			// populate page with empty text boxes

			numberOfWeeks.setText("");
			weeklyGuidePrice.setText("");
			startDate.setText("");
		} else {
			numberOfWeeks.setText("");
			weeklyGuidePrice.setText("");
			startDate.setText("");
		}

	}

	/**
	 * @author Lee Brickman
	 * @param date return true if date is valid (february 30th would return false)
	 * @return
	 */

	public boolean isRealDate(String date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try {
			format.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	/**
	 * @author Lee Brickman
	 * @param str
	 * check if inputted characters are actually acceptable integers
	 * @return
	 */
	public boolean characterCheck(JTextField str) {
	    for (int i = 0; i <= str.getText().length() - 1; i++) {
	      if (str.getText().charAt(0)=='\u2014') {
	        continue;}
	      if (Character.isDigit(str.getText().charAt(i)) == false) {
	        return false;}
	      }
	    return true;

	  }

	/**
	 * @author Lee Brickman
	 * @param e if date is valid and all text boxes have entered values,call the
	 *          controller and apply data entered
	 */
	private void applyAllActionPerformed(ActionEvent e) {
	  error = "";
	    if(characterCheck(numberOfWeeks)==false) {
	      error = "The number of weeks must be a positive integer";
	      refreshData();}
	    if (characterCheck(weeklyGuidePrice)==false) {
	      error = "The weekly price of guides must be a positive integer";
	      refreshData();}
	    

	   if (error.isEmpty() ) {
	     if (startDate.getText().isBlank()||numberOfWeeks.getText().isBlank()||weeklyGuidePrice.getText().isBlank()) {
	       error = "Please fill out all fields";
	       refreshData();}
	     if (isRealDate(startDate.getText())==true){
	         if (Date.valueOf(startDate.getText()).before(today)) {
	           error = "Invalid date";
	           refreshData();}
	         else {
	           callController(()-> ClimbSafeFeatureSet1Controller.setup(Date.valueOf(startDate.getText()),Integer.valueOf(numberOfWeeks.getText()),Integer.valueOf(weeklyGuidePrice.getText())));
	           refreshData();}}
	     else {
	       error = "Invalid Date";
	       refreshData();
	     }}
	     else {
	       refreshData();
	     }
	}



	private void backToPreviousPage(ActionEvent evt) {
        HomePageAdminFrame homePage = new HomePageAdminFrame();
        homePage.setVisible(true);
        dispose();
	}
	/**
	 * @author Lee Brickman
	 * @param executable implements controller call
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

	interface Executable {
		public void execute() throws Throwable;
	}

}
