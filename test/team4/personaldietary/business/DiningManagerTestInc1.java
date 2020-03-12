package team4.personaldietary.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import team4.personaldietary.GUI.DiningTableRow;
import team4.personaldietary.bean.Dining;
import team4.personaldietary.bean.Indining;
import team4.personaldietary.bean.Outdining;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
class DiningManagerTestInc1 {

    ArrayList<Dining> expected = new ArrayList<>();
    private ObservableList<String> currServingList;
    private ObservableList<String> consumedServingList;

    private ObservableList<DiningTableRow> diningList;

    private DiningManager diningManager;


    Dining diningItem1;
    Dining diningItem2;
    Dining diningItem3;

    @BeforeEach
    void setUp() {
        diningItem1 = new Indining();
        diningItem2 = new Outdining();
        diningItem3 = new Indining();
        diningList = FXCollections.observableArrayList();
        consumedServingList = FXCollections.observableArrayList();
        currServingList = FXCollections.observableArrayList();
        diningManager = new DiningManager(diningList, currServingList, consumedServingList);
    }

    @org.junit.jupiter.api.Test
    void addDiningItem() {

        diningManager.addDiningItem(diningItem1);
        diningManager.addDiningItem(diningItem2);
        diningManager.addDiningItem(diningItem3);

        assertEquals(3, diningManager.getDiningArrayList().size());
    }

    @org.junit.jupiter.api.Test
    void removeDiningItem() {

        diningManager.addDiningItem(diningItem1);
        diningManager.removeDiningItem(diningItem1);

        assertEquals(0, diningManager.getDiningArrayList().size());
    }
}