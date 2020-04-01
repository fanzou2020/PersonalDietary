package team4.personaldietary.bean;

import java.util.Objects;

public class Retailer {
    private int retailerId;
    private String retailerName;

    public Retailer(){this("");}

    public Retailer(String retailerName) {
        this.setRetailerId(-1);
        this.setRetailerName(retailerName);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Retailer retailer = (Retailer) o;
        return retailerId == retailer.retailerId &&
                Objects.equals(retailerName, retailer.retailerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retailerId, retailerName);
    }
}
