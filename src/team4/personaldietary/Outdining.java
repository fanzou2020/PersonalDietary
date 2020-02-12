package team4.personaldietary;

public class Outdining extends Dining {

    private String retailer;
    private String meal;
    private String group;

    public Outdining(String retailer, String time, String meal, String group) {
        super(time);
        this.retailer = retailer;
        this.meal = meal;
        this.group = group;

    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDining() {
        return retailer;
    }
}
