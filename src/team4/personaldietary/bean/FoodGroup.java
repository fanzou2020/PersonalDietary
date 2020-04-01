package team4.personaldietary.bean;
/**
 * The <tt>FoodGroup</tt> bean class
 *
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 * @version 11/3/2020
 */
public class FoodGroup {
    private int foodGroupId;
    private String foodGroupName;

    public FoodGroup(){
        this("");
    }

    public FoodGroup(String foodGroupName) {
        this.setFoodGroupId(-1);
        this.setFoodGroupName(foodGroupName);
    }

    public int getFoodGroupId() {
        return foodGroupId;
    }

    public void setFoodGroupId(int foodGroupId) {
        this.foodGroupId = foodGroupId;
    }

    public String getFoodGroupName() {
        return foodGroupName;
    }

    public void setFoodGroupName(String foodGroupName) {
        this.foodGroupName = foodGroupName;
    }
}
