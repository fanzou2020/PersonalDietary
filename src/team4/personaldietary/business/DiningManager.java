package team4.personaldietary.business;

import team4.personaldietary.bean.Dining;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The <tt>DiningManager</tt> class provides methods to interact with
 * dining instances
 *
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 * @version 28/02/2020
 */
public class DiningManager  {
    private ArrayList<Dining> diningArrayList; // consider this as the data model.

    /**
     * Constructor
     */
    public DiningManager(){
        this.diningArrayList = new ArrayList<>();
    }

    /**
     * Add Dining Item
     * @param diningCollection
     * @param diningItem
     * @return
     */
    public boolean addDiningItem(Collection<Dining> diningCollection, Dining diningItem) {
        diningCollection.add(diningItem);  // control the GUI
        this.diningArrayList.add(diningItem);   // control the data model.
        return true;
    }

    /**
     * Remove Dining Item
     * @param diningCollection
     * @param diningItem
     * @return
     */
    public boolean removeDiningItem(Collection<Dining> diningCollection, Dining diningItem) {
        diningCollection.remove(diningItem);  // control the GUI
        this.diningArrayList.remove(diningItem);   // control the data model
        return true;
    }
}
