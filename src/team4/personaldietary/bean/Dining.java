package team4.personaldietary.bean;

import java.time.LocalDateTime;

public abstract class Dining {

    private String name;
    private LocalDateTime time;
    private FoodGroup foodGroup;
    private Serving serving;
    private String meal;
    private boolean consumed;

    public Dining(String name, LocalDateTime time, FoodGroup foodGroup, Serving serving, String meal) {
        this.name = name;
        this.time = time;
        this.foodGroup = foodGroup;
        this.serving = serving;
        this.meal = meal;
        this.consumed = false;
    }

    public String getName() {
        return name;
    }

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

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }
}
