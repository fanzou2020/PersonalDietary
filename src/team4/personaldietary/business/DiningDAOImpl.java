package team4.personaldietary.business;

import javafx.collections.ObservableList;
import team4.personaldietary.GUI.TableItem;
import team4.personaldietary.bean.Dining;
import team4.personaldietary.bean.Serving;

import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.Collection;

public class DiningDAOImpl implements DiningDAO {
    private ArrayList<Dining> diningArrayList; // consider this as the data model.
    private Collection<TableItem> observableCollection; // this is the items shown on the screen
    private ArrayList<TableItem> tableItemArrayList; // this contains all the tableItems related to database

    private ObservableList<String> currServingList, consumedServingList;

    public DiningDAOImpl(Collection<TableItem> observableCollection,
                         Collection<String> currServingList, Collection<String> consumedServingList) {
        this.observableCollection = observableCollection;
        this.currServingList = (ObservableList<String>) currServingList;
        this.consumedServingList = (ObservableList<String>) consumedServingList;
        this.diningArrayList = new ArrayList<>();
        this.tableItemArrayList = new ArrayList<>();
    }

//    public DiningDAOImpl(ArrayList<Dining> diningArrayList) {
//        this.diningArrayList = diningArrayList;
//    }


    public boolean addDiningItem(Dining diningItem) {
        TableItem newItem = new TableItem(diningItem, this);
        observableCollection.add(newItem);     // control the GUI
        tableItemArrayList.add(newItem);
        diningArrayList.add(diningItem);   // control the data model.
        System.out.println(diningArrayList);
        return true;
    }

    public boolean removeDiningItem(TableItem tableItem) {
        if (!observableCollection.isEmpty()) observableCollection.remove(tableItem);  // control the GUI
        if (!tableItemArrayList.isEmpty()) tableItemArrayList.remove(tableItem);
        if (!diningArrayList.isEmpty()) diningArrayList.remove(tableItem.getDiningItem());   // control the data model
        System.out.println(diningArrayList);
        return true;
    }

    public boolean markConsumed(TableItem tableItem) {
        // mark consumed in tableItem, that is in the GUI part
        tableItem.setConsumed(true);
        // mark consumed in arraylist, in data model.
        int indexOfItem = diningArrayList.indexOf(tableItem.getDiningItem());
        diningArrayList.get(indexOfItem).setConsumed(true);
        System.out.println(diningArrayList);
        return true;
    }

    public boolean markUnConsumed(TableItem tableItem) {
        // mark unconsumed in tableItem, that is in the GUI part
        tableItem.setConsumed(false);
        // mark unconsumed in arraylist, in data model.
        int indexOfItem = diningArrayList.indexOf(tableItem.getDiningItem());
        diningArrayList.get(indexOfItem).setConsumed(false);
        System.out.println(diningArrayList);
        return true;
    }

    public boolean hideConsumed() {
        //if item in observableCollection is consumed, remove it .
        observableCollection.removeIf(TableItem::isConsumed);
        return true;
    }

    public boolean unHideConsumed() {
        for (TableItem item : tableItemArrayList) {
            if (!observableCollection.contains(item))
                observableCollection.add(item);
        }
        return true;
    }


    public boolean updateCurrServing() {
        double totalCalories = 0, totalFat = 0, totalSodium = 0, totalSugar = 0;
        for (TableItem item : observableCollection) {
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

    public boolean updateConsumedServing() {
        double totalCalories = 0, totalFat = 0, totalSodium = 0, totalSugar = 0;
        for (TableItem item : tableItemArrayList) {
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
