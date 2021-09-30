/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.util.*;

// line 75 "../../../../../model.ump"
public class Equipment extends BookableEquipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private int weight;
  private int cost;

  //Equipment Associations
  private List<BundleItem> bundleItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(String aName, ClimbSafe aClimbSafe, int aWeight, int aCost)
  {
    super(aName, aClimbSafe);
    weight = aWeight;
    cost = aCost;
    bundleItems = new ArrayList<BundleItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeight(int aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setCost(int aCost)
  {
    boolean wasSet = false;
    cost = aCost;
    wasSet = true;
    return wasSet;
  }

  public int getWeight()
  {
    return weight;
  }

  public int getCost()
  {
    return cost;
  }
  /* Code from template association_GetMany */
  public BundleItem getBundleItem(int index)
  {
    BundleItem aBundleItem = bundleItems.get(index);
    return aBundleItem;
  }

  public List<BundleItem> getBundleItems()
  {
    List<BundleItem> newBundleItems = Collections.unmodifiableList(bundleItems);
    return newBundleItems;
  }

  public int numberOfBundleItems()
  {
    int number = bundleItems.size();
    return number;
  }

  public boolean hasBundleItems()
  {
    boolean has = bundleItems.size() > 0;
    return has;
  }

  public int indexOfBundleItem(BundleItem aBundleItem)
  {
    int index = bundleItems.indexOf(aBundleItem);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundleItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BundleItem addBundleItem(EquipmentBundle aBundle)
  {
    return new BundleItem(this, aBundle);
  }

  public boolean addBundleItem(BundleItem aBundleItem)
  {
    boolean wasAdded = false;
    if (bundleItems.contains(aBundleItem)) { return false; }
    Equipment existingEquipment = aBundleItem.getEquipment();
    boolean isNewEquipment = existingEquipment != null && !this.equals(existingEquipment);
    if (isNewEquipment)
    {
      aBundleItem.setEquipment(this);
    }
    else
    {
      bundleItems.add(aBundleItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBundleItem(BundleItem aBundleItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aBundleItem, as it must always have a equipment
    if (!this.equals(aBundleItem.getEquipment()))
    {
      bundleItems.remove(aBundleItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleItemAt(BundleItem aBundleItem, int index)
  {  
    boolean wasAdded = false;
    if(addBundleItem(aBundleItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundleItems()) { index = numberOfBundleItems() - 1; }
      bundleItems.remove(aBundleItem);
      bundleItems.add(index, aBundleItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleItemAt(BundleItem aBundleItem, int index)
  {
    boolean wasAdded = false;
    if(bundleItems.contains(aBundleItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundleItems()) { index = numberOfBundleItems() - 1; }
      bundleItems.remove(aBundleItem);
      bundleItems.add(index, aBundleItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleItemAt(aBundleItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=bundleItems.size(); i > 0; i--)
    {
      BundleItem aBundleItem = bundleItems.get(i - 1);
      aBundleItem.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "weight" + ":" + getWeight()+ "," +
            "cost" + ":" + getCost()+ "]";
  }
}