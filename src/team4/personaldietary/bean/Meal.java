package team4.personaldietary.bean;

import java.util.Objects;

public class Meal {
    private int mealId;
    private String mealName;

    public Meal(){this("");}

    public Meal(String mealname) {
        this.setMealId(-1);
        this.setMealName(mealname);
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return mealId == meal.mealId &&
                Objects.equals(mealName, meal.mealName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId, mealName);
    }

    @Override
    public String toString() {
        return this.getMealName();
    }
}
