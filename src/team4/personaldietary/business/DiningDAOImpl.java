package team4.personaldietary.business;

import team4.personaldietary.bean.Dining;

import java.util.ArrayList;
import java.util.Collection;

public class DiningDAOImpl implements DiningDAO {
    private ArrayList<Dining> diningArrayList = new ArrayList<>(); // consider this as the data model.

//    public DiningDAOImpl(ArrayList<Dining> diningArrayList) {
//        this.diningArrayList = diningArrayList;
//    }


    @Override
    public boolean addDiningItem(Collection<Dining> diningCollection, Dining diningItem) {
        diningCollection.add(diningItem);  // control the GUI
        diningArrayList.add(diningItem);   // control the data model.
        return true;
    }

    @Override
    public boolean removeDiningItem(Collection<Dining> diningCollection, Dining diningItem) {
        diningCollection.remove(diningItem);  // control the GUI
        diningArrayList.remove(diningItem);   // control the data model
        return true;
    }
}
