package team4.personaldietary;

public class Indining extends Dining {

    private String name;
    private String serving;
    private String type;

    public Indining(String name, String time, String serving, String type, boolean isEaten) {
        super(time, isEaten);
        this.name = name;
        this.serving = serving;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDining() {
        return name;
    }

}
