package team4.personaldietary.business;

import team4.personaldietary.GUI.TableItem;
import team4.personaldietary.bean.Dining;

import java.util.ArrayList;
import java.util.Collection;

public class DiningDAOImpl {
    private ArrayList<Dining> diningArrayList; // consider this as the data model.
    private Collection<TableItem> observableCollection; // this is the items shown on the screen
    private ArrayList<TableItem> tableItemArrayList; // this contains all the tableItems related to database

    public DiningDAOImpl(Collection<TableItem> observableCollection) {
        this.observableCollection = observableCollection;
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
}
