package team4.personaldietary;

public class Outdining extends Dining {

    private String retailer;
 

    public Outdining(String name, String meal, String serving, String group, String time, String retailer) {
        super(name, meal, serving, group, time);
        this.retailer = retailer;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

     public String toString(){
        return super.toString() + " " + this.retailer;
    }

    public String getDining() {
        return retailer;
    }
}
