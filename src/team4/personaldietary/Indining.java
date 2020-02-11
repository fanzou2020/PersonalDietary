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

}
