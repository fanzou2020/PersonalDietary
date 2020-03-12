package team4.personaldietary.business;

import javafx.collections.ObservableList;
import team4.personaldietary.GUI.DiningTableRow;
import team4.personaldietary.bean.Dining;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The <tt>DiningManager</tt> class
 *
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 * @version 11/3/2020
 */
public class DiningManager {

    private ArrayList<Dining> diningArrayList; // consider this as the data model.

    private ArrayList<DiningTableRow> diningTableRowArrayList; // this contains all the TableRow items
    private Collection<DiningTableRow> observableCollection; // this is the items shown on the screen
    private ObservableList<String> currServingList, consumedServingList; // ListView for serving

    /**
     * @param observableCollection
     * @param currServingList
     * @param consumedServingList
     */
    public DiningManager(Collection<DiningTableRow> observableCollection,
                         Collection<String> currServingList, Collection<String> consumedServingList) {
        this.observableCollection = observableCollection;
        this.currServingList = (ObservableList<String>) currServingList;
        this.consumedServingList = (ObservableList<String>) consumedServingList;
        this.diningArrayList = new ArrayList<>();
        this.diningTableRowArrayList = new ArrayList<>();
    }

    /**
     * @return list of dining
     */
    public ArrayList<Dining> getDiningArrayList() {
        return diningArrayList;
    }

    /**
     * Add Dining Item
     * @param diningItem
     * @return
     */
    public boolean addDiningItem(Dining diningItem) {
        DiningTableRow newItem = new DiningTableRow(diningItem, this);
        observableCollection.add(newItem);     // control the GUI
        diningTableRowArrayList.add(newItem);
        diningArrayList.add(diningItem);   // control the data model.
        System.out.println(diningArrayList);
        return true;
    }
    /**
     * Remove Dining Item
     * @param diningItem
     * @return
     */
    public boolean removeDiningItem(Dining diningItem) {
        if (!observableCollection.isEmpty())
            observableCollection.removeIf(d -> d.getDiningItem().equals(diningItem));
        if (!diningTableRowArrayList.isEmpty())
            diningTableRowArrayList.removeIf(d -> d.getDiningItem().equals(diningItem));
        if (!diningArrayList.isEmpty())
            diningArrayList.remove(diningItem);   // control the data model
        System.out.println(diningArrayList);
        return true;
    }

    /**
     * mark consumed
     * @param diningItem
     * @return
     */
    public boolean markConsumed(Dining diningItem) {
        // mark consumed in arraylist, in data model.
        int indexOfItem = diningArrayList.indexOf(diningItem);
        diningArrayList.get(indexOfItem).setConsumed(true);
        // mark consumed in DiningTableRowArrayList
        for (DiningTableRow d : diningTableRowArrayList)
            if (d.getDiningItem().equals(diningItem)) d.setConsumed(true);
        System.out.println(diningArrayList);
        return true;
    }

    /**
     * mark unconsumed
     * @param diningItem
     * @return
     */
    public boolean markUnConsumed(Dining diningItem) {
        // mark unconsumed in arraylist, in data model.
        int indexOfItem = diningArrayList.indexOf(diningItem);
        diningArrayList.get(indexOfItem).setConsumed(false);
        // mark unconsumed in DiningTableRowArrayList
        for (DiningTableRow d : diningTableRowArrayList)
            if (d.getDiningItem().equals(diningItem)) d.setConsumed(false);
        System.out.println(diningArrayList);
        return true;
    }

    /**
     * hide consumed
     * @return
     */
    public boolean hideConsumed() {
        //if item in observableCollection is consumed, remove it .
        observableCollection.removeIf(DiningTableRow::isConsumed);
        return true;
    }

    /**
     * show consumed
     * @return
     */
    public boolean unHideConsumed() {
        for (DiningTableRow item : diningTableRowArrayList) {
            if (!observableCollection.contains(item))
                observableCollection.add(item);
        }
        return true;
    }

    /**
     * update current serving
     * @return
     */
    public boolean updateCurrServing() {
        double totalCalories = 0, totalFat = 0, totalSodium = 0, totalSugar = 0;
        for (DiningTableRow item : observableCollection) {
            totalCalories += item.getCalories();
            totalFat += item.getFat();
            totalSodium += item.getSodium();
            totalSugar += item.getSugar();
        }
        currServingList.set(1, "Calories :    " + totalCalories);
        currServingList.set(2, "Fat :              " + totalFat);
        currServingList.set(3, "Sodium :      " + totalSodium);
        currServingList.set(4, "Sugar :         " + totalSugar);
        return true;
    }

    /**
     * update consumed serving
     * @return
     */
    public boolean updateConsumedServing() {
        double totalCalories = 0, totalFat = 0, totalSodium = 0, totalSugar = 0;
        for (DiningTableRow item : diningTableRowArrayList) {
            if (item.isConsumed()) {
                totalCalories += item.getCalories();
                totalFat += item.getFat();
                totalSodium += item.getSodium();
                totalSugar += item.getSugar();
            }
        }
        consumedServingList.set(1, "Calories :    " + totalCalories);
        consumedServingList.set(2, "Fat :              " + totalFat);
        consumedServingList.set(3, "Sodium :      " + totalSodium);
        consumedServingList.set(4, "Sugar :         " + totalSugar);
        return true;
    }
}
