package team4.personaldietary.persistence;
import team4.personaldietary.bean.Dining;

public interface DiningDAO {

    boolean addDiningItem(Dining diningItem);

    boolean removeDiningItem(Dining diningItem);

    boolean markConsumed(Dining diningItem);

    boolean markUnConsumed(Dining diningItem);

}
