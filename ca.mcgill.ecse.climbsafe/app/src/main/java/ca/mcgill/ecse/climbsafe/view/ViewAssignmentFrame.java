package ca.mcgill.ecse.climbsafe.view;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet.ColorAttribute;
import ca.mcgill.ecse.climbsafe.application.*;
import javax.swing.table.DefaultTableModel;
import ca.mcgill.ecse.climbsafe.controller.*;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOEquipment;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.controller.AddtitionalController;

import java.sql.*;
import java.time.*;

import java.awt.event.ActionEvent;

public class ViewAssignmentFrame extends JFrame {

	private static final long serialVersionUID = 99L;

	private JTable assignmentOverview = new JTable(new DefaultTableModel()) {
		private static final long serialVersionUID = 99L;
	};
	private JScrollPane overviewScrollPane = new JScrollPane(assignmentOverview);

	private JButton previousPage = new JButton("Return to previous page");

	// maybe remove status below
	private static final String[] ASSIGNMENT_COLUMN_NAMES = { "Member Email", "Member Name", "Member Status", "Guide Email", "Guide Name",
			"Hotel Name", "Start Week", "End Week", "Guide Total Cost", "Equipment Total Cost", "Assignment Status", "Authorization Code", "Refund Amount"};
	private static final int HEIGHT_OVERVIEW_TABLE = 200;

	public ViewAssignmentFrame() {// maybe
		setTable();
		initComponents();
	}

	private void setTable() {
		var overviewDtm = new DefaultTableModel(0, 0);
		overviewDtm.setColumnIdentifiers(ASSIGNMENT_COLUMN_NAMES);
		assignmentOverview.setModel(overviewDtm);

		for (Assignment a : AddtitionalController.getAllAssignments()) {
			// { "memberEmail", "memberName", "guideEmail", "guideName" "hotelName",
			// "startWeek", "endWeek", "totalCostForGuide", "totalCostForEquipment",
			// "Status" };
			String guideName;
			String guideEmail;
			String hotel;
			if (a.getGuide() == null) {
				guideName = "No Guide Selected";
				guideEmail = "No Guide Selected";
			} else {
				guideName = a.getGuide().getName();
				guideEmail = a.getGuide().getEmail();
			}
			if (a.getHotel() == null) {
				hotel = "No Hotel Selected";
			} else {
				hotel = a.getHotel().getName();
			}
			overviewDtm.addRow(new Object[] { a.getMember().getEmail(), a.getMember().getName(), a.getMember().getBanStatus(), guideEmail, guideName,
					hotel, a.getStartWeek(), a.getEndWeek(), a.getTotalGuideCost(), a.getTotalEquipmentCost(),
					a.getAssignmentStatusFullName(), a.getGivenAuthorizationCode(), a.getRefundPercentage()});
		}
		overviewScrollPane
				.setPreferredSize(new Dimension(assignmentOverview.getPreferredSize().width, HEIGHT_OVERVIEW_TABLE));
	}

	private void initComponents() {
		previousPage.addActionListener(this::backToPreviousPage);

		
		JSeparator horizontalLineTop = new JSeparator();

		//add(overviewScrollPane);
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(overviewScrollPane).addComponent(horizontalLineTop).addComponent(previousPage));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(overviewScrollPane).addComponent(horizontalLineTop).addComponent(previousPage));
				
		overviewScrollPane
				.setPreferredSize(new Dimension(assignmentOverview.getPreferredSize().width, HEIGHT_OVERVIEW_TABLE));
		overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("ClimbSafe Application System");
		// set up comboboxes
		pack();
		setLocationRelativeTo(null); // set window location to be in the center of the screen
		setResizable(true);
		setVisible(true);

	}

	private void backToPreviousPage(ActionEvent evt) {
		HomePageAdminFrame homepage = new HomePageAdminFrame();
		homepage.setVisible(true);
		dispose();
	}
}
