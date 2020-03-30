package team4.personaldietary.bean;

public class Retailer {
    private int retailerId;
    private String retailerName;

    public Retailer(String retailer_name) {
        this.retailerName = retailer_name;
    }

    public int getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }
}
