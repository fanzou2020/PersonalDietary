package team4.personaldietary.business;

import team4.personaldietary.GUI.TableItem;
import team4.personaldietary.bean.Dining;

import java.util.Collection;

public interface DiningDAO {
    // Initialize Dining item

    // add Dining item to Collections
    boolean addDiningItem(Dining diningItem);
    // remove Dining item from Collections
    boolean removeDiningItem(TableItem tableItem);

    boolean markConsumed(TableItem tableItem);

    boolean markUnConsumed(TableItem tableItem);

    boolean hideConsumed();

    boolean unHideConsumed();

    boolean updateCurrServing();

    boolean updateConsumedServing();

}
