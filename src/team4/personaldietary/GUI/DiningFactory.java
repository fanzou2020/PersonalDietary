package team4.personaldietary.GUI;

import team4.personaldietary.bean.*;

import java.time.LocalDateTime;

public class DiningFactory {

    public static Dining getDining(boolean isIndining, String name, LocalDateTime time, FoodGroup foodGroup,
                            Serving serving, Meal meal, Type type, Retailer retailer) {
        // if it is an InDining object
        if (isIndining) {
            return new Indining(name, time, foodGroup, serving, meal, type);
        }

        // else it is an OutDining object
        else {
            return new Outdining(name, time, foodGroup, serving, meal, retailer);
        }
    }
}
