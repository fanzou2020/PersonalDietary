package team4.personaldietary.bean;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The <tt>Indining</tt> bean class
 *
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 * @version 28/02/2020
 */
public class Indining extends Dining {

    private Type type;

    public  Indining(){
        this("",LocalDateTime.now(),new FoodGroup(), new Serving(), new Meal(), new Type());
    }
    /**
     * Constructor extends Dining class
     * @param name
     * @param time
     * @param foodGroup
     * @param serving
     * @param meal
     * @param type
     */
    public Indining(String name, LocalDateTime time, FoodGroup foodGroup, Serving serving,
                    Meal meal, Type type) {
        super(name, time, foodGroup, serving, meal);
        setType(type);
    }

    /**
     * @return a string of Type
     */
    public Type getType() {
        return type;
    }

    /**
     * set type
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return string name
     */
    @Override
    public String toString() {
        return "indining name = " + super.getName() + " consume = " + super.isConsumed();
    }

    /**
     * Equals method
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Indining indining = (Indining) o;
        return (indining.isConsumed() == super.isConsumed()) &&
                (indining.getName().equals(super.getName())) &&
                (indining.getFoodGroup().equals(super.getFoodGroup())) &&
                (indining.getServing().equals(super.getServing())) &&
                (indining.getMeal().equals(super.getMeal())) &&
                (indining.getType().equals(type)) &&
                (indining.getDiningId() == super.getDiningId());

    }

    /**
     * implement hashcode, using an additional string "Indining" and the parameters to generate
     * hashCode.
     * @return
     */
    //
    @Override
    public int hashCode() {
        String i = "Indining";
        return Objects.hash(i, super.isConsumed(), super.getName(),  super.getFoodGroup(), super.getServing(),
                 super.getMeal(), type);
    }
}
