package team4.personaldietary;

/* some code modified and adapted from:
 * https://www.tutorialspoint.com/javafx/layout_borderpane.htm
 * https://www.tutorialspoint.com/javafx/layout_gridpane.htm
 * http://tutorials.jenkov.com/javafx/index.html
 * https://gist.github.com/jewelsea/5559262
 */

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

    private boolean inOutDining;

    @Override
    public void start(Stage primaryStage) {
        // BorderPane object instantiates the layout for the window.
        // Listview can list items, used in the center pane.
        // The grid pane sets up input boxes and buttons on left pane.
        BorderPane bPane = new BorderPane();
        GridPane gridPane = new GridPane();

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
        TextField mealField = new TextField();
        TextField servingField = new TextField();
        TextField groupField = new TextField();
        TextField timeField = new TextField();
        TextField typeField = new TextField();
        TextField retailerField = new TextField();

        Button button1 = new Button("Add Button");
        Button button2 = new Button("In/Out Switch");
        Button button3 = new Button("Remove Button");

        // Sets an event to add dining object to listView<Dining> object on button click.
        // Adds a dining object to the observableList diningList. This diningList is then
        // set to a cell in the listView and the text is set accordingly.
        button1.setOnAction((event -> {
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
                    }
                    else {
                        setText(item.getDining());
                    }
                }
            });
            //clear() method called from textfield objects to remove any string text from input fields.
            nameField.clear();
            timeField.clear();
            servingField.clear();
            mealField.clear();
            groupField.clear();
            typeField.clear();
            retailerField.clear();

        }));

        // Sets event for removal of dining object from listView on button click.
        button3.setOnAction((event -> {
            final int selectedID = listViewDining.getSelectionModel().getSelectedIndex();

                listViewDining.getItems().remove(selectedID);

        }));


        CheckBox cBox1 = new CheckBox("Food Group 1");
        CheckBox cBox2 = new CheckBox("Food Group 2");
        CheckBox cBox3 = new CheckBox("Food Group 3");
        CheckBox cBox4 = new CheckBox("Food Group 4");

        HBox hBox = new HBox(20, button1, button2);
        HBox checkHBox = new HBox(cBox1, cBox2, cBox3, cBox4);

        hBox.setPrefSize(64, 64);
        hBox.setLayoutX(10);
        hBox.setLayoutY(7);

        gridPane.add(text1, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(mealField, 1, 1);
        gridPane.add(text3, 0, 2);
        gridPane.add(servingField, 1, 2);
        gridPane.add(text4, 0, 3);
        gridPane.add(groupField, 1, 3);
        gridPane.add(text5, 0, 4);
        gridPane.add(timeField, 1, 4);
        gridPane.add(text6, 0, 5);
        gridPane.add(typeField, 1, 5);
        gridPane.add(text7, 0, 6);
        gridPane.add(retailerField, 1, 6);
        gridPane.add(button1, 0, 7);

        //Setting the top, bottom, center, right and left nodes to the pane
        bPane.setTop(new Text("Personal Dietary Manager Application"));
        bPane.setBottom(checkHBox);
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

    public static void main(String args[]){
        launch(args);

    }
}
