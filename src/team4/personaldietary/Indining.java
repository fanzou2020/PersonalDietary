package team4.personaldietary;

public class Indining extends Dining {

   
    private String type;

    public Indining(String name, String meal, String serving, String group, String time, String type) {
        super(name, meal, serving, group, time);
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
        return super.toString() + " " + this.type;
    }
//    public String getDining() {
//        return name;
//    }

}
