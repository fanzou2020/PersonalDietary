package team4.personaldietary;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Observable;

public class FXGui extends Application {
    private static BorderPane bPane = new BorderPane();
    private static ObservableList<String> diningList = FXCollections.observableArrayList();
    private static ListView<String> listView = new ListView<>(diningList);

    /*
    Initialize the grid pane on the left part of boarder pane.
     */
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

        Button button1 = new Button("Add Item");

        //Combo box for food group
        ComboBox<String> foodGroup = new ComboBox<>();
        foodGroup.setPromptText("What group does it belong?");
        foodGroup.getItems().addAll("Vegetables & Fruits", "Grain", "Milk & Alternatives", "Meat & Alternatives");


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
        gridPane.add(button1, 1, 10);

        // Setting the boarder pane
        bPane.setLeft(gridPane);
    }

    /*
    Initialize the ListView in the center of boarder pane
     */
    private void initialCenterPart() {
        diningList.add("Item 1");
        diningList.add("Item 2");
        bPane.setCenter(listView);
    }

    /*
    Initialize the Right part of boarder pane
     */
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
        bPane.setRight(gridPane);
    }

    /*
    Initialize the Bottom part of boarder pane
     */
    private void initialBottomPart() {
        //Initializing buttons for food group
        Button buttonVeg = new Button("Vegetables & Fruit");
        Button buttonGrain = new Button("Grain Products");
        Button buttonMilk = new Button("Milk & Alternatives");
        Button buttonMeat = new Button("Meat & Alternatives");
        HBox bottomMenu = new HBox(buttonVeg, buttonGrain, buttonMilk, buttonMeat);

        //Customize HBox
        bottomMenu.setPadding(new Insets(10, 0, 10, 0));
        bottomMenu.setSpacing(5);
        bottomMenu.setAlignment(Pos.CENTER);

        bPane.setBottom(bottomMenu);
    }


    public static void updateView(ArrayList<String> arrayList) {
        diningList.removeAll();
        diningList = FXCollections.observableArrayList(arrayList);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        // The grid pane sets up input boxes and buttons on left pane.
        initialLeftPart();

        // Listview can list items, used in the center pane.

        initialCenterPart();

        // Button on the right part of boarder pane
        initialRightPart();

        // Categories buttons menu on the bottom part of boarder pane
        initialBottomPart();


        // Creating a scene object
        Scene scene = new Scene(bPane, 1000, 600);

        // Setting title to the Stage
        primaryStage.setTitle("Personal Dietary Application");

        // Adding scene to the stage
        primaryStage.setScene(scene);

        // Displaying the contents of the stage
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Item 3");
        updateView(arrayList);
    }

}
