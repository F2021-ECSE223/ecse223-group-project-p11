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
	// maybe remove status below
	private static final String[] ASSIGNMENT_COLUMN_NAMES = { "memberEmail", "memberName", "guideEmail", "guideName",
			"hotelName", "startWeek", "endWeek", "totalCostForGuide", "totalCostForEquipment", "Status" };
	private static final int HEIGHT_OVERVIEW_TABLE = 200;

	public ViewAssignmentFrame() {// maybe
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
			overviewDtm
					.addRow(new Object[] { a.getMember().getEmail(), a.getMember().getName(), a.getGuide().getEmail(),
							a.getGuide().getName(), a.getHotel().getName(), a.getStartWeek(), a.getEndWeek(),
							a.getTotalGuideCost(), a.getTotalEquipmentCost(), a.getAssignmentStatusFullName() });
		}
		overviewScrollPane
				.setPreferredSize(new Dimension(assignmentOverview.getPreferredSize().width, HEIGHT_OVERVIEW_TABLE));
	}

	private void initComponents() {
		add(overviewScrollPane);
		overviewScrollPane
				.setPreferredSize(new Dimension(assignmentOverview.getPreferredSize().width, HEIGHT_OVERVIEW_TABLE));
		overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("ClimbSafe Application System");
		// set up comboboxes

	}
}
