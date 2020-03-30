package team4.personaldietary.bean;

public class Meal {
    private int mealId;
    private String mealName;

    public Meal(String meal_name) {
        this.mealName = meal_name;
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
}
