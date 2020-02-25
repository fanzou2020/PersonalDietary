package main.team4.personaldietary.GUI.dietLayout.view;

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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.team4.personaldietary.bean.DiningBean;
import main.team4.personaldietary.bean.IndiningBean;
import main.team4.personaldietary.bean.OutdiningBean;
import main.team4.personaldietary.business.DietCalculate;
import main.team4.personaldietary.persistence.DiningDAO;

import java.util.HashMap;

public class DietFXMenuController {

    @FXML
    private BorderPane bPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button bt_add;
    @FXML
    private Button bt_remove;
    @FXML
    private Button bt_veg;
    @FXML
    private Button bt_grain;
    @FXML
    private Button bt_milk;
    @FXML
    private Button bt_meat;
    @FXML
    private TextField nameField;
    @FXML
    private TextField servingField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField retailerField;
    @FXML
    private Label nameLabel;
    @FXML
    private Label mealLabel;
    @FXML
    private Label servingLabel;
    @FXML
    private Label groupLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label retailerLabel;

    private DiningBean diningBean;
    private DiningDAO diningDAO;
    private DietCalculate dietCalculate;
    private BooleanProperty inOutDining;
    private HashMap<String, Integer> groupHash;

    //Below are the instances to support toggle switches
    private TranslateTransition translateAnimation;
    private FillTransition fillAnimation;
    private ParallelTransition animation;

    public DietFXMenuController(){
        super();
        diningBean = new DiningBean();
        dietCalculate = new DietCalculate();
        groupHash = new HashMap<String, Integer>();
        inOutDining = new SimpleBooleanProperty(true);
    }

