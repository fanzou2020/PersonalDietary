package team4.personaldietary.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import team4.personaldietary.GUI.DiningTableRow;
import team4.personaldietary.bean.FoodGroup;
import team4.personaldietary.bean.Indining;
import team4.personaldietary.bean.Serving;


import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DiningManagerImpTest {

    @Test
    void addDiningItem() {
        ObservableList<DiningTableRow> observableCollection = FXCollections.observableArrayList();
        ObservableList<String> currServingList = FXCollections.observableArrayList();
        ObservableList<String> consumedServingList = FXCollections.observableArrayList();

        DiningManagerImp diningManagerImp = new DiningManagerImp(observableCollection, currServingList, consumedServingList);
        LocalDateTime time = LocalDateTime.now();
        FoodGroup group = FoodGroup.grain_products;
        Serving serving = new Serving("a", 1.0, 1.0, 1.0, 1.0);
        Indining item = new Indining("name1", time, group, serving, "meal1", "type1");
        diningManagerImp.addDiningItemDataModel(item);
        assertEquals(diningManagerImp.getDiningArrayList().size(), 1, "test add");
    }
}