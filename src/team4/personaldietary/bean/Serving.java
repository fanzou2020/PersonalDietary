package team4.personaldietary.bean;

import java.util.Objects;
/**
 * The <tt>Serving</tt> bean class
 *
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 * @version 11/3/2020
 */
public class Serving {
    private int servingId;
    private double amount;
    private double calories;
    private double fat;
    private double sodium;
    private double sugar;
    private Dining dining;

    public Serving(){this(0,0,0,0,0, null);}
    /**
     * @param amount
     * @param calories
     * @param fat
     * @param sodium
     * @param sugar
     */
    public Serving(double amount, double calories, double fat, double sodium, double sugar,Dining dining) {
        this.setServingId(-1);
        this.setAmount(amount);
        this.setCalories(calories);
        this.setFat(fat);
        this.setSodium(sodium);
        this.setSugar(sugar);
        this.setDining(dining);
    }

    public int getServingId() {
        return servingId;
    }

    public void setServingId(int servingId) {
        this.servingId = servingId;
    }

    /**
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * set amount
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return calories value
     */
    public double getCalories() {
        return calories;
    }

    /**
     * set calories
     * @param calories
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * @return fat value
     */
    public double getFat() {
        return fat;
    }

    /**
     * set fat
     * @param fat
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * @return sodium
     */
    public double getSodium() {
        return sodium;
    }

    /**
     * set sodium
     * @param sodium
     */
    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    /**
     * @return sugar
     */
    public double getSugar() {
        return sugar;
    }

    /**
     * set sugar
     * @param sugar
     */
    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public Dining getDining() {
        return dining;
    }

    public void setDining(Dining dining) {
        this.dining = dining;
    }

    /**
     * @param o
     * @return
     */
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

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(amount, calories, fat, sodium, sugar);
    }

    /**
     * @return
     */
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

