package team4.personaldietary.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import team4.personaldietary.bean.Dining;
import team4.personaldietary.bean.Indining;
import team4.personaldietary.bean.Outdining;
import team4.personaldietary.business.DiningManager;

import java.time.LocalDateTime;

/**
 * The <tt>DiningTableRow</tt> class
 *
 * This class displays as a row in the TableView.
 * Wrapper class for Dining class, add a checkbox to mark a food item as consumed or not consumed.
 * Also, to keep consistent display of Inding and Outdining, both of them have type and retailer.
 * If an item is Inding, retailer is empty string, if an item is outdining, type is empty string.
 *
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 * @version 11/3/2020
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

    /**
     * @param diningItem
     * @param diningManager
     */
    public DiningTableRow(Dining diningItem, DiningManager diningManager) {
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
                    diningManager.markConsumed(DiningTableRow.this.getDiningItem());
                    diningManager.updateConsumedServing();
                    System.out.println("mark checkbox as True");
                }

                // if mark checkbox as unselected, call function to mark a food item as not consumed
                if (!checkBox.selectedProperty().get()) {
                    diningManager.markUnConsumed(DiningTableRow.this.getDiningItem());
                    diningManager.updateConsumedServing();
                    System.out.println("mark checkbox as False");
                }
            }
        });
    }

    /**
     * @return
     */
    public Dining getDiningItem() {
        return diningItem;
    }

    /**
     * @param diningItem
     */
    public void setDiningItem(Dining diningItem) {
        this.diningItem = diningItem;
    }

    /**
     * @return
     */
    public CheckBox getCheckBox() {
        return checkBox;
    }

    /**
     * @param checkBox
     */
    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    /**
     * @return
     */
    public boolean isConsumed() {
        return consumed;
    }

    /**
     * @param consumed
     */
    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public String getInOut() {
        return inOut;
    }

    /**
     * @param inOut
     */
    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    /**
     * @return
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * @return
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return
     */
    public String getMeal() {
        return meal;
    }

    /**
     * @param meal
     */
    public void setMeal(String meal) {
        this.meal = meal;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return
     */
    public String getRetailer() {
        return retailer;
    }

    /**
     * @param retailer
     */
    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    /**
     * @return
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return
     */
    public double getCalories() {
        return calories;
    }

    /**
     * @param calories
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * @return
     */
    public double getFat() {
        return fat;
    }

    /**
     * @param fat
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * @return
     */
    public double getSodium() {
        return sodium;
    }

    /**
     * @param sodium
     */
    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    /**
     * @return
     */
    public double getSugar() {
        return sugar;
    }

    /**
     * @param sugar
     */
    public void setSugar(double sugar) {
        this.sugar = sugar;
    }
}
