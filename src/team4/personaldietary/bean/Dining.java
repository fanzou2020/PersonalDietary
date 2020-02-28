package team4.personaldietary.bean;

/**
 * The <tt>Dining</tt> bean class
 *
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 * @version 28/02/2020
 */
public class Dining {

    private String name;
    private String time;
    private FoodGroup foodGroup;
    private String serving;
    private String meal;

    /**
     * Constructor
     * @param name
     * @param time
     * @param foodGroup
     * @param serving
     * @param meal
     */
    public Dining(String name, String time, FoodGroup foodGroup, String serving, String meal) {
        this.name = name;
        this.time = time;
        this.foodGroup = foodGroup;
        this.serving = serving;
        this.meal = meal;
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

    /**
     * @return a string of time
     */
    public String getTime() {
        return time;
    }

    /**
     * set time
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return a FoodGroup object
     */
    public FoodGroup getFoodGroup() {
        return foodGroup;
    }

    /**
     * set FoodGroup
     * @param foodGroup
     */
    public void setFoodGroup(FoodGroup foodGroup) {
        this.foodGroup = foodGroup;
    }

    /**
     * @return a string of serving
     */
    public String getServing() {
        return serving;
    }

    /**
     * set serving
     * @param serving
     */
    public void setServing(String serving) {
        this.serving = serving;
    }

    /**
     * @return a string of meal
     */
    public String getMeal() {
        return meal;
    }

    /**
     * set meal
     * @param meal
     */
    public void setMeal(String meal) {
        this.meal = meal;
    }
}
