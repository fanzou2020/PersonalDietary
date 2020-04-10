package team4.personaldietary.bean;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The <tt>Outdining</tt> bean class
 *
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 * @version 28/02/2020
 */
public class Outdining extends Dining {

    private Retailer retailer;

    public  Outdining(){
        this("",LocalDateTime.now(),new FoodGroup(), new Serving(), new Meal(),new Retailer());
    }
    /**
     * Constructor extends Dining class
     * @param name
     * @param time
     * @param foodGroup
     * @param serving
     * @param meal
     * @param retailer
     */
    public Outdining(String name, LocalDateTime time, FoodGroup foodGroup, Serving serving,
                     Meal meal, Retailer retailer) {
        super(name, time, foodGroup, serving, meal);
        setRetailer(retailer);
    }

    /**
     * @return a string of retailer
     */
    public Retailer getRetailer() {
        return retailer;
    }

    /**
     * set retailer
     * @param retailer
     */
    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    /**
     * @return outdining name
     */
    @Override
    public String toString() {
        return "outdining name = " + super.getName() + " consume = " + super.isConsumed();
    }

    /**
     * Equal method
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outdining outdining = (Outdining) o;
        return (outdining.isConsumed() == super.isConsumed()) &&
                (outdining.getName().equals(super.getName())) &&
                (outdining.getFoodGroup().equals(super.getFoodGroup())) &&
                (outdining.getServing().equals(super.getServing())) &&
                (outdining.getMeal().equals(super.getMeal())) &&
                (outdining.getRetailer().equals(retailer));

    }

    /**
     * implement hashcode, using an additional string "Outdining" and the parameters to generate
     * hashCode.
     * @return
     */
    @Override
    public int hashCode() {
        String i = "Outdining";
        return Objects.hash(i, super.isConsumed(), super.getName(),  super.getFoodGroup(), super.getServing(),
                super.getMeal(), retailer);
    }
}
