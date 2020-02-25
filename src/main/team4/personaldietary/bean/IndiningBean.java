package main.team4.personaldietary.bean;

public class IndiningBean extends DiningBean {

    private String serving;
    private String type;

    public IndiningBean(String name, String time, String serving, String type, String group) {
        super(time, group, name);
        this.serving = serving;
        this.type = type;
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

}
