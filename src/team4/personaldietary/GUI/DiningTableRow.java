package team4.personaldietary.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import team4.personaldietary.bean.Dining;
import team4.personaldietary.bean.Indining;
import team4.personaldietary.bean.Outdining;
import team4.personaldietary.business.DiningManagerImp;

import java.time.LocalDateTime;

/**
 * This class displays as a row in the TableView.
 * Wrapper class for Dining class, add a checkbox to mark a food item as consumed or not consumed.
 * Also, to keep consistent display of Inding and Outdining, both of them have type and retailer.
 * If an item is Inding, retailer is empty string, if an item is outdining, type is empty string.
*/
public class DiningTableRow {
    private Dining diningItem;
    private CheckBox checkBox;
    private boolean consumed;
    private String name;
    private String inOut;
    private LocalDateTime time;
    private String group;
    private String meal;
    private String type;
    private String retailer;
    private String amount;
    private double calories;
    private double fat;
    private double sodium;
    private double sugar;

    public DiningTableRow(Dining diningItem, DiningManagerImp diningDAO) {
        this.diningItem = diningItem;
        this.checkBox = new CheckBox();
        consumed = diningItem.isConsumed();
        name = diningItem.getName();
        time = diningItem.getTime();
        group = diningItem.getFoodGroup().toString();
        meal = diningItem.getMeal();
        amount = diningItem.getServing().getAmount();
        calories = diningItem.getServing().getCalories();
        fat = diningItem.getServing().getFat();
        sodium = diningItem.getServing().getSodium();
        sugar = diningItem.getServing().getSugar();

        if (diningItem instanceof Indining) {
            retailer = " ";
            inOut = "In";
            type = ((Indining) diningItem).getType();
        }
        else if (diningItem instanceof Outdining) {
            type = " ";
            inOut = "Out";
            retailer = ((Outdining) diningItem).getRetailer();
        }

        // ******** event handle to mark a food item as consumed or unconsumed *******************
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // if mark checkbox as selected, call function to mark a food item as consumed
                if (checkBox.selectedProperty().get()) {
                    diningDAO.markConsumed(DiningTableRow.this.getDiningItem());
                    diningDAO.updateConsumedServing();
                    System.out.println("mark checkbox as True");
                }

                // if mark checkbox as unselected, call function to mark a food item as not consumed
                if (!checkBox.selectedProperty().get()) {
                    diningDAO.markUnConsumed(DiningTableRow.this.getDiningItem());
                    diningDAO.updateConsumedServing();
                    System.out.println("mark checkbox as False");
                }
            }
        });
    }

    public Dining getDiningItem() {
        return diningItem;
    }

    public void setDiningItem(Dining diningItem) {
        this.diningItem = diningItem;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }
}
