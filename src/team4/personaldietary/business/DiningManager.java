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
    private Collection<Dining> diningCollection;

    /**
     * Constructor
     */
    public DiningManager(Collection<Dining> diningCollection){
        this.diningArrayList = new ArrayList<>();
        this.diningCollection = diningCollection;
    }

    /**
     * Add Dining Item
     * @param diningItem
     * @return
     */
    public boolean addDiningItem(Dining diningItem) {
        diningCollection.add(diningItem);  // control the GUI
        diningArrayList.add(diningItem);   // control the data model.
        return true;
    }

    /**
     * Remove Dining Item
     * @param diningItem
     * @return
     */
    public boolean removeDiningItem(Dining diningItem) {
        diningCollection.remove(diningItem);  // control the GUI
        diningArrayList.remove(diningItem);   // control the data model
        return true;
    }
}
