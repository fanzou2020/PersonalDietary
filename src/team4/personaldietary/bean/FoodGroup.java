package team4.personaldietary.bean;

import java.util.Objects;

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

    @Override
    public String toString() {
        return this.getFoodGroupName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodGroup foodGroup = (FoodGroup) o;
        return foodGroupId == foodGroup.foodGroupId &&
                Objects.equals(foodGroupName, foodGroup.foodGroupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodGroupId, foodGroupName);
    }
}
