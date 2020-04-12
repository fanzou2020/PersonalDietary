package team4.personaldietary.business;

import team4.personaldietary.bean.Dining;

public interface DiningManagerInterface {

    boolean addDiningItem(Dining diningItem);

    boolean removeDiningItem(Dining diningItem);

    //boolean markConsumed(Dining diningItem);

    //boolean markUnConsumed(Dining diningItem);
    boolean updateConsumed(Dining diningItem);

    boolean hideConsumed();

    boolean unHideConsumed();

    boolean updateCurrServing();

    boolean updateConsumedServing();
}
