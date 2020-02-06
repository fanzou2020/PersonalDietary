package team4.personaldietary.ui;

/* some code from https://www.tutorialspoint.com/javafx/layout_borderpane.htm
 * https://www.tutorialspoint.com/javafx/layout_gridpane.htm
 * http://tutorials.jenkov.com/javafx/index.html */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.io.InputStream;
import javafx.scene.image.Image;

public class DietGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        //Instantiating the BorderPane object
        BorderPane bPane = new BorderPane();
        ListView listV = new ListView();
        GridPane gridPane = new GridPane();

        Text text1 = new Text("Name");
        Text text2 = new Text("Meal");
        Text text3 = new Text("Serving");
        Text text4 = new Text("Group");
        Text text5 = new Text("Time");
        Text text6 = new Text("Type");
        Text text7 = new Text("Retailer");

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        TextField textField7 = new TextField();

        Button button1 = new Button("Add Button");
        Button button2 = new Button("In/Out Switch");
        Button button3 = new Button("Remove Button");

        HBox hbox = new HBox(20, button1, button2);

        hbox.setPrefSize(64, 64);
        hbox.setLayoutX(10);
        hbox.setLayoutY(7);

        listV.getItems().add("Food 1");
        listV.getItems().add("Food 2");
        listV.getItems().add("Food 3");
        listV.getItems().add("Food 4");

        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(textField2, 1, 1);
        gridPane.add(text3, 0, 2);
        gridPane.add(textField3, 1, 2);
        gridPane.add(text4, 0, 3);
        gridPane.add(textField4, 1, 3);
        gridPane.add(text5, 0, 4);
        gridPane.add(textField5, 1, 4);
        gridPane.add(text6, 0, 5);
        gridPane.add(textField6, 1, 5);
        gridPane.add(text7, 0, 6);
        gridPane.add(textField7, 1, 6);
        gridPane.add(button1, 0, 7);
        gridPane.add(button2, 1, 7);

        //Setting the top, bottom, center, right and left nodes to the pane
        bPane.setTop(new TextField("Top"));
        bPane.setBottom(new TextField("Bottom"));
        bPane.setLeft(gridPane);
        bPane.setRight(button3);
        bPane.setCenter(listV);

        //Creating a scene object
        Scene scene = new Scene(bPane);

        //Setting title to the Stage
        primaryStage.setTitle("BorderPane Example");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();

    }

    public static void main(String args[]){
        launch(args);

    }
}
