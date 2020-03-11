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

    private String retailer;

    public  Outdining(){
        this("",LocalDateTime.now(),FoodGroup.grain_products,new Serving(),"","");
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
                     String meal, String retailer) {
        super(name, time, foodGroup, serving, meal);
        setRetailer(retailer);
    }

    /**
     * @return a string of retailer
     */
    public String getRetailer() {
        return retailer;
    }

    /**
     * set retailer
     * @param retailer
     */
    public void setRetailer(String retailer) {
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
                (outdining.getTime().equals(super.getTime())) &&
                (outdining.getFoodGroup() == super.getFoodGroup()) &&
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
        return Objects.hash(i, super.isConsumed(), super.getName(), super.getTime(), super.getFoodGroup(),
                super.getServing(), super.getMeal(), retailer);
    }
}
