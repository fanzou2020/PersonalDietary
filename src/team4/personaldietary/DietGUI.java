package team4.personaldietary;

/* some code modified and adapted from:
 * https://www.tutorialspoint.com/javafx/layout_borderpane.htm
 * https://www.tutorialspoint.com/javafx/layout_gridpane.htm
 * http://tutorials.jenkov.com/javafx/index.html
 * https://gist.github.com/jewelsea/5559262
 */

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class DietGUI extends Application {

    Button buttonVeg;
    Button buttonGrain;
    Button buttonMilk;
    Button buttonMeat;
    private BooleanProperty inOutDining = new SimpleBooleanProperty(true);
//    private boolean inOutDining2;

    //Below are the instances to support toggle switches
    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(.25));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(.25));
    private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

    @Override
    public void start(Stage primaryStage) {
        // BorderPane object instantiates the layout for the window.
        // Listview can list items, used in the center pane.
        // The grid pane sets up input boxes and buttons on left pane.
        BorderPane bPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        //Vertical padding for cell
        gridPane.setVgap(2);
        //Horizontal padding for cell
        gridPane.setHgap(2);
        //Choice box for meal
        gridPane.setAlignment(Pos.TOP_CENTER);
        //dining object list to add subclass objects to and update the displaying string adequately.
        ObservableList<Dining> diningList = FXCollections.observableArrayList();

        ListView<Dining> listViewDining = new ListView<>(diningList);

        listViewDining.setEditable(true);

        Text text1 = new Text("Name");
        Text text2 = new Text("Meal");
        Text text3 = new Text("Serving");
        Text text4 = new Text("Group");
        Text text5 = new Text("Time");
        Text text6 = new Text("Type");
        Text text7 = new Text("Retailer");

        TextField nameField = new TextField();
        //TextField mealField = new TextField();
        TextField servingField = new TextField();
        //TextField groupField = new TextField();
        TextField timeField = new TextField();
        TextField typeField = new TextField();
        TextField retailerField = new TextField();
        //Since default indining/outdining toggle is indining, so retailerField default will be grayed out.
        retailerField.setDisable(true);

        Button button1 = new Button("Add Button");
        // Possibly remove button2
        Button button2 = new Button("In/Out Switch");
        Button button3 = new Button("Remove Button");

        //Combo box (drop down) for meal
        ComboBox<String> meal = new ComboBox<>();
        meal.setPromptText("When you ate?");
        meal.getItems().addAll("Break Fast", "Lunch", "Dinner", "Snacks");

        //Combo box for food group
        ComboBox<String> foodGroup = new ComboBox();
        foodGroup.setPromptText("What group does it belong?");
        foodGroup.getItems().addAll("Vegetables & Fruits", "Grain", "Milk & Alternatives", "Meat & Alternatives");

        // Sets event for removal of dining object from listView on button click.
        button3.setOnAction((event -> {
            final int selectedID = listViewDining.getSelectionModel().getSelectedIndex();

                listViewDining.getItems().remove(selectedID);

        }));

        //Initializing buttons for food group
        buttonVeg = new Button("Vegetables & Fruits");
        buttonGrain = new Button("Grain");
        buttonMilk = new Button("Milk & Alternatives");
        buttonMeat = new Button("Meat & Alternatives");

        //Hbox for add and remove button
        HBox hBox = new HBox(20, button1, button2);
        HBox bottomMenu = new HBox(buttonVeg, buttonGrain, buttonMilk, buttonMeat);

        //Customize HBox
        hBox.setPrefSize(64, 64);
        hBox.setLayoutX(10);
        hBox.setLayoutY(7);
        bottomMenu.setPadding(new Insets(10, 0, 10, 0));
        bottomMenu.setSpacing(5);
        bottomMenu.setAlignment(Pos.CENTER);

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
            if(inOutDining.getValue() == true){
                //Indining
                retailerField.clear();
                retailerField.setDisable(true);
                typeField.setDisable(false);

            } else {
                //Outdining
                typeField.clear();
                typeField.setDisable(true);
                retailerField.setDisable(false);
            }
        });

        dining.getChildren().addAll(background, trigger);

        hBox.setPrefSize(64, 64);
        hBox.setLayoutX(10);
        hBox.setLayoutY(7);

        // Sets an event to add dining object to listView<Dining> object on button click.
        // Adds a dining object to the observableList diningList. This diningList is then
        // set to a cell in the listView and the text is set accordingly.
        button1.setOnAction((event -> {
            if(inOutDining.get()) {
                Dining diningItem = new Indining(nameField.getText(), timeField.getText(), servingField.getText(),
                        servingField.getText());
                diningList.add(diningItem);
                listViewDining.setCellFactory(param -> new ListCell<Dining>() {
                    @Override
                    // i think this works because listviewdinig is made of dininglists, so
                    //the dining item from dininglist is the argument here.
                    protected void updateItem(Dining item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null || item.getDining() == null) {
                            setText(null);
                        } else {
                            setText(item.getDining());
                        }
                    }
                });
                //Modifying food group based on user's input
                foodGroupModify(foodGroup.getValue());
                nameField.clear();
                timeField.clear();
                servingField.clear();
//            mealField.clear();
//            groupField.clear();
                typeField.clear();
                retailerField.clear();
            }
            else {
                Dining diningItem = new Outdining(retailerField.getText(), timeField.getText(), meal.getValue(), foodGroup.getValue());
                diningList.add(diningItem);
                listViewDining.setCellFactory(param -> new ListCell<Dining>() {
                    @Override
                    // i think this works because listviewdinig is made of dininglists, so
                    //the dining item from dininglist is the argument here.
                    protected void updateItem(Dining item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null || item.getDining() == null) {
                            setText(null);
                        } else {
                            setText(item.getDining());
                        }
                    }
                });
                //Modifying food group based on user's input
                foodGroupModify(foodGroup.getValue());
                nameField.clear();
                timeField.clear();
                servingField.clear();
//            mealField.clear();
//            groupField.clear();
                typeField.clear();
                retailerField.clear();
            }
        }));

        gridPane.add(text1, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(meal, 1, 1);
        gridPane.add(text3, 0, 2);
        gridPane.add(servingField, 1, 2);
        gridPane.add(text4, 0, 3);
        gridPane.add(foodGroup, 1, 3);
        gridPane.add(text5, 0, 4);
        gridPane.add(timeField, 1, 4);
        gridPane.add(text6, 0, 5);
        gridPane.add(typeField, 1, 5);
        gridPane.add(text7, 0, 6);
        gridPane.add(retailerField, 1, 6);
        gridPane.add(dining, 0, 7);
        gridPane.add(inOut, 1, 7);
        gridPane.add(button1, 0, 7);

        //Setting the top, bottom, center, right and left nodes to the pane
        bPane.setTop(new Text("Personal Dietary Manager Application"));
        bPane.setBottom(bottomMenu);
        bPane.setLeft(gridPane);
        bPane.setRight(button3);
        bPane.setCenter(listViewDining);

        //Creating a scene object
        Scene scene = new Scene(bPane);

        //Setting title to the Stage
        primaryStage.setTitle("Personal Dietary Application");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();

    }

    public void foodGroupModify(String foodGroup){

        switch(foodGroup){

            case "Vegetables & Fruits": buttonVeg.setStyle("-fx-background-color: green; "); break;
            case "Grain": buttonGrain.setStyle("-fx-background-color: green; "); break;
            case "Milk & Alternatives": buttonMilk.setStyle("-fx-background-color: green; "); break;
            case "Meat & Alternatives": buttonMeat.setStyle("-fx-background-color: green; "); break;

        }

    }

    public static void main(String args[]){
        launch(args);

    }
}
