package team4.personaldietary.bean;

import java.time.LocalDateTime;
import java.util.Objects;

public class Outdining extends Dining {

    private String retailer;

    public Outdining(String name, LocalDateTime time, FoodGroup foodGroup, Serving serving,
                     String meal, String retailer) {
        super(name, time, foodGroup, serving, meal);
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
        return "outdining name = " + super.getName() + " consume = " + super.isConsumed();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outdining outdining = (Outdining) o;
        return (outdining.isConsumed() == super.isConsumed()) &&
                (outdining.getName().equals(super.getName())) &&
                (outdining.getTime().equals(super.getTime())) &&
                (outdining.getFoodGroup() == super.getFoodGroup()) &&
                (outdining.getServing().equals(super.getServing())) &&
                (outdining.getMeal().equals(super.getMeal())) &&
                (outdining.getRetailer().equals(retailer));

    }

    // implement hashcode, using an additional string "Outdining" and the parameters to generate
    // hashCode.
    @Override
    public int hashCode() {
        String i = "Outdining";
        return Objects.hash(i, super.isConsumed(), super.getName(), super.getTime(), super.getFoodGroup(),
                super.getServing(), super.getMeal(), retailer);
    }
}
