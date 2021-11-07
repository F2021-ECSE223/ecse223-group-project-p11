package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

public class AssignmentController {
	
	public void initiateAssignment() {
		
		ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
		
		
		
		for(Guide guide : climbSafe.getGuides()) {
			int currentWeek = 1;
			for( Member member: climbSafe.getMembers()) {
				
				if(!member.hasAssignment() && member.getNrWeeks() <= climbSafe.getNrWeeks()) {
					if(!member.isGuideRequired()) {
						climbSafe.addAssignment(1, member.getNrWeeks(), member);
						
					}
					else {
						
						if(!guide.hasAssignments()) {
							
							Assignment newAssignment = climbSafe.addAssignment(1, member.getNrWeeks(), member);
							newAssignment.setGuide(guide);
							currentWeek = newAssignment.getEndWeek();
						}
						
							
						else if(currentWeek + member.getNrWeeks() <= climbSafe.getNrWeeks()){
							Assignment newAssignment = climbSafe.addAssignment(currentWeek + 1,currentWeek +  member.getNrWeeks(), member);
							newAssignment.setGuide(guide);
							currentWeek = newAssignment.getEndWeek();
						}
					}
				}
			}
		}
		if(climbSafe.getAssignments().size() < climbSafe.getMembers().size()) {
			//throw Error("Assignments could not be completed for all members");
		}
		
		
		/*
		for (Member member: climbSafe.getMembers()) {
		  	if (member.isGuideRequired()) {
		      	for (Guide guide : climbSafe.getGuides()) {
		      	Assignment lastAssignment;
		      	int lastweekassigned = 0;
		      		for (Assignment as: guide.getAssignments()) {
		      			if (lastweekassigned < as.getEndWeek()) {
		      				lastweekassigned = as.getEndWeek();
		      				lastAssignment = as;
		      			}
		      		}
                }
		      	if (lastAssignment.getEndWeek() + 1 + member.getNrWeeks() < climbSafe.getNrWeeks()) {
		      		// assign guide member
		      	}
		    } else {
		    	climbSafe.setStartDate(1);
		    	.setEndWeek(1 + member.getNrWeeks());
		   }
     	}
		
		
		
		*/
		
		
		
	}
	
	
	
	
	public void processAssignment() {
		
		
		
		
		
	}

}
