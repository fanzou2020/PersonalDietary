package team4.personaldietary.business;

import team4.personaldietary.bean.Dining;

import java.util.Collection;

public interface DiningDAO {
    // Initialize Dining item


    // add Dining item to Collections
    boolean addDiningItem(Collection<Dining> diningCollection, Dining diningItem);

    // remove Dining item in Collections
    boolean removeDiningItem(Collection<Dining> diningCollection, Dining diningItem);

}
