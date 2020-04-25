package team4.personaldietary.business;

import javafx.collections.ObservableList;
import team4.personaldietary.GUI.DiningTableRow;
import team4.personaldietary.bean.Dining;
import team4.personaldietary.facade.FacadeDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DiningManager implements DiningManagerInterface{

    private ArrayList<DiningTableRow> diningTableRowArrayList; // this contains all the TableRow items
    private ObservableList<DiningTableRow> observableCollection; // this is the items shown on the screen
    private ObservableList<String> currServingList, consumedServingList; // ListView for serving
    private FacadeDAO facadeDAO;

    public DiningManager(Collection<DiningTableRow> observableCollection,
                         Collection<String> currServingList, Collection<String> consumedServingList) {
        this.observableCollection = (ObservableList<DiningTableRow>) observableCollection;
        this.currServingList = (ObservableList<String>) currServingList;
        this.consumedServingList = (ObservableList<String>) consumedServingList;
        this.diningTableRowArrayList = new ArrayList<>();
        facadeDAO = new FacadeDAO();
    }

    public Collection<DiningTableRow> getObservableCollection() {
        return observableCollection;
    }

    public ObservableList<String> getCurrServingList() {
        return currServingList;
    }

    public ObservableList<String> getConsumedServingList() {
        return consumedServingList;
    }

    public List<Dining> loadInitial() {
        try {
            List<Dining> diningList = facadeDAO.findAllDining();
            for (Dining d : diningList) {
                DiningTableRow dtr = new DiningTableRow(d, this);
                observableCollection.add(dtr);
                diningTableRowArrayList.add(dtr);
            }
            return diningList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean addDiningItem(Dining diningItem) {
        try {
            int addResult = facadeDAO.createDining(diningItem);  // add item to database

            // if item is added to database successfully
            if (addResult == 1) {
                 DiningTableRow dtr = new DiningTableRow(diningItem, this);
                 observableCollection.add(dtr);
                 diningTableRowArrayList.add(dtr);
                 return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeDiningItem(Dining diningItem) {
        try {
            // remove item from database
            int removeResult = facadeDAO.deleteDining(diningItem.getDiningId());

            // if remove from database successfully, remove it from GUI
            if (removeResult == 1) {
                observableCollection.removeIf(d -> d.getDiningItem().equals(diningItem));
                diningTableRowArrayList.removeIf(d -> d.getDiningItem().equals(diningItem));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean markConsumed(Dining diningItem) {
        try {
            // mark consumed in database
            diningItem.setConsumed(true);
            int updateResult = facadeDAO.updateDining(diningItem);

            // mark consumed in DiningTableRowArrayList
            if (updateResult == 1) {
                for (DiningTableRow d : diningTableRowArrayList) {
                    if (d.getDiningItem().equals(diningItem)) d.setConsumed(true);
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean markUnConsumed(Dining diningItem) {
        try {
            // mark unConsumed in database
            diningItem.setConsumed(false);
            int updateResult = facadeDAO.updateDining(diningItem);

            // mark consumed in DiningTableRowArrayList
            if (updateResult == 1) {
                for (DiningTableRow d : diningTableRowArrayList) {
                    if (d.getDiningItem().equals(diningItem)) d.setConsumed(false);
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hideConsumed() {
        //if item in observableCollection is consumed, remove it .
        observableCollection.removeIf(DiningTableRow::isConsumed);
        return true;
    }

    public boolean unHideConsumed() {
        for (DiningTableRow item : diningTableRowArrayList) {
            if (!observableCollection.contains(item))
                observableCollection.add(item);
        }
        return true;
    }

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
