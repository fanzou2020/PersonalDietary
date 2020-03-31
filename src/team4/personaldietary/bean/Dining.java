package team4.personaldietary.bean;
import java.time.LocalDateTime;

/**
 * The <tt>Dining</tt> bean class
 *
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 * @version 11/3/2020
 */
public class Dining {

    private int diningId;
    private String name;
    private LocalDateTime time;
    private FoodGroup foodGroup;
    private Serving serving;
    private Meal meal;
    private boolean consumed;

    public Dining(String name, LocalDateTime time, FoodGroup foodGroup, Serving serving, Meal meal) {
        setName(name);
        setTime(time);
        setFoodGroup(foodGroup);
        setServing(serving);
        setMeal(meal);
        setConsumed(false);
    }

    public int getDiningId() {
        return diningId;
    }

    public void setDiningId(int diningId) {
        this.diningId = diningId;
    }

    /**
     * @return a string of name
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public FoodGroup getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(FoodGroup foodGroup) {
        this.foodGroup = foodGroup;
    }

    public Serving getServing() {
        return serving;
    }

    public void setServing(Serving serving) {
        this.serving = serving;
    }

    /**
     * @return a string of meal
     */
    public Meal getMeal() {
        return meal;
    }

    /**
     * set meal
     * @param meal
     */
    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }
}
