package team4.personaldietary;

public abstract class Dining {

    private String name;
    private String time;
    private Group group;
    private Serving serving;
    private String meal;

    public Dining(String name, String time, Group group, Serving serving, String meal) {
        this.name = name;
        this.time = time;
        this.group = group;
        this.serving = serving;
        this.meal = meal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Serving getServing() {
        return serving;
    }

    public void setServing(Serving serving) {
        this.serving = serving;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }
}
