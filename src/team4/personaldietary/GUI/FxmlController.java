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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

public class FxmlController {
    @FXML
    private BorderPane bPane;

    private ObservableList<Dining> diningList = FXCollections.observableArrayList();
    private ListView<Dining> listView = new ListView<>(diningList);
    private DiningDAO diningDAO = new DiningDAOImpl();
    private int[] numItemsInFoodGroup = {0, 0, 0, 0};

    //Initializing buttons for food group
    Button buttonVeg = new Button("Vegetables and Fruits");
    Button buttonGrain = new Button("Grain Products");
    Button buttonMilk = new Button("Milk and Alternatives");
    Button buttonMeat = new Button("Meat and Alternatives");

    @FXML
    private void initialize() {
        initialLeftPart();
        initialRightPart();
        initialBottomPart();
        initialCenterPart();
    }

    // Initialize the ListView in the center of boarder pane
    private void initialCenterPart() {
        bPane.setCenter(listView);
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


        Text text1 = new Text("Name");
        Text text2 = new Text("Time");
        Text text3 = new Text("Group");
        Text text4 = new Text("Serving");
        Text text5 = new Text("Meal");
        Text text6 = new Text("Type");
        Text text7 = new Text("Retailer");

        TextField nameField = new TextField();
        TextField timeField = new TextField();
        // TextField groupField = new TextField();
        TextField servingField = new TextField();
        TextField mealField = new TextField();
        TextField typeField = new TextField();
        TextField retailerField = new TextField();
        // Since default indining/outdining toggle is indining, default gray out retailer field.
        retailerField.setDisable(true);

        Button addItemButton = new Button("Add Item");

        //Combo box for food group
        ComboBox<String> foodGroup = new ComboBox<>();
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
                Dining foodItem;
                // if it is indining food item
                if (inOutDining.get()) {
                    foodItem = new Indining(nameField.getText(), timeField.getText(),
                            stringToGroup(foodGroup.getValue()), servingField.getText(),
                            mealField.getText(), typeField.getText());
                }
                // else, add outdining food item
                else {
                    foodItem = new Outdining(nameField.getText(), timeField.getText(),
                            stringToGroup(foodGroup.getValue()), servingField.getText(),
                            mealField.getText(), retailerField.getText());
                }

                // call add food item function of the business layer
                diningDAO.addDiningItem(diningList, foodItem);
                markFoodGroupAdd(foodItem.getGroup());

                // clear the content in left panel after click the add button.
                nameField.clear(); timeField.clear(); servingField.clear(); mealField.clear(); retailerField.clear();
                typeField.clear(); foodGroup.getSelectionModel().clearSelection();
            }
        };
        addItemButton.setOnAction(addEventHandler);
//        addItemButton.addEventFilter(ActionEvent.ANY, eventHandler);
        // **************************** End of event handle for add food item ******************






        // add item to gridPane
        gridPane.add(text1, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(timeField, 1, 1);
        gridPane.add(text3, 0, 2);
        gridPane.add(foodGroup, 1, 2);
        gridPane.add(text4, 0, 3);
        gridPane.add(servingField, 1, 3);
        gridPane.add(text5, 0, 4);
        gridPane.add(mealField, 1, 4);
        gridPane.add(text6, 0, 5);
        gridPane.add(typeField, 1, 5);
        gridPane.add(text7, 0, 6);
        gridPane.add(retailerField, 1, 6);
        gridPane.add(dining, 0, 7);
        gridPane.add(inOut, 1, 7);
        gridPane.add(addItemButton, 1, 10);

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

        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Dining foodItem = listView.getSelectionModel().getSelectedItem();

                diningDAO.removeDiningItem(diningList, foodItem);

                markFoodGroupRemove(foodItem.getGroup());

            }
        });

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

    private Group stringToGroup(String s) {
        if (s.equals("Vegetables and Fruits")) return Group.vegetable_and_fruit;
        else if (s.equals("Grain Products")) return Group.grain_products;
        else if (s.equals("Milk and Alternatives")) return Group.milk_and_alternatives;
        else if (s.equals("Meat and Alternatives")) return Group.meat_and_alternatives;
        else return null;
    }

    private void markFoodGroupAdd(Group group) {
        if (group == Group.vegetable_and_fruit) {
            numItemsInFoodGroup[0]++;
            if (numItemsInFoodGroup[0] > 0) buttonVeg.setStyle("-fx-background-color: green");
        }
        else if (group == Group.grain_products) {
            numItemsInFoodGroup[1]++;
            if (numItemsInFoodGroup[1] >0)  buttonGrain.setStyle("-fx-background-color: green");
        }
        else if (group == Group.milk_and_alternatives) {
            numItemsInFoodGroup[2]++;
            if (numItemsInFoodGroup[2] > 0) buttonMilk.setStyle("-fx-background-color: green");
        }
        else {
            numItemsInFoodGroup[3]++;
            if (numItemsInFoodGroup[3] > 0) buttonMeat.setStyle("-fx-background-color: green");
        }
    }

    private void markFoodGroupRemove(Group group) {
        if (group == Group.vegetable_and_fruit) {
            numItemsInFoodGroup[0]--;
            if (numItemsInFoodGroup[0] <= 0) buttonVeg.setStyle("-fx-background-color: grey");
        }
        else if (group == Group.grain_products) {
            numItemsInFoodGroup[1]--;
            if (numItemsInFoodGroup[1] <= 0)  buttonGrain.setStyle("-fx-background-color: grey");
        }
        else if (group == Group.milk_and_alternatives) {
            numItemsInFoodGroup[2]--;
            if (numItemsInFoodGroup[2] <= 0) buttonMilk.setStyle("-fx-background-color: grey");
        }
        else {
            numItemsInFoodGroup[3]--;
            if (numItemsInFoodGroup[3] <= 0) buttonMeat.setStyle("-fx-background-color: grey");
        }
    }
}
