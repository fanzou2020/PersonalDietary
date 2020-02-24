package team4.personaldietary;

import java.util.Objects;

public class Indining extends Dining {

    private String type;

    public Indining(String name, String time, Group group, Serving serving,
        String meal, String type) {
        super(name, time, group, serving, meal);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Indining indining = (Indining) o;
        return (indining.getName().equals(super.getName())) &&
                (indining.getTime().equals(super.getTime())) &&
                (indining.getGroup() == super.getGroup()) &&
                (indining.getServing().equals(super.getServing())) &&
                (indining.getMeal().equals(super.getMeal())) &&
                (indining.getType().equals(type));

    }

    // implement hashcode, using an additional string "Indining" and the parameters to generate
    // hashCode.
    @Override
    public int hashCode() {
        String i = "Indining";
        return Objects.hash(i, super.getName(), super.getTime(), super.getGroup(),
                super.getServing(), super.getMeal(), type);
    }
}
