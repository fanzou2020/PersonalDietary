package team4.personaldietary.bean;

import java.util.Objects;

public class Serving {
    private String amount;
    private double calories;
    private double fat;
    private double sodium;
    private double sugar;

    public Serving(String amount, double calories, double fat, double sodium, double sugar) {
        this.amount = amount;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.sugar = sugar;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serving serving = (Serving) o;
        return Double.compare(serving.calories, calories) == 0 &&
                Double.compare(serving.fat, fat) == 0 &&
                Double.compare(serving.sodium, sodium) == 0 &&
                Double.compare(serving.sugar, sugar) == 0 &&
                Objects.equals(amount, serving.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, calories, fat, sodium, sugar);
    }

    @Override
    public String toString() {
        return "Serving{" +
                "amount='" + amount + '\'' +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", sugar=" + sugar +
                '}';
    }
}

