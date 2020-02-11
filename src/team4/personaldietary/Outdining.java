package team4.personaldietary;

public class Outdining extends Dining {

    private String retailer;
    private String meal;
    private String group;

    public Outdining(String retailer, String time, String meal, String group, boolean isEaten) {
        super(time, isEaten);
        this.retailer = retailer;
        this.meal = meal;
        this.group = group;

    }
}
