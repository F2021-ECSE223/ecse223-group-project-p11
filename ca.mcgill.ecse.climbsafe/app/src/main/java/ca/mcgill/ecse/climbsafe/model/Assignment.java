/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;
import ca.mcgill.ecse.climbsafe.model.Member.BanStatus;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

// line 107 "../../../../../ClimbSafePersistence.ump"
// line 7 "../../../../../ClimbSafeExtended.ump"
// line 1 "../../../../../ClimbSafeStates.ump"
// line 95 "../../../../../ClimbSafe.ump"
public class Assignment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private String authorizationCode;
  private int refundPercentage;
  private int totalEquipmentCost;
  private int totalGuideCost;
  private int startWeek;
  private int endWeek;

  //Assignment State Machines
  public enum AssignmentStatus { Assigned, Paid, Started, Cancelled, Finished }
  private AssignmentStatus assignmentStatus;

  //Assignment Associations
  private Member member;
  private Guide guide;
  private Hotel hotel;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(int aStartWeek, int aEndWeek, Member aMember, ClimbSafe aClimbSafe)
  {
    authorizationCode = null;
    refundPercentage = 0;
    totalEquipmentCost = 0;
    totalGuideCost = 0;
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create assignment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create assignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setAssignmentStatus(AssignmentStatus.Assigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAuthorizationCode(String aAuthorizationCode)
  {
    boolean wasSet = false;
    authorizationCode = aAuthorizationCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setRefundPercentage(int aRefundPercentage)
  {
    boolean wasSet = false;
    refundPercentage = aRefundPercentage;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalEquipmentCost(int aTotalEquipmentCost)
  {
    boolean wasSet = false;
    totalEquipmentCost = aTotalEquipmentCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalGuideCost(int aTotalGuideCost)
  {
    boolean wasSet = false;
    totalGuideCost = aTotalGuideCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public String getAuthorizationCode()
  {
    return authorizationCode;
  }

  public int getRefundPercentage()
  {
    return refundPercentage;
  }

  public int getTotalEquipmentCost()
  {
    return totalEquipmentCost;
  }

  public int getTotalGuideCost()
  {
    return totalGuideCost;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }

  public String getAssignmentStatusFullName()
  {
    String answer = assignmentStatus.toString();
    return answer;
  }

  public AssignmentStatus getAssignmentStatus()
  {
    return assignmentStatus;
  }

  public boolean payAssignment(String authorizationCode)
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Assigned:
        if (isBanned())
        {
        // line 4 "../../../../../ClimbSafeStates.ump"
          rejectBanPaid();
          setAssignmentStatus(AssignmentStatus.Assigned);
          wasEventProcessed = true;
          break;
        }
        if (!(isCorrectCode(getAuthorizationCode()))&&!(isBanned()))
        {
        // line 5 "../../../../../ClimbSafeStates.ump"
          rejectPayAssignment();
          setAssignmentStatus(AssignmentStatus.Assigned);
          wasEventProcessed = true;
          break;
        }
        if (isCorrectCode(getAuthorizationCode())&&!(isBanned()))
        {
        // line 6 "../../../../../ClimbSafeStates.ump"
          doPayAssignment(authorizationCode);
          setAssignmentStatus(AssignmentStatus.Paid);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        // line 21 "../../../../../ClimbSafeStates.ump"
        alreadyPaid();
        setAssignmentStatus(AssignmentStatus.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        // line 32 "../../../../../ClimbSafeStates.ump"
        alreadyPaid();
        setAssignmentStatus(AssignmentStatus.Started);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 37 "../../../../../ClimbSafeStates.ump"
        rejectPayCancelled();
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 43 "../../../../../ClimbSafeStates.ump"
        rejectPayFinished();
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancelAssignment()
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Assigned:
        if (isBanned())
        {
        // line 8 "../../../../../ClimbSafeStates.ump"
          rejectCancelBanned();
          setAssignmentStatus(AssignmentStatus.Assigned);
          wasEventProcessed = true;
          break;
        }
        if (!(isBanned()))
        {
          setAssignmentStatus(AssignmentStatus.Cancelled);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        // line 24 "../../../../../ClimbSafeStates.ump"
        refund(50);
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        // line 34 "../../../../../ClimbSafeStates.ump"
        refund(10);
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 45 "../../../../../ClimbSafeStates.ump"
        rejectCancelFinished(); refund(0);
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startAssignment()
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Assigned:
        if (isBanned())
        {
        // line 11 "../../../../../ClimbSafeStates.ump"
          rejectStartBanned();
          setAssignmentStatus(AssignmentStatus.Assigned);
          wasEventProcessed = true;
          break;
        }
        if (!(isBanned()))
        {
        // line 12 "../../../../../ClimbSafeStates.ump"
          banMember();
          setAssignmentStatus(AssignmentStatus.Assigned);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        setAssignmentStatus(AssignmentStatus.Started);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 39 "../../../../../ClimbSafeStates.ump"
        rejectStartCancelled();
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      case Finished:
        // line 44 "../../../../../ClimbSafeStates.ump"
        rejectStartFinished();
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finishAssignment()
  {
    boolean wasEventProcessed = false;
    
    AssignmentStatus aAssignmentStatus = assignmentStatus;
    switch (aAssignmentStatus)
    {
      case Assigned:
        if (isBanned())
        {
        // line 14 "../../../../../ClimbSafeStates.ump"
          rejectFinishBanned();
          setAssignmentStatus(AssignmentStatus.Assigned);
          wasEventProcessed = true;
          break;
        }
        if (!(isBanned()))
        {
        // line 15 "../../../../../ClimbSafeStates.ump"
          rejectFinished();
          setAssignmentStatus(AssignmentStatus.Assigned);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        // line 26 "../../../../../ClimbSafeStates.ump"
        rejectFinished();
        setAssignmentStatus(AssignmentStatus.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        setAssignmentStatus(AssignmentStatus.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        // line 38 "../../../../../ClimbSafeStates.ump"
        rejectFinishCancelled();
        setAssignmentStatus(AssignmentStatus.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setAssignmentStatus(AssignmentStatus aAssignmentStatus)
  {
    assignmentStatus = aAssignmentStatus;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }

  public boolean hasGuide()
  {
    boolean has = guide != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      //Unable to setMember to null, as assignment must always be associated to a member
      return wasSet;
    }
    
    Assignment existingAssignment = aNewMember.getAssignment();
    if (existingAssignment != null && !equals(existingAssignment))
    {
      //Unable to setMember, the current member already has a assignment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setAssignment(this);

    if (anOldMember != null)
    {
      anOldMember.setAssignment(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    Guide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeAssignment(this);
    }
    if (aGuide != null)
    {
      aGuide.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeAssignment(this);
    }
    if (aHotel != null)
    {
      aHotel.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      existingClimbSafe.removeAssignment(this);
    }
    climbSafe.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setAssignment(null);
    }
    if (guide != null)
    {
      Guide placeholderGuide = guide;
      this.guide = null;
      placeholderGuide.removeAssignment(this);
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeAssignment(this);
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeAssignment(this);
    }
  }

  // line 15 "../../../../../ClimbSafeExtended.ump"
   public void setAssignmentState(AssignmentStatus status){
    setAssignmentStatus(status);
  }

  // line 19 "../../../../../ClimbSafeExtended.ump"
   public void assignmentEquipmentCost(){
    ClimbSafe cs= ClimbSafeApplication.getClimbSafe();
        int sum=0;
        
        for (BookedItem item : this.member.getBookedItems()) {
				// if it is a bundle, compute bundle cost
				if (item.getItem() instanceof EquipmentBundle) {
					double bundleCost = 0;
					EquipmentBundle equipmentBundle = (EquipmentBundle) item.getItem();
					for (BundleItem bI : equipmentBundle.getBundleItems()) {
						Equipment e = bI.getEquipment();
						bundleCost += e.getPricePerWeek() * bI.getQuantity();
					}
					if (this.member.getGuideRequired()) {
						bundleCost = bundleCost * (100.0 - equipmentBundle.getDiscount()) /100.0;
					}
					sum += bundleCost * item.getQuantity();
				} else if (item.getItem() instanceof Equipment) {
					Equipment e = (Equipment) item.getItem();
					sum += e.getPricePerWeek() * item.getQuantity();
				}
                 
        }


        sum *= this.getMember().getNrWeeks();//sum is weekly sum until multiplied by number of weeks
        this.setTotalEquipmentCost(sum);
  }

  // line 49 "../../../../../ClimbSafeExtended.ump"
   public void assignmentGuideCost(){
    ClimbSafe cs= ClimbSafeApplication.getClimbSafe();
      int sum = 0;
      if (this.getMember().getGuideRequired()){
          sum += cs.getPriceOfGuidePerWeek() * this.getMember().getNrWeeks();
          this.setTotalGuideCost(sum);
      }
  }

  // line 58 "../../../../../ClimbSafeExtended.ump"
   public void assignmentCost(){
    assignmentGuideCost();
      assignmentEquipmentCost();
  }

  // line 51 "../../../../../ClimbSafeStates.ump"
   private Boolean isCorrectCode(String authorizationCode){
    return !authorizationCode.equals("");
  }

  // line 55 "../../../../../ClimbSafeStates.ump"
   private void rejectPayAssignment(){
    throw new RuntimeException("Invalid authorization code");
  }

  // line 59 "../../../../../ClimbSafeStates.ump"
   private void alreadyPaid(){
    throw new RuntimeException("Trip has already been paid for");
  }

  // line 62 "../../../../../ClimbSafeStates.ump"
   private void rejectBanPaid(){
    throw new RuntimeException("Cannot pay for the trip due to a ban");
  }

  // line 66 "../../../../../ClimbSafeStates.ump"
   private void rejectCancelBanned(){
    throw new RuntimeException("Cannot cancel the trip due to a ban");
  }

  // line 70 "../../../../../ClimbSafeStates.ump"
   private void rejectStartBanned(){
    throw new RuntimeException("Cannot start the trip due to a ban");
  }

  // line 73 "../../../../../ClimbSafeStates.ump"
   private void rejectFinishBanned(){
    throw new RuntimeException("Cannot finish the trip due to a ban");
  }

  // line 77 "../../../../../ClimbSafeStates.ump"
   private void rejectFinished(){
    throw new RuntimeException("Cannot finish a trip which has not started");
  }

  // line 81 "../../../../../ClimbSafeStates.ump"
   private void rejectPayCancelled(){
    throw new RuntimeException("Cannot pay for a trip which has been cancelled");
  }

  // line 85 "../../../../../ClimbSafeStates.ump"
   private void rejectStartCancelled(){
    throw new RuntimeException("Cannot start a trip which has been cancelled");
  }

  // line 89 "../../../../../ClimbSafeStates.ump"
   private void rejectFinishCancelled(){
    throw new RuntimeException("Cannot finish a trip which has been cancelled");
  }

  // line 93 "../../../../../ClimbSafeStates.ump"
   private void rejectPayFinished(){
    throw new RuntimeException("Cannot pay for a trip which has finished");
  }

  // line 97 "../../../../../ClimbSafeStates.ump"
   private void rejectStartFinished(){
    throw new RuntimeException("Cannot start a trip which has finished");
  }

  // line 101 "../../../../../ClimbSafeStates.ump"
   private void rejectCancelFinished(){
    throw new RuntimeException("Cannot cancel a trip which has finished");
  }

  // line 105 "../../../../../ClimbSafeStates.ump"
   private void doPayAssignment(String authorizationCode){
    this.authorizationCode = authorizationCode;
  }

  // line 110 "../../../../../ClimbSafeStates.ump"
   private void banMember(){
    this.member.setBanStatus(BanStatus.Banned);
  }

  // line 113 "../../../../../ClimbSafeStates.ump"
   private boolean isBanned(){
    return(this.member.getBanStatus() == BanStatus.Banned);
  }

  // line 117 "../../../../../ClimbSafeStates.ump"
   private void refund(int percentage){
    this.refundPercentage=percentage;
  }

  // line 121 "../../../../../ClimbSafeStates.ump"
   private void rejectStart(){
    throw new RuntimeException("The start was unsuccesful");
  }

  // line 125 "../../../../../ClimbSafeStates.ump"
   private void rejectCancel(){
    throw new RuntimeException("The assignment was already cancelled");
  }


  public String toString()
  {
    return super.toString() + "["+
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "refundPercentage" + ":" + getRefundPercentage()+ "," +
            "totalEquipmentCost" + ":" + getTotalEquipmentCost()+ "," +
            "totalGuideCost" + ":" + getTotalGuideCost()+ "," +
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 110 "../../../../../ClimbSafePersistence.ump"
  private static final long serialVersionUID = 11L ;

  
}