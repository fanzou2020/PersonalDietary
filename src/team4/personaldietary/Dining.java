package team4.personaldietary;

public class Dining {
    private String name;
     private String serving;
    private String time;
    private String group;
    private String meal;

      public Dining(String name, String meal, String serving, String group, String time) {
          this.name = name;
          this.meal = meal;
          this.serving = serving;
          this.group = group;
          this.time = time;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }
  

    public String getName() {
        return name;
    }

    public String getServing() {
        return serving;
    }

    public String getGroup() {
        return group;
    }

    public String getMeal() {
        return meal;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    

    public String toString() {
        return " " + this.name + " " + this.meal + " " + this.serving + " " + this.group + " " + this.time;
    }

//    public String getDining() {
//        return "";
//    }
}
