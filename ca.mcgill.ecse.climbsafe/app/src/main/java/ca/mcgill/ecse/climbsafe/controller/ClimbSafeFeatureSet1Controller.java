package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;


public class ClimbSafeFeatureSet1Controller {

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
    String error  ="";
//    Calendar cal = Calendar.getInstance();
//    cal.setLenient(false);
//    cal.setTime(startDate);
//    try {
//        cal.getTime();
//    }
//    catch (Exception e) {
//      error = "Invalid Date";
//    }
    if (nrWeeks<0 || nrWeeks==0) {
      error = "The number of climbing weeks must be greater than or equal to zero";
    }
    if (priceOfGuidePerWeek<0 || priceOfGuidePerWeek==0) {
      error = "The price of guide per week must be greater than or equal to zero";
    }
    if (!error.isEmpty()) {
      throw new InvalidInputException(error);}
//    ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
//    try {
//      climbSafe.setStartDate(startDate);
//  } catch (RuntimeException e) {
//      error = e.getMessage();
//      throw new InvalidInputException(error);}
//    try {
//      climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
//    }catch(RuntimeException e) {
//      error = e.getMessage();
//      throw new InvalidInputException
     
    
  }

    


 
    //Date datee = Date.valueOf(("startDate"));
    
//    String newdate = startDate.toString();
//    char[] ch = new char[newdate.length()];
//    
//    
//    for (int i = 0; i < newdate.length(); i++) {
//        ch[i] = newdate.charAt(i);}
//    int x = Character.getNumericValue(ch[0]);
//    int z = Character.getNumericValue(ch[1]);
//    int r = Character.getNumericValue(ch[2]);
//    int y = Character.getNumericValue(ch[3]);
//    int u = Character.getNumericValue(ch[5]);
//    int p = Character.getNumericValue(ch[6]);
//    int k = Character.getNumericValue(ch[8]);
//    int f = Character.getNumericValue(ch[9]);
//    if (u>1) {
//      error = "Invalid Date";}
//    if (p>2 && u==1) {
//      error = "Invalid Date";}
//    if (k>3){
//      error = "Invalid Date";}
//    if (k==3 && f>1){
//      error = "Invalid Date";}
//    if ((u==0 && p==2 && k>2)||(u==0 && p==2 & k==2 & f>8)){
//      error = "Invalid Date";}
//    if ((u==0 && p==4 && k==3 && f>0)){
//      error = "Invalid Date";}
//    if ((u==0 && p==6 && k==3 && f>0)){
//      error = "Invalid Date";}
//    if ((u==0 && p==9 && k==3 && f>0)){
//      error = "Invalid Date";}
//    if ((u==1 && p==1 && k==3 && f>0)){
//      error = "Invalid Date";}
//    if ((u==0 && p==0)){
//      error = "Invalid Date";}


   
    
    
    
    //if (.isValid()==false) {
      //error = "Invalid date";
      
    //}
  
  //.isValid()
    //String strdate = startDate.toString();
    //if (strdate.getNumericalValueOf(strdate.charAt(6))>1) {
  //}

  public static void deleteMember(String email) throws InvalidInputException {
    try {
      Member memberToDelete = (Member) Member.getWithEmail(email);
      memberToDelete.delete();}
    catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }}
    
    
   
 
  

  public static void deleteGuide(String email) throws InvalidInputException {
    try {
    Guide guideToDelete = (Guide) Guide.getWithEmail(email);
    guideToDelete.delete();}
    catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }}
  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}

}