    @FXML
    private void initialize(){
        translateAnimation = new TranslateTransition(Duration.seconds(.25));
        fillAnimation = new FillTransition(Duration.seconds(.25));
        animation = new ParallelTransition(translateAnimation, fillAnimation);

        // Initialize food group hash map to keep track of food items belonging to food groups
        groupHash.put("Vegetables & Fruit", 0);
        groupHash.put("Grain Products", 0);
        groupHash.put("Milk & Alternatives", 0);
        groupHash.put("Meat & Alternatives", 0);

        // BorderPane object instantiates the layout for the window.
        // Listview can list items, used in the center pane.
        // The grid pane sets up input boxes and buttons on left pane.
        //bPane = new BorderPane();
        //gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        //Vertical padding for cell
        gridPane.setVgap(2);
        //Horizontal padding for cell
        gridPane.setHgap(2);
        //Choice box for meal
        gridPane.setAlignment(Pos.TOP_CENTER);
        //dining object list to add subclass objects to and update the displaying string adequately.
        ObservableList<DiningBean> diningList = FXCollections.observableArrayList();

        ListView<DiningBean> listViewDining = new ListView<>(diningList);

        listViewDining.setEditable(true);

        nameLabel = new Label("Name");
        mealLabel = new Label("Meal");
        servingLabel = new Label("Serving");
        groupLabel = new Label("Group");
        timeLabel = new Label("Time");
        typeLabel = new Label("Type");
        retailerLabel = new Label("Retailer");

        //Since default indining/outdining toggle is indining, so retailerField default will be grayed out.
        retailerField.setDisable(true);

        bt_add = new Button("Add Button");
        bt_remove = new Button("Remove Button");

        //Combo box (drop down) for meal
        ComboBox<String> meal = new ComboBox<>();
        meal.setPromptText("When you ate?");
        meal.getItems().addAll("Breakfast", "Lunch", "Dinner", "Snacks");

        //Combo box for food group
        ComboBox<String> foodGroup = new ComboBox();
        foodGroup.setPromptText("What group does it belong?");
        foodGroup.getItems().addAll("Vegetables & Fruit", "Grain Products", "Milk & Alternatives", "Meat & Alternatives");

        //Initializing buttons for food group
        bt_veg = new Button("Vegetables & Fruit");
        bt_grain = new Button("Grain Products");
        bt_milk = new Button("Milk & Alternatives");
        bt_meat = new Button("Meat & Alternatives");

        //Hbox for add and other button
        HBox hBox = new HBox(20, bt_add);
        HBox bottomMenu = new HBox(bt_veg, bt_grain, bt_milk, bt_meat);

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
                servingField.setDisable(false);

            } else {
                //Outdining
                typeField.clear();
                typeField.setDisable(true);
                retailerField.setDisable(false);
                servingField.clear();
                servingField.setDisable(true);
            }
        });

        dining.getChildren().addAll(background, trigger);

        // Sets an event to add dining object to listView<Dining> object on button click.
        // Adds a dining object to the observableList diningList. This diningList is then
        // set to a cell in the listView and the text is set accordingly.
        bt_add.setOnAction((event -> {
            if(inOutDining.get()) {
                DiningBean diningItem = new IndiningBean(nameField.getText(), timeField.getText(), servingField.getText(),
                        typeField.getText(), foodGroup.getValue());
                diningList.add(diningItem);

                // Increments respective integer value for food group hash map, indicating food group is eaten
                foodGroupHashAdd(foodGroup.getValue());

                listViewDining.setCellFactory(param -> new ListCell<DiningBean>() {
                    @Override
                    // i think this works because listviewdinig is made of dininglists, so
                    //the dining item from dininglist is the argument here.
                    protected void updateItem(DiningBean item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null || item.getName() == null) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                });
                //Modifying food group based on user's input

                foodGroupModify(foodGroup.getValue());
                nameField.clear();
                timeField.clear();
                servingField.clear();
                typeField.clear();
                retailerField.clear();
            }
            else {
                DiningBean diningItem = new OutdiningBean(nameField.getText(), retailerField.getText(), timeField.getText(),
                        meal.getValue(), foodGroup.getValue());
                diningList.add(diningItem);

                // Increments respective integer value for food group hash map, indicating food group is eaten
                foodGroupHashAdd(foodGroup.getValue());

                listViewDining.setCellFactory(param -> new ListCell<DiningBean>() {
                    @Override
                    // i think this works because listviewdinig is made of dininglists, so
                    //the dining item from dininglist is the argument here.
                    protected void updateItem(DiningBean item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null || item.getName() == null) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                });
                //Modifying food group based on user's input
                foodGroupModify(foodGroup.getValue());
                nameField.clear();
                timeField.clear();
                servingField.clear();
                typeField.clear();
                retailerField.clear();
            }
        }));

        // Sets event for removal of dining object from listView on button click.
        bt_remove.setOnAction((event -> {
            final int selectedID = listViewDining.getSelectionModel().getSelectedIndex();
            DiningBean foodObj = listViewDining.getSelectionModel().getSelectedItem();

            listViewDining.getItems().remove(selectedID);

            // Obtains food group of selected object, decrements hash map value, and removes color of button if required
            foodGroupHashRemove(foodObj.getGroup());
            if(groupHash.get(foodObj.getGroup())<=0) {
                foodGroupRemove(foodObj.getGroup());
            }
        }));

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(mealLabel, 0, 1);
        gridPane.add(meal, 1, 1);
        gridPane.add(servingLabel, 0, 2);
        gridPane.add(servingField, 1, 2);
        gridPane.add(groupLabel, 0, 3);
        gridPane.add(foodGroup, 1, 3);
        gridPane.add(timeLabel, 0, 4);
        gridPane.add(timeField, 1, 4);
        gridPane.add(typeLabel, 0, 5);
        gridPane.add(typeField, 1, 5);
        gridPane.add(retailerLabel, 0, 6);
        gridPane.add(retailerField, 1, 6);
        gridPane.add(dining, 0, 7);
        gridPane.add(inOut, 1, 7);
        gridPane.add(bt_add, 1, 10);

        //Setting the top, bottom, center, right and left nodes to the pane
        bPane.setTop(new Text("Personal Dietary Manager Application"));
        bPane.setBottom(bottomMenu);
        bPane.setLeft(gridPane);
        bPane.setRight(bt_remove);
        bPane.setCenter(listViewDining);

        //Creating a scene object
        //Scene scene = new Scene(bPane);

    }

    public void foodGroupHashAdd(String foodGroup) {

        int g = groupHash.get(foodGroup);
        groupHash.replace(foodGroup, g + 1);

    }

    public void foodGroupHashRemove(String foodGroup) {

        int x = groupHash.get(foodGroup);
        groupHash.replace(foodGroup, x - 1);

    }

    public void foodGroupModify(String foodGroup) {

        switch(foodGroup) {

            case "Vegetables & Fruit": bt_veg.setStyle("-fx-background-color: green; "); break;
            case "Grain Products": bt_grain.setStyle("-fx-background-color: green; "); break;
            case "Milk & Alternatives": bt_milk.setStyle("-fx-background-color: green; "); break;
            case "Meat & Alternatives": bt_meat.setStyle("-fx-background-color: green; "); break;

        }
    }

    public void foodGroupRemove(String foodGroup) {

        switch(foodGroup) {

            case "Vegetables & Fruit": bt_veg.setStyle("-fx-background-color: none; "); break;
            case "Grain Products": bt_grain.setStyle("-fx-background-color: none; "); break;
            case "Milk & Alternatives": bt_milk.setStyle("-fx-background-color: none; "); break;
            case "Meat & Alternatives": bt_meat.setStyle("-fx-background-color: none; "); break;
        }
    }

}
