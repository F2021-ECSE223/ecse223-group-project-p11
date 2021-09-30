/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.util.*;

// line 90 "../../../../../model.ump"
public class EquipmentBundle extends BookableEquipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EquipmentBundle Attributes
  private int bundlePercentageDiscount;

  //EquipmentBundle Associations
  private List<BundleItem> items;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EquipmentBundle(String aName, ClimbSafe aClimbSafe, int aBundlePercentageDiscount)
  {
    super(aName, aClimbSafe);
    bundlePercentageDiscount = aBundlePercentageDiscount;
    items = new ArrayList<BundleItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBundlePercentageDiscount(int aBundlePercentageDiscount)
  {
    boolean wasSet = false;
    bundlePercentageDiscount = aBundlePercentageDiscount;
    wasSet = true;
    return wasSet;
  }

  public int getBundlePercentageDiscount()
  {
    return bundlePercentageDiscount;
  }
  /* Code from template association_GetMany */
  public BundleItem getItem(int index)
  {
    BundleItem aItem = items.get(index);
    return aItem;
  }

  public List<BundleItem> getItems()
  {
    List<BundleItem> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(BundleItem aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfItemsValid()
  {
    boolean isValid = numberOfItems() >= minimumNumberOfItems();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 2;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public BundleItem addItem(Equipment aEquipment)
  {
    BundleItem aNewItem = new BundleItem(aEquipment, this);
    return aNewItem;
  }

  public boolean addItem(BundleItem aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    EquipmentBundle existingBundle = aItem.getBundle();
    boolean isNewBundle = existingBundle != null && !this.equals(existingBundle);

    if (isNewBundle && existingBundle.numberOfItems() <= minimumNumberOfItems())
    {
      return wasAdded;
    }
    if (isNewBundle)
    {
      aItem.setBundle(this);
    }
    else
    {
      items.add(aItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(BundleItem aItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aItem, as it must always have a bundle
    if (this.equals(aItem.getBundle()))
    {
      return wasRemoved;
    }

    //bundle already at minimum (2)
    if (numberOfItems() <= minimumNumberOfItems())
    {
      return wasRemoved;
    }

    items.remove(aItem);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(BundleItem aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(BundleItem aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (items.size() > 0)
    {
      BundleItem aItem = items.get(items.size() - 1);
      aItem.delete();
      items.remove(aItem);
    }
    
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "bundlePercentageDiscount" + ":" + getBundlePercentageDiscount()+ "]";
  }
}