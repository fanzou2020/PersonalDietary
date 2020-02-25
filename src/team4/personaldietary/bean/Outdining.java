package team4.personaldietary.bean;

import java.util.Objects;

public class Outdining extends Dining {

    private String retailer;

    public Outdining(String name, String time, Group group, String serving,
                     String meal, String retailer) {
        super(name, time, group, serving, meal);
        this.retailer = retailer;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    @Override
    public String toString() {
        return "outdining name = " + super.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outdining outdining = (Outdining) o;
        return (outdining.getName().equals(super.getName())) &&
                (outdining.getTime().equals(super.getTime())) &&
                (outdining.getGroup() == super.getGroup()) &&
                (outdining.getServing().equals(super.getServing())) &&
                (outdining.getMeal().equals(super.getMeal())) &&
                (outdining.getRetailer().equals(retailer));

    }

    // implement hashcode, using an additional string "Outdining" and the parameters to generate
    // hashCode.
    @Override
    public int hashCode() {
        String i = "Outdining";
        return Objects.hash(i, super.getName(), super.getTime(), super.getGroup(),
                super.getServing(), super.getMeal(), retailer);
    }
}
