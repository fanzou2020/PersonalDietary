package team4.personaldietary.business;

import team4.personaldietary.bean.Dining;
import team4.personaldietary.bean.Serving;

public interface DiningManagerInterface {

    boolean addDiningItem(Serving serving);

    boolean addDiningItemDataModel(Dining diningItem);

    boolean markConsumed(Dining diningItem);

    boolean markUnConsumed(Dining diningItem);

    boolean hideConsumed();

    boolean unHideConsumed();

    boolean updateCurrServing();

    boolean updateConsumedServing();
}
