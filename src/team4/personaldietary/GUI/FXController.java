package team4.personaldietary.GUI;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.event.*;
import team4.personaldietary.bean.*;
import team4.personaldietary.business.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FXController {
    @FXML private BorderPane bPane;

    // For left part
    private TextField nameField = new TextField();
    private DatePicker datePicker = new DatePicker();
    private TextField timeField = new TextField();
    private TextField mealField = new TextField();
    private TextField typeField = new TextField();
    private TextField retailerField = new TextField();
    private TextField amountField = new TextField();
    private TextField caloriesField = new TextField();
    private TextField fatField = new TextField();
    private TextField sodiumField = new TextField();
    private TextField sugarField = new TextField();
    private ComboBox<String> foodGroup;

    // items for right part
    private ObservableList<String> currServingList = FXCollections.observableArrayList();
    private ObservableList<String> consumedServingList = FXCollections.observableArrayList();
    private ListView<String> currServingListView = new ListView<>(currServingList);
    private ListView<String> consumedServingListView = new ListView<>(consumedServingList);

    // items for center part
    private ObservableList<DiningTableRow> diningList = FXCollections.observableArrayList(); // ObservableList related to TableView.
    private TableView<DiningTableRow> table = new TableView<>(diningList); // TableView in the center of bPane.
    private int[] numItemsInFoodGroup = {0, 0, 0, 0}; // number of items for each food group
    private boolean hideConsumed = false;  // whether to hide the consumed food item.

    //Initializing buttons for food group
    private Button buttonVeg = new Button("Vegetables and Fruits");
    private Button buttonGrain = new Button("Grain Products");
    private Button buttonMilk = new Button("Milk and Alternatives");
    private Button buttonMeat = new Button("Meat and Alternatives");

    // Business layer controller
    private DiningManagerImp diningManagerImp = new DiningManagerImp(diningList, currServingList, consumedServingList);

    @FXML
    private void initialize() {
        initialLeftPart();
        initialRightPart();
        initialBottomPart();
        initialCenterPart();
    }

    // Initialize the ListView in the center of boarder pane
    private void initialCenterPart() {
        TableColumn<DiningTableRow, CheckBox> selectCol = new TableColumn<>("Consumed");
        selectCol.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        table.getColumns().add(selectCol);

        TableColumn<DiningTableRow,String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(nameCol);

        TableColumn<DiningTableRow, String> inOutCol = new TableColumn<>("In/Out");
        inOutCol.setCellValueFactory(new PropertyValueFactory<>("inOut"));
        table.getColumns().add(inOutCol);

        TableColumn<DiningTableRow, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        table.getColumns().add(timeCol);

        TableColumn<DiningTableRow, String> groupCol = new TableColumn<>("Group");
        groupCol.setCellValueFactory(new PropertyValueFactory<>("group"));
        table.getColumns().add(groupCol);

        TableColumn<DiningTableRow, String> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        table.getColumns().add(amountCol);

        TableColumn<DiningTableRow, Double> caloriesCol = new TableColumn<>("Calories");
        caloriesCol.setCellValueFactory(new PropertyValueFactory<>("calories"));
        table.getColumns().add(caloriesCol);

        TableColumn<DiningTableRow, Double> fatCol = new TableColumn<>("Fat");
        fatCol.setCellValueFactory(new PropertyValueFactory<>("fat"));
        table.getColumns().add(fatCol);

        TableColumn<DiningTableRow, Double> sodiumCol = new TableColumn<>("Sodium");
        sodiumCol.setCellValueFactory(new PropertyValueFactory<>("sodium"));
        table.getColumns().add(sodiumCol);

        TableColumn<DiningTableRow, Double> sugarCol = new TableColumn<>("Sugar");
        sugarCol.setCellValueFactory(new PropertyValueFactory<>("sugar"));
        table.getColumns().add(sugarCol);

        TableColumn<DiningTableRow, String> mealCol = new TableColumn<>("Meal");
        mealCol.setCellValueFactory(new PropertyValueFactory<>("meal"));
        table.getColumns().add(mealCol);

        TableColumn<DiningTableRow, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        table.getColumns().add(typeCol);

        TableColumn<DiningTableRow, String> retailerCol= new TableColumn<>("Retailer");
        retailerCol.setCellValueFactory(new PropertyValueFactory<>("retailer"));
        table.getColumns().add(retailerCol);

        bPane.setCenter(table);
    }

    // Initialize the Left part of boarder pane
    private void initialLeftPart() {
        BooleanProperty inOutDining = new SimpleBooleanProperty(true);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        //Vertical padding for cell
        gridPane.setVgap(2);
        //Horizontal padding for cell
        gridPane.setHgap(2);
        // Choice box for meal
        gridPane.setAlignment(Pos.TOP_CENTER);

        // here, it is better create a string array, then foreach loop generate text object
        Text nameText = new Text("Name");
        Text dateText = new Text("Date");
        Text timeText = new Text("Time");
        Text groupText = new Text("Group");
        Text servingText = new Text("Serving");
        Text mealText = new Text("Meal");
        Text typeText = new Text("Type");
        Text retailerText = new Text("Retailer");
        Text amountText = new Text("Amount");
        Text caloriesText = new Text("Calories");
        Text fatText = new Text("Fat");
        Text sodiumText = new Text("Sodium");
        Text sugarText = new Text("Sugar");

        // Since default indining/outdining toggle is indining, default gray out retailer field.
        retailerField.setDisable(true);

        Button addItemButton = new Button("Add Item");

        //Combo box for food group
        foodGroup = new ComboBox<>();
        foodGroup.setPromptText("What group does it belong?");
        foodGroup.getItems().addAll("Vegetables and Fruits", "Grain Products", "Milk and Alternatives", "Meat and Alternatives");


        //*********************************** Set toggle switch ***************************************************
        //Below are the instances to support toggle switches
        TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(.25));
        FillTransition fillAnimation = new FillTransition(Duration.seconds(.25));
        ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);


        //Toggle Switch for In Dining and out Dining
        Pane dining = new Pane();
        Text inOut = new Text();
        inOut.setFill(Color.BLACK);
        inOut.textProperty().bind(Bindings.when(inOutDining).then("In Dining").otherwise("Out Dining"));

        //Creating the background of toggle switch
        Rectangle background = new Rectangle(40, 20);
        background.setArcWidth(20);
        background.setArcHeight(20);
        background.setFill(Color.LIGHTGREEN);
        background.setStroke(Color.LIGHTGRAY);

        //Create the circle that would be button for the switch
        Circle trigger = new Circle(10);
        trigger.setCenterX(10);
        trigger.setCenterY(10);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);

        //Set the animation to the circle
        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);

        //Listener to inOutDining
        inOutDining.addListener((obs, oldState, newState) -> {
            boolean inDining = newState.booleanValue();
            translateAnimation.setToX(inDining ? 0 : 40 - 20);
            fillAnimation.setFromValue(inDining ? Color.WHITE : Color.LIGHTGREEN);
            fillAnimation.setToValue(inDining ? Color.LIGHTGREEN : Color.WHITE);
            animation.play();
        });
        //Mouse click on the trigger (Circle)
        trigger.setOnMouseClicked(event -> {
            inOutDining.set(!inOutDining.get());

            //Grayed out text area based on indining/outdining
            if(inOutDining.getValue()){
                //Indining
                typeField.setDisable(false);
                retailerField.clear();
                retailerField.setDisable(true);

            } else {
                //Outdining
                retailerField.setDisable(false);
                typeField.clear();
                typeField.setDisable(true);
            }
        });

        dining.getChildren().addAll(background, trigger);
        //***************************************** End of setting toggle switch ***********************************



        //********************** Event handle for add food item ************************
        // Event handler for add item
        EventHandler<ActionEvent> addEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Dining foodItem = null;
                // if it is indining food item
                if (inOutDining.get() && validateInputIndining()) {
                    Serving servingItem = new Serving(amountField.getText(),
                            Double.parseDouble(caloriesField.getText()),
                            Double.parseDouble(fatField.getText()),
                            Double.parseDouble(sodiumField.getText()),
                            Double.parseDouble(sugarField.getText())
                    );
                    LocalDateTime dateTime = LocalDateTime.of(
                            datePicker.getValue(),
                            LocalTime.parse(timeField.getText())
                    );
                    foodItem = new Indining(nameField.getText(), dateTime,
                            stringToGroup(foodGroup.getValue()), servingItem,
                            mealField.getText(), typeField.getText());
                }
                // else, add outdining food item
                else if(!inOutDining.get() && validateInputOutdining()){
                    Serving servingItem = new Serving(amountField.getText(),
                            Double.parseDouble(caloriesField.getText()),
                            Double.parseDouble(fatField.getText()),
                            Double.parseDouble(sodiumField.getText()),
                            Double.parseDouble(sugarField.getText())
                    );
                    LocalDateTime dateTime = LocalDateTime.of(
                            datePicker.getValue(),
                            LocalTime.parse(timeField.getText())
                    );
                    foodItem = new Outdining(nameField.getText(), dateTime,
                            stringToGroup(foodGroup.getValue()), servingItem,
                            mealField.getText(), retailerField.getText());
                }

                if(foodItem != null) { // call add food item function of the business layer
                    diningManagerImp.addDiningItem(foodItem);
                    markFoodGroupAdd(foodItem.getFoodGroup());
                    diningManagerImp.updateCurrServing();
                    diningManagerImp.updateConsumedServing();
                    refreshItems();
                }
            }
        };
        addItemButton.setOnAction(addEventHandler);
        // **************************** End of event handle for add food item ******************

        // add items to gridPane
        gridPane.add(nameText, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(dateText, 0, 1);
        gridPane.add(datePicker, 1,1);
        gridPane.add(timeText, 0, 2);
        gridPane.add(timeField, 1, 2);
        gridPane.add(groupText, 0, 3);
        gridPane.add(foodGroup, 1, 3);
        gridPane.add(mealText, 0, 4);
        gridPane.add(mealField, 1, 4);
        gridPane.add(typeText, 0, 5);
        gridPane.add(typeField, 1, 5);
        gridPane.add(retailerText, 0, 6);
        gridPane.add(retailerField, 1, 6);
        gridPane.add(servingText, 0, 8);
        gridPane.add(amountText, 0, 9);
        gridPane.add(amountField, 1, 9);
        gridPane.add(caloriesText, 0,10);
        gridPane.add(caloriesField, 1,10);
        gridPane.add(fatText, 0,11);
        gridPane.add(fatField, 1, 11);
        gridPane.add(sodiumText, 0, 12);
        gridPane.add(sodiumField, 1, 12);
        gridPane.add(sugarText, 0, 13);
        gridPane.add(sugarField, 1, 13);
        gridPane.add(dining, 0, 14);
        gridPane.add(inOut, 1, 14);
        gridPane.add(addItemButton, 1, 16);

        // Setting the boarder pane
        bPane.setLeft(gridPane);
    }

    // Initialize the Right part of boarder pane
    private void initialRightPart() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        //Vertical padding for cell
        gridPane.setVgap(2);
        //Horizontal padding for cell
        gridPane.setHgap(2);
        // Choice box for meal
        gridPane.setAlignment(Pos.TOP_CENTER);

        Button removeButton = new Button("Remove Item");
        gridPane.add(removeButton, 0, 0);

        Button hideUnhideButton = new Button("Hide/Unhide Consumed");
        gridPane.add(hideUnhideButton, 0, 1);

        // ************** event handle for remove food item ************************************
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(table.getItems().size()>0) {
                    DiningTableRow selectedItem = table.getSelectionModel().getSelectedItem();
                    if(selectedItem == null ) return;
                    diningManagerImp.removeDiningItem(selectedItem.getDiningItem());
                    markFoodGroupRemove(selectedItem.getDiningItem().getFoodGroup());
                    diningManagerImp.updateCurrServing();
                    diningManagerImp.updateConsumedServing();
                }
            }
        });
        // ************* end of remove food item ************************************************


        // ************** event handle for Hide/Unhide consumed food item ***********************
        hideUnhideButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // hide consumed is true, click this button to unhide
                if (hideConsumed) {
                    diningManagerImp.unHideConsumed();
                    hideConsumed = false;
                }

                // hide consumed is false, click this button to hide
                else {
                    diningManagerImp.hideConsumed();
                    hideConsumed = true;
                }
                diningManagerImp.updateCurrServing();
                diningManagerImp.updateConsumedServing();
            }
        });
        // *********** end of Hide/Unhide consumed food *****************************************

        // Initialize current serving list and consumed serving list.
        currServingList.add("Total Serving of Displayed Items");
        currServingList.add("Calories :    ");
        currServingList.add("Fat :         ");
        currServingList.add("Sodium :      ");
        currServingList.add("Sugar :       ");

        gridPane.add(currServingListView, 0, 3);

        consumedServingList.add("Total Serving of Consumed Items");
        consumedServingList.add("Calories :    ");
        consumedServingList.add("Fat :         ");
        consumedServingList.add("Sodium :      ");
        consumedServingList.add("Sugar :       ");

        gridPane.add(consumedServingListView, 0, 5);

        bPane.setRight(gridPane);
    }

    // Initialize the Bottom part of boarder pane
    private void initialBottomPart() {
        HBox bottomMenu = new HBox(buttonVeg, buttonGrain, buttonMilk, buttonMeat);

        //Customize HBox
        bottomMenu.setPadding(new Insets(10, 0, 10, 0));
        bottomMenu.setSpacing(5);
        bottomMenu.setAlignment(Pos.CENTER);

        bPane.setBottom(bottomMenu);
    }

    private void refreshItems(){
        // clear the content in left panel after click the add button.
        nameField.clear();
        nameField.setPromptText("");
        datePicker.getEditor().clear();
        timeField.clear();
        timeField.setPromptText("");
        mealField.clear();
        mealField.setPromptText("");
        retailerField.clear();
        retailerField.setPromptText("");
        typeField.clear();
        typeField.setPromptText("");
        foodGroup.getSelectionModel().clearSelection();
        foodGroup.setPromptText("What group does it belong?");
        foodGroup.setStyle(null);
        amountField.clear();
        caloriesField.clear();
        fatField.clear();
        sugarField.clear();
        sodiumField.clear();
    }

    private FoodGroup stringToGroup(String s) {
        switch (s){
            case "Grain Products": return FoodGroup.grain_products;
            case "Milk and Alternatives": return FoodGroup.milk_and_alternatives;
            case "Meat and Alternatives": return FoodGroup.meat_and_alternatives;
            case "Vegetables and Fruits": return FoodGroup.vegetable_and_fruit;
            default: return null;
        }
    }

    private void markFoodGroupAdd(FoodGroup foodGroup) {
        if (foodGroup == FoodGroup.vegetable_and_fruit) {
            numItemsInFoodGroup[0]++;
            if (numItemsInFoodGroup[0] > 0) buttonVeg.setStyle("-fx-background-color: green");
        }
        else if (foodGroup == FoodGroup.grain_products) {
            numItemsInFoodGroup[1]++;
            if (numItemsInFoodGroup[1] >0)  buttonGrain.setStyle("-fx-background-color: green");
        }
        else if (foodGroup == FoodGroup.milk_and_alternatives) {
            numItemsInFoodGroup[2]++;
            if (numItemsInFoodGroup[2] > 0) buttonMilk.setStyle("-fx-background-color: green");
        }
        else {
            numItemsInFoodGroup[3]++;
            if (numItemsInFoodGroup[3] > 0) buttonMeat.setStyle("-fx-background-color: green");
        }
    }

    private void markFoodGroupRemove(FoodGroup foodGroup) {
        if (foodGroup == FoodGroup.vegetable_and_fruit) {
            numItemsInFoodGroup[0]--;
            if (numItemsInFoodGroup[0] <= 0) buttonVeg.setStyle(null);
        }
        else if (foodGroup == FoodGroup.grain_products) {
            numItemsInFoodGroup[1]--;
            if (numItemsInFoodGroup[1] <= 0)  buttonGrain.setStyle(null);
        }
        else if (foodGroup == FoodGroup.milk_and_alternatives) {
            numItemsInFoodGroup[2]--;
            if (numItemsInFoodGroup[2] <= 0) buttonMilk.setStyle(null);
        }
        else {
            numItemsInFoodGroup[3]--;
            if (numItemsInFoodGroup[3] <= 0) buttonMeat.setStyle(null);
        }
    }

    private boolean validateInput(TextField textField){
        if(textField.getText() == null || textField.getText().isEmpty()){
            textField.setPromptText("Invalid input");
            textField.getParent().requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateInput(ComboBox<String> comboBox){
        if(comboBox.getValue() == null ){
            comboBox.setStyle("-fx-background-color: red");
            comboBox.getParent().requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateInput(DatePicker datePicker) {
        if (datePicker.getValue() == null) {
            datePicker.setPromptText("Choose a date");
            datePicker.getParent().requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateInputServing() {
        boolean b = validateInput(amountField) && validateInput(caloriesField) && validateInput(fatField)
                && validateInput(sodiumField) && validateInput(sugarField);
        try { Double.parseDouble(caloriesField.getText()); } catch(Exception e) {
            caloriesField.clear();
            caloriesField.setPromptText("Input a number here");
            caloriesField.getParent().requestFocus();
            return false;
        }
        try { Double.parseDouble(fatField.getText()); } catch(Exception e) {
            fatField.clear();
            fatField.setPromptText("Input a number here");
            fatField.getParent().requestFocus();
            return false;
        }
        try { Double.parseDouble(sodiumField.getText()); } catch(Exception e) {
            sodiumField.clear();
            sodiumField.setPromptText("Input a number here");
            sodiumField.getParent().requestFocus();
            return false;
        }
        try { Double.parseDouble(sugarField.getText()); } catch(Exception e) {
            sugarField.clear();
            sugarField.setPromptText("Input a number here");
            sugarField.getParent().requestFocus();
            return false;
        }
        return b;
    }

    private boolean validateInputTime() {
        boolean b = validateInput(timeField) && validateInput(datePicker);
        try { LocalTime.parse(timeField.getText()); } catch (Exception e) {
            timeField.clear();
            timeField.setPromptText("Input time format: HH:mm");
            timeField.getParent().requestFocus();
            return false;
        }
        return b;
    }

    private boolean validateInputIndining() {
        return validateInput(nameField) && validateInputTime() && validateInput(foodGroup) &&
                validateInput(mealField) && validateInput(typeField) && validateInputServing();
    }
    private boolean validateInputOutdining() {
        return validateInput(nameField) && validateInputTime() && validateInput(foodGroup) &&
                validateInput(mealField) && validateInput(retailerField) && validateInputServing();
    }
}
