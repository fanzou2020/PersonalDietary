package team4.personaldietary.business;

import team4.personaldietary.bean.Dining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DiningManagerTest {

    ArrayList<Dining> expected = new ArrayList<>();
    Collection<team4.personaldietary.bean.Dining> diningCollection = new ArrayList<Dining>();
    private DiningManager manager = new DiningManager(diningCollection);


    Dining diningItem1;
    Dining diningItem2;
    Dining diningItem3;

    @org.junit.jupiter.api.Test
    void addDiningItem() {

        manager.addDiningItem(diningItem1);
        manager.addDiningItem(diningItem2);
        manager.addDiningItem(diningItem3);

        assertEquals(3, manager.getDiningArrayList().size());
    }

    @org.junit.jupiter.api.Test
    void removeDiningItem() {
        manager.addDiningItem(diningItem1);
        manager.removeDiningItem(diningItem1);

        assertEquals(0, manager.getDiningArrayList().size());
    }
}