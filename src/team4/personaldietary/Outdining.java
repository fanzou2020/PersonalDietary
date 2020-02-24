package team4.personaldietary;

import java.util.Objects;

public class Outdining extends Dining {

    private String type;

    public Outdining(String name, String time, Group group, Serving serving,
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
        Outdining outdining = (Outdining) o;
        return (outdining.getName().equals(super.getName())) &&
                (outdining.getTime().equals(super.getTime())) &&
                (outdining.getGroup() == super.getGroup()) &&
                (outdining.getServing().equals(super.getServing())) &&
                (outdining.getMeal().equals(super.getMeal())) &&
                (outdining.getType().equals(type));

    }

    // implement hashcode, using an additional string "Outdining" and the parameters to generate
    // hashCode.
    @Override
    public int hashCode() {
        String i = "Outdining";
        return Objects.hash(i, super.getName(), super.getTime(), super.getGroup(),
                super.getServing(), super.getMeal(), type);
    }
}
