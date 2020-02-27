package team4.personaldietary.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class FXGui2 extends Application {
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("content.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 1800, 1000);
        primaryStage.setTitle("Personal Dietary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
