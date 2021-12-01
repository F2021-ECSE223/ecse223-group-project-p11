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

public class ViewAssignmentFrame extends JFrame {
	List<String> memberEmails= new ArrayList<String>();
	List<String> guideEmails= new ArrayList<String>();
	List<String> equipment= new ArrayList<String>();
	List<Integer> equipmentNumber= new ArrayList<Integer>();
	List<String> bundles= new ArrayList<String>();
	List<Integer> bundleNumber= new ArrayList<Integer>();
	
	private static final long serialVersionUID = 99L;
	
	
	private JComboBox<String> memberEmailsBox= new JComboBox<>();
	private JComboBox<String> guideEmailsBox= new JComboBox<>();
	
	private JTable assignmentOverview= new JTable(new DefaultTableModel()){
		private static final long serialVersionUID=99L;
	};
	/*var assignment= new DefaultTableModel(0,0);
	assignment.setColumnIdentifiers(ASSIGNMENT_COLUMN_NAMES);*/
	  private JScrollPane overviewScrollPane = new JScrollPane(assignmentOverview);
	  //maybe remove status below
	  private static final String[] ASSIGNMENT_COLUMN_NAMES = {"memberEmail","memberName", "guideEmail","guideName", "hotelName", "startWeek","endWeek", "totalCostForGuide", "totalCostForEquipment","Status" };
	  private static final int HEIGHT_OVERVIEW_TABLE = 200;
	  
	  public ViewAssignmentFrame() {//maybe
		  initComponents();
	  }
	  private void initComponents() {
		  add(overviewScrollPane);
		    overviewScrollPane.setPreferredSize(new Dimension(assignmentOverview.getPreferredSize().width, HEIGHT_OVERVIEW_TABLE));
		    overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setTitle("ClimbSafe Application System");
			//fix
			List<String> memberEmails = AddtitionalController.getAssignmentMemberEmails();
			List<String> memberNames= AddtitionalController.getAssignmentMemberNames();
			List<String> guideEmails = AddtitionalController.getAssignmentGuideEmails();
			List<String> gudieNames= AddtitionalController.getAssignmentGuideNames();
			List<String> guideNames= AddtitionalController.getAssignmentGuideNames();
			List<String> hotelNames= AddtitionalController.getAssignmentHotelNames();
			List<Integer> startWeeks=AddtitionalController.getAssignmentStartWeeks();
			List<Integer> endWeeks=AddtitionalController.getAssignmentEndWeeks();
			List<Integer> totalGuideCosts=AddtitionalController.getAssignmentTotalGuideCosts();
			List<Integer> totalEquipmentCosts=AddtitionalController.getAssignmentTotalEquipmentCosts();
			List<String> statuses= AddtitionalController.getAssignmentStatuses();	
			
			
					
			
	  }
}
