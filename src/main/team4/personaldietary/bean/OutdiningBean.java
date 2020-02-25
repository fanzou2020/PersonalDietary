package main.team4.personaldietary.bean;

public class OutdiningBean extends DiningBean {

    private String retailer;
    private String meal;

    public OutdiningBean(String name, String retailer, String time, String meal, String group) {
        super(time, group, name);
        this.retailer = retailer;
        this.meal = meal;

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

}
