package team4.personaldietary.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import team4.personaldietary.GUI.DiningTableRow;
import team4.personaldietary.bean.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

class DiningManagerTest {

    private ArrayList<Dining> diningArrayList; // consider this as the data model.
    private ArrayList<DiningTableRow> diningTableRowArrayList; // this contains all the TableRow items
    private Collection<DiningTableRow> observableCollection; // this is the items shown on the screen
    private ObservableList<String> currServingList, consumedServingList; // ListView for serving

    private ArrayList<Dining> testDiningArrayList = new ArrayList<Dining>();
    private ArrayList<DiningTableRow> testDiningTableRowArrayList = new ArrayList<DiningTableRow>();
    private Collection<DiningTableRow> testObservableCollection = FXCollections.observableArrayList();
    private ObservableList<String> testCurrServingList = FXCollections.observableArrayList(), testConsumedServingList = FXCollections.observableArrayList();

    @Test
    void DiningManager() {
    }

    @Test
    void getDiningArrayList() {

        DiningManager manager = new DiningManager(testObservableCollection, testCurrServingList, testConsumedServingList);
        assertEquals(manager.getDiningArrayList().size(), 0, "testing getDinaryArrayList()");
    }

    @Test
    void addDiningItem() {

        DiningManager manager = new DiningManager(testObservableCollection, testCurrServingList, testConsumedServingList);

        LocalDateTime time = LocalDateTime.now();
        FoodGroup group = FoodGroup.grain_products;
        Serving serving = new Serving("a", 1.0, 1.0, 1.0, 1.0);
        Dining a = new Indining("1", time, group, serving, "1", "1");
        Dining b = new Outdining("1", time, group, serving, "1", "1");

        manager.addDiningItem(null);
        manager.addDiningItem(a);

        assertEquals(manager.getDiningArrayList().get(0), a, "testing addDiningItem()");
        assertEquals(manager.getDiningArrayList().get(1), b, "testing addDiningItem()");
    }

    @Test
    void removeDiningItem() {

        LocalDateTime time = LocalDateTime.now();
        FoodGroup group = FoodGroup.grain_products;
        Serving serving = new Serving("a", 1.0, 1.0, 1.0, 1.0);
        Dining a = new Indining("1", time, group, serving, "1", "1");
        Dining b = new Outdining("1", time, group, serving, "1", "1");

        testDiningArrayList.add(a);
        testDiningArrayList.add(b);

        //.re

    }

    @Test
    void markConsumed() {
    }

    @Test
    void markUnConsumed() {
    }

    @Test
    void hideConsumed() {
    }

    @Test
    void unHideConsumed() {
    }

    @Test
    void updateCurrServing() {
    }

    @Test
    void updateConsumedServing() {
    }
}