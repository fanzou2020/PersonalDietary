package team4.personaldietary;

public class Dining {

    private String time;
    private boolean isEaten;

    public Dining(String time, boolean isEaten) {
        this.time = time;
        this.isEaten = isEaten;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getIsEaten() {
        return isEaten;
    }

    public void setIsEaten(boolean eaten) {
        isEaten = eaten;
    }

    public String toString() {
        return "" +isEaten;
    }

    public String getDining() {
        return "";
    }
}
