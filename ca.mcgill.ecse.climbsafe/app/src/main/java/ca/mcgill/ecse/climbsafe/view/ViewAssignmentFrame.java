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
import ca.mcgill.ecse.climbsafe.controller.AddtitionalController;

import java.sql.*;
import java.time.*;

import java.awt.event.ActionEvent;
private JComboBox<String> memberEmails=new JComboBox<>();
for(String email: AddtitionalController.getAssignmentMemberEmails()) {
	memberEmails.addItem(email);
}
private JComboBox<String> memberNames=new JComboBox<>();
for(String names: AddtitionalController.getAssignmentMemberNames()) {
	memberNames.addItem(names);
}
private JComboBox<String> guideEmails=new JComboBox<>();
for(String email: AddtitionalController.getAssignmentGuideEmails()) {
	guideEmails.addItem(email);
}
private JComboBox<String> guideNames=new JComboBox<>();
for(String names: AddtitionalController.getAssignmentGuideNames()) {
	guideNames.addItem(names);
}
private JComboBox<String> hotelNames=new JComboBox<>();
for(String names: AddtitionalController.getAssignmentHotelNames()) {
	hotelNames.addItem(names);
}
private JComboBox<Integer> startWeeks = new JComboBox<>();
for(Integer num: AddtitionalController.getAssignmentStartWeeks()) {
	startWeeks.addItem(num);
}
private JComboBox<Integer> endWeeks = new JComboBox<>();
for(Integer num: AddtitionalController.getAssignmentEndWeeks()) {
	endWeeks.addItem(num);
}
private JComboBox<Integer> totalGuideCosts = new JComboBox<>();
for(Integer num:AddtitionalController.getAssignmentTotalGuideCosts()) {
	totalGuideCosts.addItem(num);
}
private JComboBox<Integer> totalEquipmentCosts = new JComboBox<>();
for(Integer num:AddtitionalController.getAssignmentTotalEquipmentCosts()) {
	totalEquipmentCosts.addItem(num);
}
private JComboBox<String> statuses=new JComboBox<>();
for(String names: AddtitionalController.getAssignmentStatuses()) {
	statuses.addItem(names);
}

public class ViewAssignmentFrame extends JFrame {
	

	private static final long serialVersionUID = 99L;

	
	

	private JTable assignmentOverview = new JTable(new DefaultTableModel()) {
		private static final long serialVersionUID = 99L;
	};
	/*
	 * var assignment= new DefaultTableModel(0,0);
	 * assignment.setColumnIdentifiers(ASSIGNMENT_COLUMN_NAMES);
	 */
	private JScrollPane overviewScrollPane = new JScrollPane(assignmentOverview);
	// maybe remove status below
	private static final String[] ASSIGNMENT_COLUMN_NAMES = { "memberEmail", "memberName", "guideEmail", "guideName",
			"hotelName", "startWeek", "endWeek", "totalCostForGuide", "totalCostForEquipment", "Status" };
	private static final int HEIGHT_OVERVIEW_TABLE = 200;

	public ViewAssignmentFrame() {// maybe
		initComponents();
	}
	
	private void makeTable() {
		
	}
	private void initComponents() {
		  add(overviewScrollPane);
		    overviewScrollPane.setPreferredSize(new Dimension(assignmentOverview.getPreferredSize().width, HEIGHT_OVERVIEW_TABLE));
		    overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		    
		    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setTitle("ClimbSafe Application System");
			//set up comboboxes
		
			
					
	
	  }
}
