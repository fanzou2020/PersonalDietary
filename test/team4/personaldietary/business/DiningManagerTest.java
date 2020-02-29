package team4.personaldietary.business;

import team4.personaldietary.bean.Dining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DiningManagerTest {

    private DiningManager manager = new DiningManager()
    Collection<team4.personaldietary.bean.Dining> diningCollection = new ArrayList<Dining>();
    ArrayList<Dining> diningArrayList = new ArrayList<>();
    Dining diningItem1;
    Dining diningItem2;
    Dining diningItem3;

    @org.junit.jupiter.api.Test
    void addDiningItem() {
        diningCollection.add(diningItem1);
        diningCollection.add(diningItem2);
        diningCollection.add(diningItem3);
        assertEquals(3, diningCollection.size());

        diningArrayList.add(diningItem1);
        diningArrayList.add(diningItem2);
        diningArrayList.add(diningItem3);
        assertEquals(3, diningArrayList.size());
    }

    @org.junit.jupiter.api.Test
    void removeDiningItem() {
        diningCollection.add(diningItem1);
        diningCollection.remove(diningItem1);
        assertEquals(0, diningCollection.size());

        diningArrayList.add(diningItem1);
        diningArrayList.remove(diningItem1);
        assertEquals(0, diningArrayList.size());
    }
}